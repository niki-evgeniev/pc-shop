package com.example.pcproject.Service.impl;

import com.example.pcproject.Repository.ModelRepository;
import com.example.pcproject.Repository.ProductRepository;
import com.example.pcproject.Repository.UserRepository;
import com.example.pcproject.Service.ProductService;
import com.example.pcproject.Service.aop.ExecutionTime;
import com.example.pcproject.models.DTO.ProductAllDTO;
import com.example.pcproject.models.DTO.ProductDTO;
import com.example.pcproject.models.DTO.ProductDetailsDTO;
import com.example.pcproject.models.entity.*;
import com.example.pcproject.models.eunums.ComputerType;
import com.example.pcproject.models.eunums.RoleType;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductServiceImpl(ModelMapper modelMapper, ModelRepository modelRepository,
                              ProductRepository productRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean addProduct(ProductDTO productDTO, UserDetails userDetails) {

        Product newProduct = modelMapper.map(productDTO, Product.class);
        Model model = modelRepository.findById(productDTO.getModelId())
                .orElseThrow(() -> new IllegalArgumentException("Model not found"));
        newProduct.setModel(model);

        if (newProduct.getImageUrl().isBlank()) {
            newProduct.setImageUrl(model.getImageUrl());
        }
        newProduct.setComputerType(model.getComputerType());
        newProduct.setCreated(LocalDateTime.now());

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        newProduct.setSeller(user);
        productRepository.save(newProduct);

        return true;
    }

    @ExecutionTime(time = 2000L)
    @Override
    public Page<ProductAllDTO> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductServiceImpl::mapAsSummary);
    }

    @Override
    public Page<ProductAllDTO> getAllLaptop(Pageable pageable) {
        Page<ProductAllDTO> allLaptop = getProductByComputerType(ComputerType.LAPTOP, pageable);
        return allLaptop;
    }

    @Override
    public Page<ProductAllDTO> getAllComputer(Pageable pageable) {
        Page<ProductAllDTO> allComputer = getProductByComputerType(ComputerType.COMPUTER, pageable);
        return allComputer;
    }

    private Page<ProductAllDTO> getProductByComputerType(ComputerType computerType, Pageable pageable) {
        Page<ProductAllDTO> allInfo =
                productRepository.findAllByComputerType(computerType, pageable)
                        .map(ProductServiceImpl::mapAsSummary);
        return allInfo;
    }

    @ExecutionTime(time = 1000L)
    @Override
    public Optional<ProductDetailsDTO> getDetails(Long id, UserDetails userDetails) {
        Optional<ProductDetailsDTO> productDetailsDTO = productRepository.findById(id)
                .map(p -> this.mapAsDetails(p, userDetails));
        return productDetailsDTO;
    }

    @Override
    public void cleanExpiredProduct() {
        List<Product> allProduct = productRepository.findAll();

        for (Product product : allProduct) {
            LocalDateTime created = product.getCreated();
            LocalDateTime expirationDays = created.plusDays(30);
            if (created.isAfter(expirationDays)) {
                productRepository.deleteById(product.getId());

            }
        }
    }

    @Override
    public void soldProduct(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDetailsDTO mapAsDetails(Product product, UserDetails userDetails) {
        ProductDetailsDTO map = modelMapper.map(product, ProductDetailsDTO.class);
        map.setBrand(product.getModel().getBrand().getName());
        map.setModel(product.getModel().getName());
        map.setOwner(isOwner(product, userDetails));
        map.setSeller(product.getSeller().getUsername());
        return map;
    }


    private boolean isOwner(Product product, UserDetails userDetails) {
        if (userDetails == null) {
            return false;
        }
        User users = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User dont exist"));

        if (isAdmin(users)) {
            return true;
        }
        return Objects.equals(product.getSeller().getId(), users.getId());
    }

    private boolean isAdmin(User user) {
        return user.getRoles()
                .stream()
                .map(UserRole::getRoles)
                .anyMatch(r -> RoleType.ADMIN == r);
    }

    private static ProductAllDTO mapAsSummary(Product product) {
        return new ProductAllDTO(
                product.getId().toString(),
                product.getModel().getBrand().getName(),
                product.getModel().getName(),
                product.getYear(),
                product.getPrice(),
                product.getImageUrl(),
                product.getComputerType(),
                product.getTypeToUse());
    }
}

