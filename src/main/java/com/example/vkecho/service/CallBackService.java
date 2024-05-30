package com.example.vkecho.service;

import com.example.vkecho.dto.CallBackDto;

public interface CallBackService {
    String handleCallback(CallBackDto callBackDto);
}
