package com.example.controller;

import com.example.data.dto.request.ChangeNameRequestDto;
import com.example.data.dto.request.RequestBoardDto;
import com.example.data.dto.response.ResponseBoardDto;
import com.example.service.BoardService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dlawhd-gus")
public class BoardController {
    private final BoardService boardService;


    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseBoardDto> getBoard(@RequestParam String email) throws Exception {
        ResponseBoardDto responseBoardDto = boardService.getBoard(email);

        return ResponseEntity.status(HttpStatus.OK).body(responseBoardDto);
    }

    @ApiImplicitParam(name = "X-AUTH-TOKEN",value="로그인 성공 후 발급 받은 access_token", required = true, dataType ="String",paramType = "header")
    @PostMapping("/post")
    public ResponseEntity<ResponseBoardDto> createBoard(@RequestBody RequestBoardDto requestBoardDto){
        ResponseBoardDto responseBoardDto = boardService.saveBoard(requestBoardDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseBoardDto);
    }

    @ApiImplicitParam(name = "X-AUTH-TOKEN",value="로그인 성공 후 발급 받은 access_token", required = true, dataType ="String",paramType = "header")
    @PutMapping("/put")
    public ResponseEntity<ResponseBoardDto> changeBoardName(@RequestBody ChangeNameRequestDto changeNameRequestDto) throws Exception {
        ResponseBoardDto responseBoardDto = boardService.changeBoardName(changeNameRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseBoardDto);
    }

    @ApiImplicitParam(name = "X-AUTH-TOKEN",value="로그인 성공 후 발급 받은 access_token", required = true, dataType ="String",paramType = "header")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBoard(String email) throws Exception {
        boardService.deleteBoard(email);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}