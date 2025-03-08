package com.inn.fitness.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberWrapper {
    private Integer id;
    private String name;
    private int age;
    private float weight;
    private float height;
    private float bmi;

    public MemberWrapper(Integer id, String name, int age, float weight, float height, float bmi) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
    }

    public MemberWrapper(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
