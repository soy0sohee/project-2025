package com.study.Ex1002ExampleCalcDB;

import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CalcService {
    private final CalcRepository calcRepository;

    @Transactional
    public void add(CalcDto calcDto){
        // CalcEntity calcEntity = calcRepository.findById(1L).orElseThrow(()->new RuntimeException("No Data"));  -- 수정
        CalcEntity calcEntity = new CalcEntity();  // -- 생성

        int input1 = calcDto.getInput1();
        int input2 = calcDto.getInput2();
        int result = input1 + input2;

        calcEntity.updateCalc("add", input1, input2, result);
        calcRepository.save(calcEntity);
    }

    @Transactional
    @Builder
    public void sub(CalcDto calcDto){
        // CalcEntity calcEntity = calcRepository.findById(1L).orElseThrow(()->new RuntimeException("No Data")); // -- 있으면 수정 없으면 추가
        // CalcEntity calcEntity = new CalcEntity();  // -- 클래스 생성으로 추가

        int input1 = calcDto.getInput1();
        int input2 = calcDto.getInput2();
        int result = input1 - input2;

        // calcEntity.updateCalc("sub", input1, input2, result);
        CalcEntity calcEntity = CalcEntity.builder()
                .op("sub")
                .input1(input1)
                .input2(input2)
                .result(result)
                .build(); // -- 빌드로 추가
        calcRepository.save(calcEntity);
    }

    @Transactional
    public void mul(CalcDto calcDto){
        // CalcEntity calcEntity = calcRepository.findById(1L).orElseThrow(()->new RuntimeException("No Data"));
        CalcEntity calcEntity = new CalcEntity();  // -- 생성

        int input1 = calcDto.getInput1();
        int input2 = calcDto.getInput2();
        int result = input1 * input2;

        calcEntity.updateCalc("mul", input1, input2, result);
        calcRepository.save(calcEntity);
    }

    @Transactional
    public void div(CalcDto calcDto){
        // CalcEntity calcEntity = calcRepository.findById(1L).orElseThrow(()->new RuntimeException("No Data"));
        CalcEntity calcEntity = new CalcEntity();  // -- 생성

        int input1 = calcDto.getInput1();
        int input2 = calcDto.getInput2();
        int result = input1 / input2;

        calcEntity.updateCalc("div", input1, input2, result);
        calcRepository.save(calcEntity);
    }
}
