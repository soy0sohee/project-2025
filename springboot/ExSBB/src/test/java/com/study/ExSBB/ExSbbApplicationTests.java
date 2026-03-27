package com.study.ExSBB;

import com.study.ExSBB.answer.AnswerRepository;
import com.study.ExSBB.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ExSbbApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	@Transactional
	@Test
	void testJpa() {
		//Question Entity 질문 삽입 + Repository 저장
//		Question q1 = new Question();
//		q1.setSubject("sbb가 무엇인가요?");
//		q1.setContent("sbb에 대해서 알고 싶습니다.");
//		q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1);
//		System.out.println(">> 첫번째 성공");
//
//		Question q2 = new Question();
//		q2.setSubject("스프링부트 모델 질문입니다.");
//		q2.setContent("id는 자동으로 생성되나요?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2);
//		System.out.println(">> 두번째 성공");

		//findAll 메서드: 전체 데이터 조회
//		List<Question> list = this.questionRepository.findAll();
		//데이터 갯수 확인
//		assertEquals(2, all.size());
//		System.out.println(">> findAll 성공");

		//all.get(): 전체 데이터 조회
//		Question question = all.get(0);
//		assertEquals("sbb가 무엇인가요?", q.getSubject());
//		System.out.println(">> 내용 일치");

		//findById 메서드 : 기본키 id의 값으로 데이터 조회
//		Optional<Question> optional = this.questionRepository.findById(1);
//		if(optional.isPresent()){
//			Question question = optional.get();
//			assertEquals("sbb가 무엇인가요?", question.getSubject());
//		}
//		System.out.println(">> findById 성공");
		
		//findBySubject 메서드 : subject 값으로 데이터 조회
//		Question question = this.questionRepository.findBySubject("sbb가 무엇인가요?");
//		assertEquals(1, question.getId());
//		System.out.println(">> findBySubject 성공");

		//findBySubjectAndContent 메서드 : subject && content 값으로 데이터 조회
//		Question question = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
//		assertEquals(1, question.getId());
//		System.out.println(">> findBySubjectAndContent 성공");

		//findBySubjectLike 메서드: 특정 문자열을 포함하는 데이터 조회
//		List<Question> list = this.questionRepository.findBySubjectLike("sbb%");
//		Question question = list.get(0);
//		assertEquals("sbb가 무엇인가요?", question.getSubject());
//		System.out.println(">> findBySubjectLike 성공");

		//질문 데이터 수정
//		Optional<Question> optional = this.questionRepository.findById(1);
//		assertTrue(optional.isPresent());
//		Question question = optional.get();
//		question.setSubject("수정된 제목");
//		this.questionRepository.save(question);
//		System.out.println(">> 데이터 수정 성공");

		//질문 데이터 삭제
//		assertEquals(2, this.questionRepository.count());
//		Optional<Question> optional = this.questionRepository.findById(1);
//		assertTrue(optional.isPresent());
//		Question question = optional.get();
//		this.questionRepository.delete(question);
//		assertEquals(1, this.questionRepository.count());
//		System.out.println(">> 데이터 삭제 성공");
		
		//답변 데이터 저장
//		Optional<Question> optional = this.questionRepository.findById(2);
//		assertTrue(optional.isPresent());
//		Question question = optional.get();
//
//		Answer answer = new Answer();
//		answer.setContent("네, 자동으로 생성됩니다.");
//		answer.setQuestion(question);
//		answer.setCreateDate(LocalDateTime.now());
//		this.answerRepository.save(answer);
//		System.out.println(">> 답변 데이터 추가 성공");

		//답변 데이터 조회
//		Optional<Answer> optional = this.answerRepository.findById(1);
//		assertTrue(optional.isPresent());
//		Answer answer = optional.get();
//		assertEquals(2, answer.getQuestion().getId());
//		System.out.println(">> 답변 데이터 조회 성공");

		//외래키로 데이터 찾기 -> 외래키에 접근 할 때는 @Transactional 필수
//		Optional<Question> optional = this.questionRepository.findById(2);
//		assertTrue(optional.isPresent());
//		Question question = optional.get();
//		List<Answer> answerList = question.getAnswerList();
//		assertEquals(1, answerList.size());
//		assertEquals("네, 자동으로 생성됩니다.", answerList.get(0).getContent());
//		System.out.println(">> 데이터 조회 성공");
	}
}
