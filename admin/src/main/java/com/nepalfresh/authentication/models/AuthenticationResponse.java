package com.nepalfresh.authentication.models;

import com.nepalfresh.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse extends ModelBase {

    private String token;

}
