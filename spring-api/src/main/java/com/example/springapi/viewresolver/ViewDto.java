package com.example.springapi.viewresolver;

public class ViewDto {

    private Long id;
    private String name;

    public ViewDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
