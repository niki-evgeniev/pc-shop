package com.example.pcproject.service.impl;

import com.example.pcproject.repository.ModelRepository;
import com.example.pcproject.repository.ProductRepository;
import com.example.pcproject.repository.UserRepository;
import com.example.pcproject.models.DTO.product.ProductDTO;
import com.example.pcproject.models.DTO.product.ProductDetailsDTO;
import com.example.pcproject.models.entity.*;
import com.example.pcproject.models.eunums.ComputerType;
import com.example.pcproject.models.eunums.RoleType;
import com.example.pcproject.models.eunums.TracesOfUse;
import com.example.pcproject.models.eunums.TypeToUse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ModelRepository modelRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        ProductServiceImpl productService = new ProductServiceImpl(
                modelMapper, modelRepository, productRepository, userRepository);
    }

    @Test
    public void testAddProduct() {

        ProductDTO productDTO = getProductDTO();

        Product newProduct = getProduct(productDTO);

        Model model = getModel();

        when(modelMapper.map(productDTO, Product.class)).thenReturn(newProduct);
        when(modelRepository.findById(productDTO.getModelId())).thenReturn(Optional.of(model));

        UserDetails userDetails = getUserDetails();

        Optional<User> user = Optional.of(new User());
        user.get().setUsername("Pesho");

        when(userRepository.findByUsername(userDetails.getUsername())).thenReturn(user);
        ProductServiceImpl productService = new ProductServiceImpl(modelMapper, modelRepository, productRepository, userRepository);

        boolean isAddedProduct = productService.addProduct(productDTO, userDetails);

        Assertions.assertTrue(isAddedProduct);
    }

    @Test
    public void testGetDetails() {
        long id = 1;
        Product productFull = getProductFull(getProductDTO());
        when(productRepository.findById(id)).thenReturn(Optional.of(productFull));
        UserDetails userDetails = getUserDetails();
        User testUser = createTestUser();

        when(userRepository.findByUsername(userDetails.getUsername())).thenReturn(Optional.of(testUser));
        ProductServiceImpl productService = new ProductServiceImpl(modelMapper, modelRepository, productRepository, userRepository);
        Optional<ProductDetailsDTO> details = productService.getDetails(id, getUserDetails());

        Assertions.assertEquals(productFull.getYear(), details.get().getYear());
        Assertions.assertNotNull(details);
        Assertions.assertEquals(productFull.getDescription(), details.get().getDescription());
        Assertions.assertEquals(productFull.getImageUrl(), details.get().getImageUrl());
        Assertions.assertEquals(productFull.getPrice(), details.get().getPrice());
        Assertions.assertEquals(productFull.getYear(), details.get().getYear());
        Assertions.assertEquals(productFull.getPhoneNumber(), details.get().getPhoneNumber());
    }

    private UserDetails getUserDetails() {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return "testUserDetails";
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
    }

    private Model getModel() {
        Model model = new Model();
        model.setName("testModel");
        model.setComputerType(ComputerType.COMPUTER);
        model.setImageUrl("modelImageUrl");
        model.setStartYear(2020);
        model.setCreated(LocalDateTime.now());
        return model;
    }

    private Product getProduct(ProductDTO productDTO) {
        Product newProduct = new Product();
        newProduct.setDescription(productDTO.getDescription());
        newProduct.setImageUrl(productDTO.getImageUrl());
        newProduct.setPhoneNumber(productDTO.getPhoneNumber());
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setYear(productDTO.getYear());
        return newProduct;
    }
    private Product getProductFull(ProductDTO productDTO) {

        User seller = createTestUser();

        Brand brand = new Brand();
        brand.setName("testBrand");
        brand.setCreated(LocalDateTime.now());

        Model model = new Model();
        model.setBrand(brand);
        model.setName("testModel");
        model.setComputerType(ComputerType.COMPUTER);
        model.setImageUrl("modelImageUrl");
        model.setStartYear(2020);
        model.setCreated(LocalDateTime.now());

        Product newProduct = new Product();
        newProduct.setModel(model);
        newProduct.setDescription(productDTO.getDescription());
        newProduct.setImageUrl(productDTO.getImageUrl());
        newProduct.setPhoneNumber(productDTO.getPhoneNumber());
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setYear(productDTO.getYear());
        newProduct.setTypeToUse(TypeToUse.OFFICE);
        newProduct.setTracesOfUse(TracesOfUse.B);
        newProduct.setSeller(seller);
        return newProduct;
    }

    private ProductDTO getProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setTracesOfUse(TracesOfUse.B);
        productDTO.setPrice(BigDecimal.valueOf(200));
        productDTO.setTypeToUse(TypeToUse.OFFICE);
        productDTO.setImageUrl("image");
        productDTO.setPhoneNumber("12345");
        productDTO.setYear(2020);
        productDTO.setDescription("mnooo ubav");
        return productDTO;
    }

    private static User createTestUser(){
        User user = new User();
        user.setFirstName("name");
        user.setLastName("namev");
        user.setEmail("email@email");
        user.setUsername("username");
        user.setPassword("12345");
        user.setRoles(List.of(
                new UserRole().setRoles(RoleType.USER),
                new UserRole().setRoles(RoleType.MODERATOR),
                new UserRole().setRoles(RoleType.ADMIN)
        ));
        return user;
    }

}
