package com.example.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.data.entity.BoardDetail;
public interface BoardDetailRepository extends JpaRepository<BoardDetail, Long> {
}
