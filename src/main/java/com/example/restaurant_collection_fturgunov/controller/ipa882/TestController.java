package com.example.restaurant_collection_fturgunov.controller.ipa882;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "NSP test")
@AllArgsConstructor
@RestController
@RequestMapping("/nsp")
public class TestController {

    @GetMapping("/test")
    @Operation(summary = "test custom auth provider")
    public String testAuth() {
        return "ok";
    }
}