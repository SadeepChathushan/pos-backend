package com.aasait.pos.backend.repository;

import com.aasait.pos.backend.entity.Item;
import com.aasait.pos.backend.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item,Long> {

    boolean existsByName(String name);
}
