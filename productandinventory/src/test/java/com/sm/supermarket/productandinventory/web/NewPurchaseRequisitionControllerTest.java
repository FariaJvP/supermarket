package com.sm.supermarket.productandinventory.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sm.supermarket.SupermarketApplication;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.ProductToBeOrdered;
import com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.product.ProductNotFoundException;
import com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition.CreatePurchaseRequisition;
import com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition.PurchaseRequisitionForm;
import com.sm.supermarket.productandinventory.web.dto.ProductToBeOrderedRequisition;
import com.sm.supermarket.productandinventory.web.dto.PurchaseRequisitionRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


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

    private URI uri;

    @Value("${com.sm.supermarket.uri.purchaserequisition.new}")
    private String uriController;

    @Autowired
    private CreatePurchaseRequisition createPurchaseRequisition;

    @Test
    @DisplayName("should return status 201 when receives a valid request")
    public void test1() throws Exception {

        Set<ProductToBeOrderedRequisition> listOfProductsToBeOrdered = new HashSet<>();
        listOfProductsToBeOrdered.add(new ProductToBeOrderedRequisition(1 , BigInteger.valueOf(1000000)));
        listOfProductsToBeOrdered.add(new ProductToBeOrderedRequisition(2 , BigInteger.valueOf(400)));
        listOfProductsToBeOrdered.add(new ProductToBeOrderedRequisition(3 , BigInteger.valueOf(100)));

        PurchaseRequisitionRequest newPurchaseOrderRequest = new PurchaseRequisitionRequest(listOfProductsToBeOrdered);

        uri = new URI(uriController);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(newPurchaseOrderRequest));

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

        Set<ProductToBeOrderedRequisition> listOfProductsToBeOrdered = new HashSet<>();
        listOfProductsToBeOrdered.add(new ProductToBeOrderedRequisition(1 , BigInteger.valueOf(-1000000)));
        listOfProductsToBeOrdered.add(new ProductToBeOrderedRequisition(2 , BigInteger.valueOf(400)));
        listOfProductsToBeOrdered.add(new ProductToBeOrderedRequisition(3 , BigInteger.valueOf(100)));

        PurchaseRequisitionRequest newPurchaseOrderRequest = new PurchaseRequisitionRequest(listOfProductsToBeOrdered);

        uri = new URI(uriController);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(newPurchaseOrderRequest));

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException)).andReturn();
    }

    @Test
    @DisplayName("should throws exception if the client send a negative value for a product to be ordered")
    public void test3() throws Exception {

        Set<ProductToBeOrderedRequisition> listOfProductsToBeOrdered = new HashSet<>();
        listOfProductsToBeOrdered.add(new ProductToBeOrderedRequisition(1 , BigInteger.valueOf(-1000000)));
        listOfProductsToBeOrdered.add(new ProductToBeOrderedRequisition(2 , BigInteger.valueOf(400)));
        listOfProductsToBeOrdered.add(new ProductToBeOrderedRequisition(3 , BigInteger.valueOf(100)));

        PurchaseRequisitionRequest newPurchaseOrderRequest = new PurchaseRequisitionRequest(listOfProductsToBeOrdered);
        PurchaseRequisitionForm purchaseRequisitionForm = newPurchaseOrderRequest.convertIntoPurchaseRequisitionForm();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> createPurchaseRequisition.execute(purchaseRequisitionForm));

    }

    @Test
    @DisplayName("should throws exception if the client send 0 as value for a product to be ordered")
    public void test4() throws Exception {

        Set<ProductToBeOrderedRequisition> listOfProductsToBeOrdered = new HashSet<>();
        listOfProductsToBeOrdered.add(new ProductToBeOrderedRequisition(1 , BigInteger.valueOf(0)));
        listOfProductsToBeOrdered.add(new ProductToBeOrderedRequisition(2 , BigInteger.valueOf(400)));
        listOfProductsToBeOrdered.add(new ProductToBeOrderedRequisition(3 , BigInteger.valueOf(100)));

        PurchaseRequisitionRequest newPurchaseOrderRequest = new PurchaseRequisitionRequest(listOfProductsToBeOrdered);
        PurchaseRequisitionForm purchaseRequisitionForm = newPurchaseOrderRequest.convertIntoPurchaseRequisitionForm();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> createPurchaseRequisition.execute(purchaseRequisitionForm));

    }

    @Test
    @DisplayName("should throws exception if the client send an invalid product id")
    public void test5() throws Exception {

        Set<ProductToBeOrderedRequisition> listOfProductsToBeOrdered = new HashSet<>();
        listOfProductsToBeOrdered.add(new ProductToBeOrderedRequisition(55 , BigInteger.valueOf(1000000)));
        listOfProductsToBeOrdered.add(new ProductToBeOrderedRequisition(2 , BigInteger.valueOf(400)));
        listOfProductsToBeOrdered.add(new ProductToBeOrderedRequisition(3 , BigInteger.valueOf(100)));

        PurchaseRequisitionRequest newPurchaseOrderRequest = new PurchaseRequisitionRequest(listOfProductsToBeOrdered);
        PurchaseRequisitionForm purchaseRequisitionForm = newPurchaseOrderRequest.convertIntoPurchaseRequisitionForm();

        Assertions.assertThrows(ProductNotFoundException.class, () -> createPurchaseRequisition.execute(purchaseRequisitionForm));

    }

}