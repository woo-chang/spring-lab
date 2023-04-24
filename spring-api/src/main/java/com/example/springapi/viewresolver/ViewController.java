package com.example.springapi.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/layout/string")
    public String layoutJsp() {
        System.out.println("call layout jsp");
        return "layout";
    }

    @GetMapping("/layout")
    public void layoutVoid() {
        System.out.println("call layout void");
    }

    @GetMapping("/layout/dto")
    public ViewDto layoutDto() {
        return new ViewDto(1L, "layout");
    }
}
