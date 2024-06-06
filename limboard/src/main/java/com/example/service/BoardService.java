package com.example.service;

import com.example.data.dto.request.ChangeNameRequestDto;
import com.example.data.dto.request.RequestBoardDto;
import com.example.data.dto.response.ResponseBoardDto;

public interface BoardService {
    ResponseBoardDto getBoard(String email) throws Exception;

    ResponseBoardDto saveBoard(RequestBoardDto requestBoardDto);

    ResponseBoardDto changeBoardName(ChangeNameRequestDto changeNameRequestDto) throws Exception;

    void deleteBoard(String email) throws Exception;
}
