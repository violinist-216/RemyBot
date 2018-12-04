package com.example.remy.repositories;

import com.example.remy.entities.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
