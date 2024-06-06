package com.example.board.data.repository;

import com.example.data.entity.Board;
import com.example.data.entity.Group;
import com.example.data.repository.BoardRepository;
import com.example.data.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GroupRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    GroupRepository groupRepository;

    @Test
    void relationshipTest(){
        // 테스트 데이터 생성
        Board board = new Board();
        board.setName("임종현");
        board.setEmail("kangnam@likelion.org");
        board.setNumber("010-1234-5678");
        board.setPassword("1234");
        board.setProfile("none");


        boardRepository.save(board);

        Group group = new Group();
        group.setGroupName("멋쟁이사자처럼");
        group.getMembers().add(board);

        groupRepository.save(group);

        List<Board> members = groupRepository.findById(1L).get().getMembers();

        for(Board foundBoard : members){
            System.out.println(board);
        }
    }
}