package com.study.Ex17JWT.service.impl;

import com.study.Ex17JWT.dto.UserDto;
import com.study.Ex17JWT.dto.UserRequestDto;
import com.study.Ex17JWT.entity.Users;
import com.study.Ex17JWT.entity.UsersRepository;
import com.study.Ex17JWT.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// 서비스 구현클래스
// 어떻게 할지 실제 구현
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    // 생성자 주입
    // final: 한번 선언되면 재할당 불가(데이터 손실 방지, 안전성 향상)
    private final UsersRepository usersRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입
    @Override
    @Transactional  // 오류 발생시 ROLL_BACK
    public UserDto createUser(UserRequestDto requestDto) {
        // 비밀번호 암호화 BCrypt 사용
        Users entity = Users.builder()
                .email(requestDto.getEmail())
                .password(bCryptPasswordEncoder.encode(requestDto.getPassword()))
                .userRole(requestDto.getUserRole())
                .build();

        // 트렌잭션 내 엔티티 객체가 생성된 상태에서 엔티티의 set함수를 호출하면 자동으로 DB에 저장됨
        // -> 이때 DB 저장이 2번이상 동작하여 오류 발생 가능성이 있음
        // -> 예) entity.setEmail("test@gamil.com") -> DB Commit 됨

        Users newEntity = usersRepo.save(entity);

        UserDto newDto = UserDto.builder()
                .id(newEntity.getId())
                .email(newEntity.getEmail())
                .password(newEntity.getPassword())
                .userRole(newEntity.getUserRole())
                .build();

        return newDto;
    }

    // 사용자 단건 조회
    @Override
    @Transactional(readOnly = true)
    public UserDto findUser(String email) throws Exception {
        Optional<Users> optional = usersRepo.findByEmail(email);

        if (optional.isEmpty()) {
            // 예외 강제 발생
            throw new Exception("이메일에 맞는 회원이 없습니다.");
        }

        Users entity = optional.get();

        return UserDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .userRole(entity.getUserRole())
                .build();
    }

    // 로그인
    @Override
    @Transactional(readOnly = true)
    // 예외처리 방법: try-catch 직접처리, throws 호출하는 메서드에게 넘김(코드가 복잡해지는 것을 방지함)
    // @Override 할 때에는 인터페이스 메서드에도 똑같이 throws Exception이 있어야 함
    public UserDto findByEmailAndPassword(String email, String password) throws Exception {
        Optional<Users> optional = usersRepo.findByEmail(email);

        if (optional.isEmpty()) {
            // 예외 강제 발생
            throw new Exception("이메일에 맞는 회원이 없습니다.");
        }

        Users entity = optional.get();

        // DB에 있는 암호화된 비번과 사용자가 입력한 암호가 같은지 확인
        // BCrypt 알고리즘은 복고화가 불가
        // -> 유저 입력 암호를 암호화하여 DB의 암호와 매칭(match)
        if (bCryptPasswordEncoder.matches(password, entity.getPassword()) == false) {
            throw new Exception("이메일 또는 비밀번호가 다릅니다.");
        }

        return UserDto.builder()
                .email(entity.getEmail())
                .password(entity.getPassword())
                .userRole(entity.getUserRole())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        // Entity -> Dto 클래스 변환
        return usersRepo.findAll()
                .stream()
                .map(entity -> UserDto.builder()
                        .id(entity.getId())
                        .email(entity.getEmail())
                        .password(entity.getPassword())
                        .userRole(entity.getUserRole())
                        .build())
                .collect(Collectors.toList());
    }


}
