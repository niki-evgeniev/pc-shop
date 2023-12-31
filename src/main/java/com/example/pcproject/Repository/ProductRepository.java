package com.example.pcproject.Repository;

import com.example.pcproject.models.entity.Product;
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

    Page<Product> findAllByComputerType(ComputerType computerType, Pageable pageable);
}
