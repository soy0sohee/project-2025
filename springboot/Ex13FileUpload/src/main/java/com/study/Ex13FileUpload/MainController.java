package com.study.Ex13FileUpload;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(){
        return "upload";
    }

    @PostMapping("/upload")
    // @RequestParam -> input태그에 name="" 중요
    public String upload(@RequestParam MultipartFile[] uploadfile,
                         Model model) throws IOException {
        List<FileDto> list = new ArrayList<>();
        
        for(MultipartFile file : uploadfile){
            if(!file.isEmpty()){
                // fileDto 생성 - builder 패턴
                FileDto dto = FileDto.builder()
                        .uuid(UUID.randomUUID().toString()) // UUID - 중복 안되게 파일 이름 만들기 위해
                        .fileName(file.getOriginalFilename()) // 사용자가 선택한 원래 파일 이름
                        .contentTypes(file.getContentType()) // image/png 확장자 타입
                        .build();
                list.add(dto); // ArrayList에 추가
                // 물리적으로 File 생성하기 - transferTo
                File newFilename = new File(dto.getUuid() + "_" + dto.getFileName());
                file.transferTo(newFilename);
                // DB에 파일경로 + 이름 기록
            }
        }
        model.addAttribute("files", list);
        return "result";
    }
}
