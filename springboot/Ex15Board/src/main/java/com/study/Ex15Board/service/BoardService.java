package com.study.Ex15Board.service;

import com.study.Ex15Board.domain.board.Board;
import com.study.Ex15Board.domain.board.BoardRepository;
import com.study.Ex15Board.dto.BoardResponseDto;
import com.study.Ex15Board.dto.BoardSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 트랜젝션 처리 - 단위작업 처리시 중간에 오류가 발생하면 Rollback 처리
    //               - 정상 처리되면 Commit = 물리적 파일에 기록됨
    // Rollback은 INSERT, UPDATE, DELETE에만 필요, SELECT는 필요없음
    
    // 게시글 전체 목록 읽기
    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAll() {
        List<Board> list = boardRepository.findAll();
        
        // .stream() : List<Board> -> List<DTO> List를 Stream 타입으로 변환
        // .map(BoardResponseDto::new) = .map(board -> new BoardResponseDto(board)) : new 생성자 참조
        // .collect(Collectors.toList()) : map으로 변환된 stream을 다시 List로 변환
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    // 게시글 저장하기
    @Transactional
    public Long save(final BoardSaveRequestDto dto){
        Board entity = boardRepository.save(dto.toEntity());

        return entity.getBoardIdx();
    }

    // 글쓰기 후 실제 게시글이 있는지 확인
    @Transactional(readOnly = true)
    public boolean existsById(Long boardIdx){
        boolean isFound = boardRepository.existsById(boardIdx);

        return isFound;
    }

    // 게시글 DB인덱스로 게시글 정보 가져오기
    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long boardIdx){
        // Optional클래스함수 orElseThrow(람다식) : null이면 람다식 실행
        Board entity = boardRepository.findById(boardIdx).orElseThrow(()->new IllegalArgumentException("게시글 인덱스가 없습니다."));

        return new BoardResponseDto(entity);
    }

    // 조회수 업데이트하기
    @Transactional
    public void updateHit(final Long boardIdx, final Integer hit){
        // BEGIN TRANSACTION

        Board entity = boardRepository.findById(boardIdx).orElseThrow(()->new IllegalArgumentException("게시글 인덱스가 없습니다."));

        // Entity 인스턴스의 멤버 변수를 수정하면, 자동으로 save됨
        // Repository의 save함수를 별도로 사용하지 않아도 됨
        entity.updateHit(hit);

        // COMMIT(ROLLBACK) 자동 발생
    }

    // 게시글 업데이트하기
    @Transactional
    public boolean update(final Long boardIdx, final BoardSaveRequestDto dto){
        Board entity = boardRepository.findById( boardIdx )
                .orElseThrow( () -> new IllegalArgumentException("없는 글인덱스입니다.") ) ;

        entity.update(dto.getBoardName(), dto.getBoardTitle(),
                dto.getBoardContent(), dto.getBoardHit());

        return true;
    }

    // 게시글 지우기
    @Transactional
    public void delete(final Long boardIdx){
        Board entity = boardRepository.findById( boardIdx )
                .orElseThrow( () -> new IllegalArgumentException("없는 글인덱스입니다.") ) ;

        boardRepository.delete(entity);
    }

}