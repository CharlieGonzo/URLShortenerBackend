package com.example.urlshort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class URLController {


    @Autowired
    URLRepository rep;

    @GetMapping("/getLong")
    public ResponseEntity<?> redirect(@RequestParam String shortURL){
        String url = rep.getURL(Integer.valueOf(shortURL));
        if(url == null){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(302).location(URI.create(url)).build();
    }

    @PostMapping("/shorten")
    public ResponseEntity<?> createURL(@RequestParam String URL){
        int newID = rep.createURL(URL);
        if(newID == -1 ){
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(201).body(newID);


    }
}
