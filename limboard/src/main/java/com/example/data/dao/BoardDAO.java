package com.example.data.dao;

import com.example.data.entity.Board;

public interface BoardDAO {
    Board insertBoard(Board board);

    Board selectBoard(String email) throws Exception;

    Board updateBoard(String email, String name) throws Exception;

    void deleteBoard(String email) throws Exception;
}
