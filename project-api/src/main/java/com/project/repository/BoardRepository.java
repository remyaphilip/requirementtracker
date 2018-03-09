package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Board;

public interface BoardRepository extends JpaRepository<Board,Integer> {

}
