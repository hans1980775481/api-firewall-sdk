package com.github.skycloudtech.apifirewallsdk.firewall.checkpoint.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author: xiongmin
 * @since create by 2022/6/15 14:20
 */
@Builder
@Data
public class LoginRequest {
    private String user;
    private String password;
}
