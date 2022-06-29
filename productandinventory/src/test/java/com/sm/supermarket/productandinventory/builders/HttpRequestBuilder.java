package com.sm.supermarket.productandinventory.builders;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sm.supermarket.productandinventory.web.dto.NewProductRequest;
import com.sm.supermarket.productandinventory.web.dto.PurchaseRequisitionRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;

public class HttpRequestBuilder {

    public MockHttpServletRequestBuilder getMockHttpServletRequestBuilder(URI uri, ObjectMapper objectMapper, PurchaseRequisitionRequest purchaseRequisitionRequest) throws JsonProcessingException {
        return MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(purchaseRequisitionRequest));
    }

    public MockHttpServletRequestBuilder getMockHttpServletRequestBuilder(URI uri, ObjectMapper objectMapper, NewProductRequest newProductRequest) throws JsonProcessingException {
        return MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(newProductRequest));
    }

}
