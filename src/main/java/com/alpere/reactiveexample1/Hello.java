package com.alpere.reactiveexample1;

import lombok.Data;

import java.beans.ConstructorProperties;

@Data
public class Hello {
    @ConstructorProperties("name")
    Hello(String name) {
        this.name = name;
    }
    private final String name;
}
