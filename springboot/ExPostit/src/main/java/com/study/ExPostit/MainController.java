package com.study.ExPostit;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final PostitRepository postitRepository;

    @GetMapping("/")
    public String main() {
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Postit> list = postitRepository.findAll();

        for (Postit postit : list) {
            System.out.println(">> X좌표: " + postit.getPosX());
            System.out.println(">> Y좌표: " + postit.getPosY());
        }

        model.addAttribute("lists", list);

        return "index";
    }

    // 빈노트 생성
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Postit> add(@RequestBody RequestDto requestDto) {
        Postit entity = Postit.builder()
                .posX(requestDto.getPosX())
                .posY(requestDto.getPosY())
                .zIndex(requestDto.getZIndex())
                .rotation(requestDto.getRotation())
                .build();

        Postit saved = postitRepository.save(entity);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/delete")
    @ResponseBody
    public void delete(@RequestParam Long id) {  // ← @RequestParam 추가
        postitRepository.deleteById(id);
    }

    @GetMapping("/deleteAll")
    @ResponseBody  // ← 추가
    public void deleteAll() {
        postitRepository.deleteAll();
    }

    @PostMapping("/update")
    @ResponseBody  // ← 추가
    public void update(@RequestParam Long id, @RequestBody RequestDto requestDto) {
        Optional<Postit> optional = postitRepository.findById(id);
        optional.ifPresent(postit -> {
            postit.setContent(requestDto.getContent());
            postit.setColor(requestDto.getColor());
            postit.setPosX(requestDto.getPosX());
            postit.setPosY(requestDto.getPosY());
            postit.setZIndex(requestDto.getZIndex());
            postit.setRotation(requestDto.getRotation());
            postitRepository.save(postit);
        });
    }
}
