package com.sm.supermarket.productandinventory.usecases;

import com.sm.supermarket.productandinventory.entities.brand.Brand;
import com.sm.supermarket.productandinventory.entities.brand.EntityRepositoryForBrand;
import com.sm.supermarket.productandinventory.entities.product.EntityRepositoryForProduct;
import com.sm.supermarket.productandinventory.entities.product.PackageUnit;
import com.sm.supermarket.productandinventory.entities.product.Product;
import com.sm.supermarket.productandinventory.web.dto.NewProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreatedProduct {

    @Autowired
    public EntityRepositoryForBrand brandRepository;

    @Autowired
    public EntityRepositoryForProduct productRepository;

    private Brand brand;
    private Product product;

    public void execute(NewProductRequest request){
        findBrand(request.getBrandId());
        convertReceivedProductDataFormIntoProduct(request);
        productRepository.sendToDatabase(product);
    }

    private void findBrand(Long brandId) {
        this.brand = brandRepository.findBrandById(brandId);
    }

    private void convertReceivedProductDataFormIntoProduct(NewProductRequest newProductRequest) {

        this.product = new Product(newProductRequest.getName(), brand, newProductRequest.getDescription(),
                newProductRequest.getPrice(), PackageUnit.valueOf(newProductRequest.getUnit()));

    }

}
