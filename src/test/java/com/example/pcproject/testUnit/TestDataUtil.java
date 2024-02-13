package com.example.pcproject.testUnit;

import com.example.pcproject.repository.*;
import com.example.pcproject.models.entity.*;
import com.example.pcproject.models.eunums.ComputerType;
import com.example.pcproject.models.eunums.RoleType;
import com.example.pcproject.models.eunums.TracesOfUse;
import com.example.pcproject.models.eunums.TypeToUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class TestDataUtil {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    public User createTestUser(String username) {
        addToUserRole();

        List<UserRole> roles = userRoleRepository.findAllByRolesIn(List.of(RoleType.USER, RoleType.MODERATOR));
        User user = new User();
        user.setFirstName("name");
        user.setLastName("namev");
        user.setEmail("email@email");
        user.setUsername(username);
        user.setPassword("12345");
        user.setRoles(roles);
        user.setCreateOn(LocalDate.now());

        return userRepository.save(user);
    }

    public User createTestUserAdmin(String username) {
        addToUserRole();

        List<UserRole> roles = userRoleRepository.findAll();
        User user = new User();
        user.setFirstName("name");
        user.setLastName("namev");
        user.setEmail("email@email");
        user.setUsername(username);
        user.setPassword("12345");
        user.setRoles(roles);
        user.setCreateOn(LocalDate.now());

        return userRepository.save(user);

    }


    public Product createProductTest(User owner) {
        createBrand();
        createModel();
        Brand brand = brandRepository.findByName("testBrand");
        Model model = modelRepository.findByName("testModel");


        Product product = new Product();
        product.setComputerType(ComputerType.COMPUTER);
        product.setModel(model);
        product.setDescription("description test");
        product.setCreated(LocalDateTime.now());
        product.setImageUrl("url");
        product.setPhoneNumber("1234566778");
        product.setSeller(owner);
        product.setPrice(BigDecimal.valueOf(200));
        product.setTracesOfUse(TracesOfUse.B);
        product.setTypeToUse(TypeToUse.OFFICE);
        product.setYear(2022);
        System.out.println();
        return productRepository.save(product);
    }

    public void createModel() {
        Brand byName = brandRepository.findByName("testBrand");
        Model model = new Model();
        model.setBrand(byName);
        model.setName("testModel");
        model.setComputerType(ComputerType.COMPUTER);
        model.setImageUrl("modelImageUrl");
        model.setStartYear(2020);
        model.setCreated(LocalDateTime.now());
        modelRepository.save(model);
    }

    public void createBrand() {
        Brand brand = new Brand();
        brand.setName("testBrand");
        brand.setCreated(LocalDateTime.now());
        brandRepository.save(brand);
    }

    public void addToUserRole() {
        if (userRoleRepository.count() == 0) {
            userRoleRepository.saveAll(List.of(
                    new UserRole().setRoles(RoleType.ADMIN),
                    new UserRole().setRoles(RoleType.MODERATOR),
                    new UserRole().setRoles(RoleType.USER)

            ));
        }
    }

    public void clearAllData() {
        productRepository.deleteAll();
        userRepository.deleteAll();
        brandRepository.deleteAll();
    }
}
