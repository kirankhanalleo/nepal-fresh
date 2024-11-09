package com.nepalfresh.authentication.service;

import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.authentication.models.AuthenticationRequest;
import com.nepalfresh.authentication.models.AuthenticationResponse;

public interface AuthenticationService {
    ApiResponse<AuthenticationResponse> authenticate(AuthenticationRequest request);
}
