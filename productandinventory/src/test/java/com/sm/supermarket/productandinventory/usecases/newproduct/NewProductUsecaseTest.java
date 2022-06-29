package com.sm.supermarket.productandinventory.usecases.newproduct;

import com.sm.supermarket.SupermarketApplication;
import com.sm.supermarket.productandinventory.usecases.product.newproduct.CreatedProduct;
import com.sm.supermarket.productandinventory.web.dto.NewProductRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(classes = SupermarketApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class NewProductUsecaseTest {

    @Autowired
    private CreatedProduct createdProduct;

    @Test
    @DisplayName("should throws a resolved exception if the user send an invalid name which causes a database constraint violation")
    public void test1(){
        NewProductRequest newProductRequest = new NewProductRequest(" ", 6L,
                "Frozen meal, 286g, vegetarian and no added sugar." , new BigDecimal("15.00"), "UNIT");

        Assertions.assertThrows(ConstraintViolationException.class, () -> createdProduct.execute(newProductRequest));
    }

    @Test
    @DisplayName("should throws a resolved exception if the user send an invalid description which causes a database constraint violation")
    public void test2(){
        NewProductRequest newProductRequest = new NewProductRequest("Penne alla Vodka", 6L,
                " " , new BigDecimal("15.00"), "UNIT");

        Assertions.assertThrows(ConstraintViolationException.class, () -> createdProduct.execute(newProductRequest));
    }

    @Test
    @DisplayName("should throws a resolved exception if the user send an invalid price value which causes a data integrity violation in database")
    public void test3(){
        NewProductRequest newProductRequest = new NewProductRequest("Penne alla Vodka", 6L,
                "Frozen meal, 286g, vegetarian and no added sugar." , new BigDecimal("-15.00"), "UNIT");

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> createdProduct.execute(newProductRequest));
    }

    @Test
    @DisplayName("should throws a resolved exception if the user send a request with a value that be the last negative boundary of price value causes a data integrity violation in database")
    public void test4(){
        NewProductRequest newProductRequest = new NewProductRequest("Penne alla Vodka", 6L,
                "Frozen meal, 286g, vegetarian and no added sugar." , new BigDecimal("-0.01"), "UNIT");

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> createdProduct.execute(newProductRequest));
    }
}
