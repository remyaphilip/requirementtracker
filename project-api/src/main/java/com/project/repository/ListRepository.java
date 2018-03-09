package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.List;

public interface ListRepository extends JpaRepository<List, Integer> {

}
