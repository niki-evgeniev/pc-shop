package com.example.pcproject.Repository;

import com.example.pcproject.models.DTO.ProductAllDTO;
import com.example.pcproject.models.entity.Model;
import com.example.pcproject.models.entity.Product;
import com.example.pcproject.models.entity.User;
import com.example.pcproject.models.eunums.ComputerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    void deleteBySellerId(Long id);

    List<Product> findAllBySellerId(Long seller_id);

    Page<Product> findAllByIsSoldIsFalse(Pageable pageable);

    Page<Product> findAllByComputerTypeAndIsSoldIsFalse(ComputerType computerType, Pageable pageable);


    Page<Product> findAllByModelName(String modelName, Pageable pageable);

    Long countBySellerIdAndIsSoldIsFalse(Long seller_id);

    Long countBySellerIdAndIsSoldIsTrue(Long id);
}
