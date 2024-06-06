package com.example.service.Impl;

import com.example.data.dao.BoardDAO;
import com.example.data.dto.request.ChangeNameRequestDto;
import com.example.data.dto.request.RequestBoardDto;
import com.example.data.dto.response.ResponseBoardDto;
import com.example.data.entity.Board;
import com.example.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;

    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public ResponseBoardDto getBoard(String email) throws Exception {
        Board board = boardDAO.selectBoard(email);

        ResponseBoardDto responseBoardDto = new ResponseBoardDto();
        responseBoardDto.setPid(board.getPid());
        responseBoardDto.setName(board.getName());
        responseBoardDto.setEmail(board.getEmail());
        responseBoardDto.setNumber(board.getNumber());
        responseBoardDto.setProfile(board.getProfile());
        responseBoardDto.setPassword(board.getPassword());

        return responseBoardDto;
    }

    @Override
    public ResponseBoardDto saveBoard(RequestBoardDto requestBoardDto) {
        Board board = new Board();
        board.setName(requestBoardDto.getName());
        board.setEmail(requestBoardDto.getEmail());
        board.setNumber(requestBoardDto.getNumber());
        board.setPassword(requestBoardDto.getPassword());
        board.setProfile(requestBoardDto.getProfile());
        board.setCreateAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());

        Board savedBoard = boardDAO.insertBoard(board);

        ResponseBoardDto responseBoardDto = new ResponseBoardDto();
        responseBoardDto.setEmail(savedBoard.getEmail());
        responseBoardDto.setName(savedBoard.getName());
        responseBoardDto.setNumber(savedBoard.getNumber());
        responseBoardDto.setProfile(savedBoard.getProfile());
        responseBoardDto.setPassword(savedBoard.getPassword());

        return responseBoardDto;
    }

    @Override
    public ResponseBoardDto changeBoardName(ChangeNameRequestDto changeNameRequestDto) throws Exception {
        Board findBoard = boardDAO.updateBoard(changeNameRequestDto.getEmail(), changeNameRequestDto.getName());

        ResponseBoardDto responseBoardDto = new ResponseBoardDto();
        responseBoardDto.setPid(findBoard.getPid());
        responseBoardDto.setName(findBoard.getName());
        responseBoardDto.setEmail(findBoard.getEmail());
        responseBoardDto.setNumber(findBoard.getNumber());
        responseBoardDto.setPassword(findBoard.getPassword());
        responseBoardDto.setProfile(findBoard.getProfile());

        return responseBoardDto;
    }

    @Override
    public void deleteBoard(String email) throws Exception {
        boardDAO.deleteBoard(email);
    }
}