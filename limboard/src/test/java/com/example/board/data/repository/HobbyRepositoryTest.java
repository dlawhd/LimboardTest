package com.example.board.data.repository;

import com.example.data.entity.Board;
import com.example.data.entity.Hobby;
import com.example.data.repository.BoardRepository;
import com.example.data.repository.HobbyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HobbyRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    HobbyRepository hobbyRepository;

    @Test
    void relationshipTest1() {

        Hobby hobby = new Hobby();
        hobby.setHobby("알고리즘 문제풀이");

        hobby = hobbyRepository.save(hobby); // 저장 후 ID를 가져옴

        Board board = new Board();
        board.setName("최멋사");
        board.setEmail("babylion@likelion.orgg");
        board.setNumber("010-1234-5678");
        board.setPassword("1234");
        board.setProfile("pic");
        board.setHobby(hobby); // Hobby 엔티티의 ID 설정

        boardRepository.save(board);

        System.out.println(
                "babylion : " + boardRepository.findById(board.getPid()).orElseThrow(RuntimeException::new));

        System.out.println("hobby : " + boardRepository.findById(board.getPid()).orElseThrow(RuntimeException::new).getHobby());
    }

    @Test
    void relationshipTest() {
        // 테스트 데이터 생성
        Hobby hobby = new Hobby();
        hobby.setHobby("운동");

        hobbyRepository.save(hobby);

        Board board1 = new Board();
        board1.setName("임종현");
        board1.setEmail("babylions@likelion.org");
        board1.setNumber("010-1234-5670");
        board1.setPassword("12345");
        board1.setProfile("pic0");
        board1.setHobby(hobby);


        Board board2 = new Board();
        board2.setName("종현임");
        board2.setEmail("lion@likelion.org");
        board2.setNumber("010-1111-1111");
        board2.setPassword("0987");
        board2.setProfile("pic1");
        board2.setHobby(hobby);

        Board board3 = new Board();
        board3.setName("종현갓");
        board3.setEmail("babylion@likelion.org");
        board3.setNumber("010-0987-6543");
        board3.setPassword("4567");
        board3.setProfile("pic2");
        board3.setHobby(hobby);

        boardRepository.save(board1);
        boardRepository.save(board2);
        boardRepository.save(board3);

        System.out.println("check 1");

        List<Board> boards = hobbyRepository.findById(hobby.getId()).get()
                .getBoardList();

        for (Board board : boards) {
            System.out.println(board);
        }

    }

}
