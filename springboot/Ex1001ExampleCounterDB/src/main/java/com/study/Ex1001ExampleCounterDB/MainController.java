package com.study.Ex1001ExampleCounterDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private CounterBean counter;
    @Autowired
    private CountRepository countRepository;

    // index - 일반 URL 요청
    @GetMapping("/")
    public String main(Model model){
        // model.addAttribute("count", counter.getCount());

        // DB 리스트
        List<CountEntity> list = countRepository.findAll();
        CountEntity countEntity = list.get(0);
        System.out.println(">>" + countEntity.getCount());

        model.addAttribute("count", countEntity.getCount());

        return "index";
    }
    @GetMapping("/plus")
    public String plus(Model model){
        // counter.setCount(counter.getCount() + 1);
        // model.addAttribute("count", counter.getCount());

        Optional<CountEntity> optional = countRepository.findById(1L); // 값이 있는지 없는지 모르는데 Id가 Long타입에 1인 애를 데려와
        optional.ifPresent((countEntity)->{ // 값이 있으면 entity에서 getter로 count를 가져와
            Long count = countEntity.getCount();
            System.out.println(">> count: " + count);

            countEntity.updateCount(count + 1);
            countRepository.save(countEntity);
        });

        return "redirect:/";
    }
    @GetMapping("/minus")
    public String minus(Model model){
        // counter.setCount(counter.getCount() - 1);
        // model.addAttribute("count", counter.getCount());

        Optional<CountEntity> optional = countRepository.findById(1L);
        optional.ifPresent((countEntity)->{
            Long count = countEntity.getCount();
            System.out.println("<< count: " + count);

            countEntity.updateCount(count - 1);
            countRepository.save(countEntity);
        });

        return "redirect:/";
    }

    // index - fetch(), API 요청
    @GetMapping("/countApi")
    public String countApi(Model model){
        List<CountEntity> list = countRepository.findAll();
        CountEntity countEntity = list.get(0);

        model.addAttribute("count", countEntity.getCount());

        return "countApi";
    }
    @GetMapping("/api/plus")
    @ResponseBody
    public String apiPlus(){
        // counter.setCount(counter.getCount() + 1);

        CountEntity countEntity = countRepository.findById(1L).get();
        Long count = countEntity.getCount();
        System.out.println(count);

        countEntity.updateCount(count + 1L);
        countRepository.save(countEntity);

        return String.valueOf(count + 1L);
    }
    @GetMapping("/api/minus")
    @ResponseBody
    public String apiMinus(){
        // counter.setCount(counter.getCount() - 1);

        CountEntity countEntity = countRepository.findById(1L).get();
        Long count = countEntity.getCount();
        System.out.println(count);

        countEntity.updateCount(count - 1L);
        countRepository.save(countEntity);

        return String.valueOf(count - 1L);
    }

    // count - JS 버전
    @GetMapping("/count")
    public String count(){
        return "count";
    }
}
