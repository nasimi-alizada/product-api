package com.example.marketapijpa.dao.repository;

import com.example.marketapijpa.dao.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

}
