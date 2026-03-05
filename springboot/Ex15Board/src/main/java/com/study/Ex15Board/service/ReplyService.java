package com.study.Ex15Board.service;

import com.study.Ex15Board.domain.reply.Reply;
import com.study.Ex15Board.domain.reply.ReplyRepository;
import com.study.Ex15Board.dto.ReplyResponseDto;
import com.study.Ex15Board.dto.ReplySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    
    // 댓글 목록 조회
    // 게시글 DB인덱스가 같은 댓글 레코드 가져옴
    @Transactional(readOnly = true)
    public List<ReplyResponseDto> findAllByReplyBoardIdx(Long boardIdx){
        List<Reply> list = replyRepository.findAllByReplyBoardIdxOrderByReplyDateDesc(boardIdx);
        // stream() : List<Reply> -> List<DTO>
        return list.stream().map(ReplyResponseDto::new).collect(Collectors.toList());
    }

    //댓글 삭제
    @Transactional
    public void delete(Long replyIdx){
        Reply entity = replyRepository.findById(replyIdx)
                .orElseThrow( () -> new IllegalArgumentException("댓글 인덱스 오류입니다. replyIdx:"+replyIdx) );
        replyRepository.delete( entity) ;
    }

    //댓글 저장
    @Transactional
    public Long save(final ReplySaveRequestDto dto){
        Reply entity = replyRepository.save( dto.toEntity() );
        return entity.getReplyIdx();
    }

    //댓글 있는지 조회
    @Transactional(readOnly = true)
    public boolean existsById(final Long replyIdx) {
        boolean isFound = replyRepository.existsById( replyIdx );

        return isFound;
    }

    //댓글 DB인덱스로 댓글 정보 가져오기
    @Transactional(readOnly = true)
    public ReplyResponseDto findById(Long replyIdx){
        Reply entity = replyRepository.findById(replyIdx).orElseThrow(()->new IllegalArgumentException("댓글이 없습니다."));
        return new ReplyResponseDto(entity);
    }

    //댓글 업데이트
}
