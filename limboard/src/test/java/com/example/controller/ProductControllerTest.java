package com.example.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.data.dto.request.RequestBoardDto;
import com.example.data.dto.response.ResponseBoardDto;
import com.example.service.Impl.BoardServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BoardController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BoardServiceImpl boardService;

    @Test
    @DisplayName("MockMvc를 통한 Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception {
        // given
        given(boardService.getBoard("daum")).willReturn(
                new ResponseBoardDto(1L, "pen", "navers", "1001", "1235", "Admins"));

        String boardId = "1234";

        // when, then
        mockMvc.perform(get("/board?number=" + boardId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.password").exists())
                .andExpect(jsonPath("$.profile").exists())
                .andDo(print());

        // verify
        verify(boardService).getBoard("daum");
    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception {
        // given
        given(boardService.saveBoard(any(RequestBoardDto.class)))
                .willReturn(new ResponseBoardDto(12315L, "pen", "test@example.com", "5000", "1234", "Admin"));

        RequestBoardDto boardDto = RequestBoardDto.builder()
                .name("pen")
                .number("5000")
                .password("1234")
                .email("test@example.com")
                .profile("Admin")
                .build();

        Gson gson = new Gson();
        String content = gson.toJson(boardDto);

        // when, then
        mockMvc.perform(post("/board")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.password").exists())
                .andExpect(jsonPath("$.profile").exists())
                .andDo(print());

        // verify
        verify(boardService).saveBoard(any(RequestBoardDto.class));
    }
}
