package com.enigma.enigmaboot.controller;

import com.enigma.enigmaboot.entity.Hello;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
public class HelloController {
    @RequestMapping("/hello")
    //Method hanya dibuat private ketika hanya dijalankan di kelas tersebut saja
    //localhost:8080/hello
    //Jika ada url yang sama maka akan terjadi bad request
    public String hello(){
        return "Hello world";
    }

    //localhost:8080/hello/name
    @GetMapping("/hello/{name}")
    public String getName(@PathVariable String name){
        return "Hello " + name;
    }

    //localhost:8080/queryStringName?name=Erika
    @GetMapping("/queryStringName")
    public String helloName(@RequestParam String name){
        return "Hello " + name;
    }

    @PostMapping("/reqBody")
    public Hello getUserName(@RequestBody Hello username){
        return username;
    }
}
