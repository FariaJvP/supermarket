package com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.brand;

import com.sm.supermarket.productandinventory.entities.brand.Brand;
import com.sm.supermarket.productandinventory.entities.brand.EntityRepositoryForBrand;
import com.sm.supermarket.productandinventory.infrastructure.springdatarepositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BrandRepositoryUsingSpringData implements EntityRepositoryForBrand {

    @Autowired
    public BrandRepository brandRepository;

    @Override
    public Brand findBrandById(Long brandId) {
        Optional<Brand> brand = brandRepository.findById(brandId);
        if(!brand.isPresent()){
            throw new BrandNotFoundException("could not find a valid Brand in database with the id: id " + brandId);
        }else {
            return brand.get();
        }
    }
}
