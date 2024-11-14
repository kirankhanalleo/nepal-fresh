package com.nepalfresh.app.service;

import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.dto.request.SignUpRequest;
import reactor.core.publisher.Mono;

public interface SignUpService {
    Mono<ApiResponse> signUp(SignUpRequest signUpRequest);
}
