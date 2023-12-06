package com.example.pcproject.Repository;

import com.example.pcproject.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    void deleteBySellerId(Long id);

    List<Product> findAllBySellerId(Long seller_id);
}
