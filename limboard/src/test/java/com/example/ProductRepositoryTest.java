package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;


import com.example.data.entity.Board;
import com.example.data.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void save(){
        Board board = new Board();
        board.setName("펜");
        board.setNumber("1000");
        board.setPassword("1234");
        board.setEmail("naver");
        board.setProfile("Admin");

        Board savedBoard = boardRepository.save(board);

        assertEquals(board.getName(), savedBoard.getName());
        assertEquals(board.getNumber(), savedBoard.getNumber());
        assertEquals(board.getPassword(), savedBoard.getPassword());
        assertEquals(board.getEmail(), savedBoard.getEmail());
        assertEquals(board.getProfile(), savedBoard.getProfile());
    }
}
