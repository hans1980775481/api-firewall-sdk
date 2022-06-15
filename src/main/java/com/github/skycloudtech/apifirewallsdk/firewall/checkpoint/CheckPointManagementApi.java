package com.github.skycloudtech.apifirewallsdk.firewall.checkpoint;

import com.github.skycloudtech.apifirewallsdk.firewall.checkpoint.model.LoginRequest;
import com.github.skycloudtech.apifirewallsdk.firewall.checkpoint.model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author: xiongmin
 * @since create by 2022/6/15 13:56
 */
public interface CheckPointManagementApi {

    /**
     * 登录接口
     *
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
