package com.study.ExReviewWebApp.service;

import com.study.ExReviewWebApp.dto.FileDto;
import com.study.ExReviewWebApp.dto.PostRequestDto;
import com.study.ExReviewWebApp.dto.PostResponseDto;
import com.study.ExReviewWebApp.entity.Post;
import com.study.ExReviewWebApp.entity.PostFile;
import com.study.ExReviewWebApp.repository.PostFileRepository;
import com.study.ExReviewWebApp.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostFileRepository postFileRepository;

    @Transactional(readOnly = true)
    public List<PostResponseDto> list() {
        List<Post> postList = postRepository.findAll();
        return postList.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void create(PostRequestDto requestDto, MultipartFile[] fileUpload) throws IOException {
        Post post = Post.builder()
                .id(requestDto.getId())
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .rating(requestDto.getRating())
                .dateTime(LocalDateTime.now())
                .build();

        postRepository.save(post);

        //업로드 경로 설정
        String uploadDir = System.getProperty("user.dir") + "/uploads/";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        System.out.println("저장 경로: " + uploadDir);  // ✅ 경로 확인
        System.out.println("폴더 존재 여부: " + dir.exists());  // ✅ 폴더 생성 확인

        for(MultipartFile file : fileUpload) {
            if (!file.isEmpty()) {
                //같은 uuid 사용
                String uuid = UUID.randomUUID().toString();

                //실제 저장 파일명
                String savedFileName = uuid + "_" + file.getOriginalFilename();

                //DTO에 모든 정보 담기
                FileDto dto = FileDto.builder()
                        .uuid(uuid) //중복방지 고유값
                        .fileName(file.getOriginalFilename()) //원본 파일명
                        .contentType(file.getContentType()) //파일형식
                        .filePath(uploadDir + savedFileName)
                        .build();

                //물리적 파일 저장
                File newFile = new File(dto.getFilePath());
                file.transferTo(newFile);

                System.out.println("파일 저장 완료: " + newFile.getAbsolutePath());  // ✅ 실제 저장 위치 확인

                //DB에 실제 파일명 저장
                PostFile postFile = PostFile.builder()
                        .fileName(savedFileName)
                        .post(post)
                        .build();

                postFileRepository.save(postFile);
            }
        }
    }

    @Transactional(readOnly = true)
    public PostResponseDto postDetail(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalAccessError("회원 없음"));
        return new PostResponseDto(post);
    }
}
