package com.nepalfresh.app.service;

import com.nepalfresh.app.models.AuthenticationRequest;
import com.nepalfresh.app.models.AuthenticationResponse;
import com.nepalfresh.common.dto.ApiResponse;

public interface AuthenticationService {
    ApiResponse<AuthenticationResponse> authenticate(AuthenticationRequest request);
}
