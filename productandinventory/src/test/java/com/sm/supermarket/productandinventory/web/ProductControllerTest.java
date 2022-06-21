package com.sm.supermarket.productandinventory.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sm.supermarket.SupermarketApplication;
import com.sm.supermarket.productandinventory.usecases.product.newproduct.CreatedProduct;
import com.sm.supermarket.productandinventory.web.dto.NewProductRequest;
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

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.net.URI;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(classes = SupermarketApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private URI uri;

    @Value("${com.sm.supermarket.uri.newproduct}")
    private String uriController;

    @Autowired
    CreatedProduct createdProduct;

    @Test
    @DisplayName("should return status 201 when receives a valid request")
    public void test1() throws Exception {

        NewProductRequest newProductRequest = new NewProductRequest("Penne alla Vodka", 6L,
                "Frozen meal, 286g, vegetarian and no added sugar." , new BigDecimal("15.00"), "UNIT");

        uri = new URI(uriController);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(newProductRequest));

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

    }

     @Test
     @DisplayName("should throws a resolved exception if the user send an invalid brand id")
     public void test2() throws Exception {
         NewProductRequest newProductRequest = new NewProductRequest("Penne alla Vodka", 32L,
                 "Frozen meal, 286g, vegetarian and no added sugar." , new BigDecimal("15.00"), "UNIT");

         uri = new URI(uriController);
         MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                 .post(uri)
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper
                         .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                         .writeValueAsString(newProductRequest));

         mockMvc.perform(request)
                 .andDo(MockMvcResultHandlers.print())
                 .andExpect(MockMvcResultMatchers.status().isBadRequest())
                 .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
     }

    @Test
    @DisplayName("should throws a resolved exception if the user send an invalid name which causes a MethodArgumentNotValidExption")
    public void test3() throws Exception {

        NewProductRequest newProductRequest = new NewProductRequest(" ", 6L,
                "Frozen meal, 286g, vegetarian and no added sugar." , new BigDecimal("15.00"), "UNIT");

        uri = new URI(uriController);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(newProductRequest));

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException)).andReturn();
    }

    @Test
    @DisplayName("should throws a resolved exception if the user send an invalid description which causes a MethodArgumentNotValidExption")
    public void test4() throws Exception {
        NewProductRequest newProductRequest = new NewProductRequest("Penne alla Vodka", 6L,
                " " , new BigDecimal("15.00"), "UNIT");

        uri = new URI(uriController);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(newProductRequest));

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException)).andReturn();
    }

    @Test
    @DisplayName("should throws a resolved exception if the user send an invalid price value which causes a MethodArgumentNotValidExption")
    public void test5() throws Exception {
        String json = "{\"name\":\"Penne alla Vodka\", \"brandId\":6, \"description\":\"Frozen meal, 286g, vegetarian and no added sugar.\", \"price\":\"  \", \"unit\":\"UNIT\"}";

        uri = new URI(uriController);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException)).andReturn();
    }

    @Test
    @DisplayName("should throws a resolved exception if the user send a negative price value which causes a MethodArgumentNotValidExption")
    public void test6() throws Exception {
        String json = "{\"name\":\"Penne alla Vodka\", \"brandId\":6, \"description\":\"Frozen meal, 286g, vegetarian and no added sugar.\", \"price\":\"-15.00\", \"unit\":\"UNIT\"}";

        uri = new URI(uriController);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException)).andReturn();
    }

    @Test
    @DisplayName("should throws a resolved exception if the user send a request with a value that be the last negative boundary of price value which causes a database constraint violation")
    public void test7() throws Exception {
        String json = "{\"name\":\"Penne alla Vodka\", \"brandId\":6, \"description\":\"Frozen meal, 286g, vegetarian and no added sugar.\", \"price\":\"-0.01\", \"unit\":\"UNIT\"}";

        uri = new URI(uriController);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException)).andReturn();
    }

    @Test
    @DisplayName("should throws a resolved exception if the user send an invalid unit value which causes a database constraint violation")
    public void test8() throws Exception {
        NewProductRequest newProductRequest = new NewProductRequest("Penne alla Vodka", 6L,
                "Frozen meal, 286g, vegetarian and no added sugar." , new BigDecimal("15.00"), " ");

        uri = new URI(uriController);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(newProductRequest));

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException)).andReturn();
    }

    @Test
    @DisplayName("should throws a resolved exception if the user send an invalid name which causes a database constraint violation")
    public void test9(){
        NewProductRequest newProductRequest = new NewProductRequest(" ", 6L,
                "Frozen meal, 286g, vegetarian and no added sugar." , new BigDecimal("15.00"), "UNIT");

        Assertions.assertThrows(ConstraintViolationException.class, () -> createdProduct.execute(newProductRequest));
    }

    @Test
    @DisplayName("should throws a resolved exception if the user send an invalid description which causes a database constraint violation")
    public void test10(){
        NewProductRequest newProductRequest = new NewProductRequest("Penne alla Vodka", 6L,
                " " , new BigDecimal("15.00"), "UNIT");

        Assertions.assertThrows(ConstraintViolationException.class, () -> createdProduct.execute(newProductRequest));
    }

    @Test
    @DisplayName("should throws a resolved exception if the user send an invalid price value which causes a data integrity violation in database")
    public void test11(){
        NewProductRequest newProductRequest = new NewProductRequest("Penne alla Vodka", 6L,
                "Frozen meal, 286g, vegetarian and no added sugar." , new BigDecimal("-15.00"), "UNIT");

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> createdProduct.execute(newProductRequest));
    }

    @Test
    @DisplayName("should throws a resolved exception if the user send a request with a value that be the last negative boundary of price value causes a data integrity violation in database")
    public void test12(){
        NewProductRequest newProductRequest = new NewProductRequest("Penne alla Vodka", 6L,
                "Frozen meal, 286g, vegetarian and no added sugar." , new BigDecimal("-0.01"), "UNIT");

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> createdProduct.execute(newProductRequest));
    }
}