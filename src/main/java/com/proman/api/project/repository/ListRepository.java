package com.proman.api.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proman.api.project.model.List;

public interface ListRepository extends JpaRepository<List, Integer> {

}
