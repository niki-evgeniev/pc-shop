package com.example.pcproject.Service;

import com.example.pcproject.models.bindingModels.ProductAllBindingModel;
import com.example.pcproject.models.bindingModels.ProductBindingModel;
import com.example.pcproject.models.bindingModels.ProductDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ProductService {

    boolean addProduct(ProductBindingModel productBindingModel, UserDetails user);

    Page<ProductAllBindingModel> getAllProduct(Pageable pageable);

    Optional<ProductDetailsDTO> getDetails(Long id);

    void cleanExpiredProduct();

    void soldProduct(Long id);
}
