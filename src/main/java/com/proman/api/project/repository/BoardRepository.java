package com.proman.api.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proman.api.project.model.Board;

public interface BoardRepository extends JpaRepository<Board,Integer> {

}
