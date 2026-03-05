package com.study.Ex1002ExampleCalcDB;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="history")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalcEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="history_no")
    private Long historyNo;
    @Column(name="op")
    private String op;
    @Column(name="input1")
    private int input1;
    @Column(name="input2")
    private int input2;
    @Column(name="result")
    private int result;

    public void updateCalc(String op, int input1, int input2, int result){
        this.op = op;
        this.input1 = input1;
        this.input2 = input2;
        this.result = result;
    }
}
