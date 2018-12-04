package com.example.remy.repositories;

import com.example.remy.entities.Object;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectRepository extends JpaRepository<Object, Long> {
}
