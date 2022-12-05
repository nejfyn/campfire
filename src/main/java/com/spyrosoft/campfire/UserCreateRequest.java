package com.spyrosoft.campfire;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String username;
    private String password;
    private Authorities authority;
}
