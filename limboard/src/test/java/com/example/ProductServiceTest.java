package com.example;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.example.data.dto.request.RequestBoardDto;
import com.example.data.dto.response.ResponseBoardDto;
import com.example.data.entity.Board;
import com.example.data.repository.BoardRepository;
import com.example.service.Impl.BoardServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProductServiceTest {

    private BoardRepository boardRepository = Mockito.mock(BoardRepository.class);
    private BoardServiceImpl boardService;

    @BeforeEach
    public void setUpTest() {
        boardService = new BoardServiceImpl(boardRepository);
    }

    @Test
    void saveProductTest() {
        // given
        Board savedBoard = new Board();
        savedBoard.setName("펜");
        savedBoard.setNumber("1000");
        savedBoard.setPassword("1234");
        savedBoard.setEmail("naver");
        savedBoard.setProfile("Admin");

        Mockito.when(boardRepository.save(any(Board.class)))
                .thenReturn(savedBoard); // 수정

        // when
        ResponseBoardDto responseBoardDto = boardService.saveBoard(
                new RequestBoardDto("펜", "1000", "1234", "naver", "Admin"));

        // then
        Assertions.assertEquals(responseBoardDto.getName(), "펜");
        Assertions.assertEquals(responseBoardDto.getNumber(), "1000");
        Assertions.assertEquals(responseBoardDto.getPassword(), "1234");
        Assertions.assertEquals(responseBoardDto.getEmail(), "naver");
        Assertions.assertEquals(responseBoardDto.getProfile(), "Admin");

        verify(boardRepository).save(any());
    }
}
