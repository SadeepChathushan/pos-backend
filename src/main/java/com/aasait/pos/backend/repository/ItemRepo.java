package com.aasait.pos.backend.repository;

import com.aasait.pos.backend.entity.Item;
import com.aasait.pos.backend.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item,Long> {

    boolean existsByName(String name);

    @Query("SELECT I.name FROM Item I")
    List<String> findAllItemNames();
}
