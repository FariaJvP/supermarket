package com.sm.supermarket.productandinventory.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sm.supermarket.SupermarketApplication;
import com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.brand.BrandNotFoundException;
import com.sm.supermarket.productandinventory.web.dto.NewProductRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Value("${com.sm.supermarket.uri.productcontroller}")
    private String uriController;

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
     @DisplayName("should trows a resolved exception if the user send an invalid brand id")
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
                 .andExpect(MockMvcResultMatchers.status().isNotFound())
                 .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof BrandNotFoundException));
     }
}