package com.example.data.repository;

import com.example.data.dao.BoardDAO;
import com.example.data.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardDAO {
    Optional<Board> findByEmail(String email);
}
