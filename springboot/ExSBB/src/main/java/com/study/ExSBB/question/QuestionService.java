package com.study.ExSBB.question;

import com.study.ExSBB.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//서비스 역할
//1. 리포지터리의 메서드 호출 기능을 서비스에 만들고 컨트롤러에서 이 서비스를 호출하여 사용
//  -> 즉, 서비스를 사용하면 모듈화가 가능
//2. 엔티티 객체를 DTO 객체로 변환하여 사용
//  -> 엔티티 객체에는 민감한 데이터가 포함될 수 있는데, 타임리프에 엔티티 객체를 직접 사용하면 민감한 데이터가 노출될 위험이 있음
//  -> 즉, 서비스는 컨트롤러와 리포지터리의 중간에서 엔티티 객체와 DTO 객체를 서로 변환하여 양방향에 전달하는 역할을 함

@Service //스프링에서 데이터 처리를 위해 작성하는 클래스
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    //Question 엔티티 모든 데이터 조회
    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    //Question 엔티티에서 id값이 동일한 데이터만 조회
    public Question getQuestion(Integer id) {
        Optional<Question> optional = questionRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

}
