package com.study.Ex1002ExampleCalcDB;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final CalcService calcService;

    @GetMapping("/")
    public String main(){
        return "index";
    }

    @PostMapping("/")
    public String calc(CalcDto calcDto){
        System.out.println(calcDto.getInput1());
        System.out.println(calcDto.getInput2());

        return "index";
    }

    @PostMapping("/add")
    public String add(CalcDto calcDto, Model model){
        System.out.println(calcDto.getInput1());
        System.out.println(calcDto.getInput2());

        int addResult = calcDto.getInput1() + calcDto.getInput2();

        model.addAttribute("input1", calcDto.getInput1());
        model.addAttribute("input2", calcDto.getInput2());
        model.addAttribute("result", addResult);

        // Service에서 DTO를 받는데 Controller에 또 DTO를 전달하는 이유 = 계층 분리 + 역할 분리
        // Controller가 DTO를 받는 이유 = 요청 받는 역할로 자동 DTO에 매핑됨
        // Service가 DTO를 또 받는 이유 = 받은 데이터를 그대로 넘겨주는 것
        // 즉, Controller가 Service에 Dto를 전달해주지 않으면 Service는 DTO를 못 받음
        calcService.add(calcDto);

        return "index";
    }

    @PostMapping("/sub")
    public String sub(CalcDto calcDto, Model model){
        int subResult = calcDto.getInput1() - calcDto.getInput2();

        model.addAttribute("input1", calcDto.getInput1());
        model.addAttribute("input2", calcDto.getInput2());
        model.addAttribute("result", subResult);

        calcService.sub(calcDto);

        return "index";
    }

    @PostMapping("/mul")
    public String mul(CalcDto calcDto, Model model){
        int mulResult = calcDto.getInput1() * calcDto.getInput2();

        model.addAttribute("input1", calcDto.getInput1());
        model.addAttribute("input2", calcDto.getInput2());
        model.addAttribute("result", mulResult);

        calcService.mul(calcDto);

        return "index";
    }

    @PostMapping("/div")
    public String div(CalcDto calcDto, Model model){
        int divResult = calcDto.getInput1() / calcDto.getInput2();

        model.addAttribute("input1", calcDto.getInput1());
        model.addAttribute("input2", calcDto.getInput2());
        model.addAttribute("result", divResult);

        calcService.div(calcDto);

        return "index";
    }
}
