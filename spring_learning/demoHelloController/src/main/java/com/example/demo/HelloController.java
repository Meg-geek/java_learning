package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    /*
    @GetMapping({"/hello"})
    public String hello(){
        return "Hello!!!";
    }

     */

    @PostMapping({"/hello"})
    public void hello(@RequestBody Model model){
        String text = model.getText();
    }

    @GetMapping({"/hello/{text}"})
    public Model hello(@PathVariable String text){
        return new Model(text, text.length());
    }

    static class Model{
        private String text;
        private int length;

        Model(String text, int length){
            this.text = text;
            this.length = length;
        }

        public String getText(){
            return text;
        }

        public int getLength(){
            return length;
        }

        public void setText(String newText){
            text = newText;
        }

        public void setLength(int newLength){
            length = newLength;
        }
    }
}
