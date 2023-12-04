package com.example.pcproject.Service;

import com.example.pcproject.models.bindingModels.ProductAllDTO;
import com.example.pcproject.models.bindingModels.ProductDTO;
import com.example.pcproject.models.bindingModels.ProductDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ProductService {

    boolean addProduct(ProductDTO productDTO, UserDetails user);

    Page<ProductAllDTO> getAllProduct(Pageable pageable);

    Optional<ProductDetailsDTO> getDetails(Long id, UserDetails userDetails);

    void cleanExpiredProduct();

    void soldProduct(Long id);
}
