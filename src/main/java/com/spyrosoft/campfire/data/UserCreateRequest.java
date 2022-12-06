package com.spyrosoft.campfire.data;

import com.spyrosoft.campfire.entities.Authorities;
import lombok.Data;

@Data
public class UserCreateRequest {
    private String username;
    private String password;
    private Authorities authority;
}
