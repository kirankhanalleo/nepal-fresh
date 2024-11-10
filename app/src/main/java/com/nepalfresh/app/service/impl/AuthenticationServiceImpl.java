package com.nepalfresh.app.service.impl;

import com.nepalfresh.app.core.service.JwtService;
import com.nepalfresh.app.models.AuthenticationRequest;
import com.nepalfresh.app.models.AuthenticationResponse;
import com.nepalfresh.app.service.AuthenticationService;
import com.nepalfresh.common.constant.StatusConstant;
import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.repo.StatusRepository;
import com.nepalfresh.common.util.ResponseUtil;
import com.nepalfresh.entity.Customer;
import com.nepalfresh.repository.customer.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final CustomerRepository customerRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final StatusRepository statusRepository;


    @Override
    public ApiResponse authenticate(AuthenticationRequest request) {
        Optional<Customer> user = customerRepository.findByUsername(request.getUsername());

        if (user.isPresent() && !user.get().getStatus().equals(statusRepository.findByName(StatusConstant.DELETED.getName()))) {
            if (user.get().getStatus().equals(statusRepository.findByName(StatusConstant.ACTIVE.getName()))) {
                Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
                String jwtToken = jwtService.generateToken(user.get());

                AuthenticationResponse authenticationResponse = new AuthenticationResponse();
                authenticationResponse.setToken(jwtToken);
                return ResponseUtil.getSuccessfulApiResponse(authenticationResponse, "Successfully Logged in.");
            } else if (user.get().getStatus().equals(statusRepository.findByName(StatusConstant.PENDING.getName()))) {
                return ResponseUtil.getFailureResponse("The user is pending verification.");
            }
            return ResponseUtil.getFailureResponse("The user is blocked.");
        } else {
            return ResponseUtil.getFailureResponse("The user does not exist.");
        }
    }
}