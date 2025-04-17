package com.katem.numbertransform.controller;

import com.katem.numbertransform.service.TransformerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FooBarQuixController {
    private final TransformerService transformerService;

    public FooBarQuixController(TransformerService transformerService) {
        this.transformerService = transformerService;
    }

    @GetMapping("/transform/{number}")
    public ResponseEntity<String> transform(@PathVariable int number) {
        return ResponseEntity.ok(transformerService.transform(number));
    }
}
