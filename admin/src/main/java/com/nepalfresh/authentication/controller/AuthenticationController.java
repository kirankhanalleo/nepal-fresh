package com.nepalfresh.authentication.controller;


import com.nepalfresh.common.constant.ApiConstant;
import com.nepalfresh.authentication.models.AuthenticationRequest;
import com.nepalfresh.authentication.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.AUTHENTICATE)
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping()
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
