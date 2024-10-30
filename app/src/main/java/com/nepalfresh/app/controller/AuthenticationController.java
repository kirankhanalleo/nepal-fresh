package com.nepalfresh.app.controller;

import com.nepalfresh.app.models.AuthenticationRequest;
import com.nepalfresh.app.service.AuthenticationService;
import com.nepalfresh.common.constant.ApiConstant;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
