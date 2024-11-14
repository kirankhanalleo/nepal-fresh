package com.nepalfresh.app.service.impl;

import com.nepalfresh.app.mapper.SignUpMapper;
import com.nepalfresh.app.service.SignUpService;
import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.dto.request.SignUpRequest;
import com.nepalfresh.common.util.ResponseUtil;
import com.nepalfresh.entity.Customer;
import com.nepalfresh.repository.customer.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    private final CustomerRepository customerRepository;
    private final SignUpMapper signUpMapper;
    @Transactional
    @Override
    public Mono<ApiResponse> signUp(SignUpRequest signUpRequest) {
        Optional<Customer> customerByEmail = customerRepository.findByEmail(signUpRequest.getEmail());
        if(customerByEmail.isPresent()){
            return Mono.just(ResponseUtil.getFailureResponse("User with this email already exists."));
        }
        if(!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())){
            return Mono.just(ResponseUtil.getFailureResponse("Password and confirm password field does not match."));
        }
        signUpMapper.mapToSignUp(signUpRequest);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Your account has been created successfully."));
    }
}
