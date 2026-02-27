package com.study.Ex1001ExampleCounterDB;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="count")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="count_no")
    private Long countNo;
    @Column(name="count")
    private Long count;

    public void updateCount(Long count){
        this.count = count;
    }
}
