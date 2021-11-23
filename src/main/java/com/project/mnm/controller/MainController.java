package com.project.mnm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/")
public class MainController {

    @GetMapping("first")
    @ResponseBody
    public String first(Model model) {
        return "first";
    }

    @GetMapping("hello")
    @ResponseBody
    public String hello(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("api")
    @ResponseBody
    public Hello api(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}