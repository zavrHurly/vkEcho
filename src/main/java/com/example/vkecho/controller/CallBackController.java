package com.example.vkecho.controller;

import com.example.vkecho.dto.CallBackDto;
import com.example.vkecho.service.CallBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class CallBackController {
    private final CallBackService callBackService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> handleCallback(@RequestBody CallBackDto callBackDto) {
        return new ResponseEntity<>(callBackService.handleCallback(callBackDto), HttpStatus.OK);
    }
}
