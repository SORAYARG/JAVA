package com.example.block6pathvariableheaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class GreetingController {

    // Petición POST
    @PostMapping("/post")
    public Greeting postRequest(@RequestBody Greeting greeting) {
        return greeting;
    }

    // Petición GET
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable String id) {
        return id;
    }

    // Petición PUT
    @PutMapping("/put")
    public HashMap<String, String> putRequest(@RequestParam String var1, @RequestParam String var2) {
        HashMap<String, String> data = new HashMap<>();
        data.put("var1", var1);
        data.put("var2", var2);
        return data;
    }
}
