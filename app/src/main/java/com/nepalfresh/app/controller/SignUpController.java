package com.nepalfresh.app.controller;

import com.nepalfresh.app.service.SignUpService;
import com.nepalfresh.common.constant.ApiConstant;
import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.dto.request.SignUpRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.SIGNUP)
@AllArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;
    @PostMapping
    Mono<ApiResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest){
        return signUpService.signUp(signUpRequest);
    }
}
