package com.example.pcproject.service;

import com.example.pcproject.models.DTO.product.ProductAllDTO;
import com.example.pcproject.models.DTO.product.ProductDTO;
import com.example.pcproject.models.DTO.product.ProductDetailsDTO;
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

    Page<ProductAllDTO> getAllLaptop(Pageable pageable);

    Page<ProductAllDTO> getAllComputer(Pageable pageable);

    Page<ProductAllDTO> searchModel(Pageable pageable, String model);
}
