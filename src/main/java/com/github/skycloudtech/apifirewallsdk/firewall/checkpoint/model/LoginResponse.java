package com.github.skycloudtech.apifirewallsdk.firewall.checkpoint.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author: xiongmin
 * @since create by 2022/6/15 14:08
 */
@Data
public class LoginResponse {
    private String sid;
    @JsonProperty("api-server-version")
    private String apiServerVersion;
    @JsonProperty("disk-space-message")
    private String diskSpaceMessage;
    @JsonProperty("last-login-was-at")
    private Object lastLoginWasAt;
    @JsonProperty("login-message")
    private Object loginMessage;
    @JsonProperty("read-only")
    private Boolean readOnly;
    @JsonProperty("session-timeout")
    private Integer sessionTimeout;
    private String standby;
    private String uid;
    private String url;
    private String code;
    private String message;
}
