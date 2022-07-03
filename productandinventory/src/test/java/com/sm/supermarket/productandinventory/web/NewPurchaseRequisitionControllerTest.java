package com.sm.supermarket.productandinventory.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sm.supermarket.SupermarketApplication;
import com.sm.supermarket.productandinventory.builders.HttpRequestBuilder;
import com.sm.supermarket.productandinventory.builders.PurchaseRequisitionDataBuilder;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.producttobeordered.ProductToBeOrdered;
import com.sm.supermarket.productandinventory.web.dto.PurchaseRequisitionRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.net.URI;
import java.util.Collection;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(classes = SupermarketApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class NewPurchaseRequisitionControllerTest {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${com.sm.supermarket.uri.purchaserequisition.new}")
    private String uriController;

    @Test
    @DisplayName("should return status 201 when receives a valid request")
    public void test1() throws Exception {

        PurchaseRequisitionRequest newPurchaseOrderRequest = new PurchaseRequisitionDataBuilder()
                    .withProductToBeOrdered(1, "KG", BigInteger.valueOf(1000000))
                    .withProductToBeOrdered(2, "UNIT" , BigInteger.valueOf(400))
                    .withProductToBeOrdered(3, "UNIT", BigInteger.valueOf(100))
                .buildPurchaseRequisitionRequest();

        MockHttpServletRequestBuilder request = new HttpRequestBuilder()
                .getMockHttpServletRequestBuilder(new URI(uriController), objectMapper, newPurchaseOrderRequest);

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        Collection<ProductToBeOrdered> resultList = entityManager.createQuery("select p from ProductToBeOrdered p where p.id.purchaseRequisition.id = 3", ProductToBeOrdered.class).getResultList();

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(3, resultList.size());
    }

    @Test
    @DisplayName("should return a resolved exception when the client sends an invalid value for product quantity")
    public void test2() throws Exception {

        PurchaseRequisitionRequest newPurchaseOrderRequest = new PurchaseRequisitionDataBuilder()
                    .withProductToBeOrdered(1, "KG" , BigInteger.valueOf(-1000000))
                    .withProductToBeOrdered(2, "UNIT", BigInteger.valueOf(400))
                    .withProductToBeOrdered(3, "UNIT", BigInteger.valueOf(100))
                .buildPurchaseRequisitionRequest();

        MockHttpServletRequestBuilder request = new HttpRequestBuilder()
                .getMockHttpServletRequestBuilder(new URI(uriController), objectMapper, newPurchaseOrderRequest);

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException)).andReturn();
    }

    @Test
    @DisplayName("should return a resolved exception when the client sends an invalid id for product")
    public void test3() throws Exception {

        PurchaseRequisitionRequest newPurchaseOrderRequest = new PurchaseRequisitionDataBuilder()
                .withProductToBeOrdered(150, "UNIT", BigInteger.valueOf(300))
                .buildPurchaseRequisitionRequest();

        MockHttpServletRequestBuilder request = new HttpRequestBuilder()
                .getMockHttpServletRequestBuilder(new URI(uriController), objectMapper, newPurchaseOrderRequest);

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException)).andReturn();
    }
}