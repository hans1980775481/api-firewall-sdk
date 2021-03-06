package com.github.skycloudtech.apifirewallsdk.controller;

import com.github.skycloudtech.apifirewallsdk.firewall.checkpoint.CheckPointManagementApi;
import com.github.skycloudtech.apifirewallsdk.firewall.checkpoint.model.LoginRequest;
import com.github.skycloudtech.apifirewallsdk.firewall.checkpoint.model.LoginResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author: xiongmin
 * @since create by 2022/6/15 14:16
 */
@Slf4j
@RestController
public class CheckPointController {

    @Autowired
    private CheckPointManagementApi checkPointManagementApi;

    @GetMapping("")
    public LoginResponse getToken() throws IOException {
        LoginRequest loginRequest = LoginRequest.builder()
                .user("admin")
                .password("r00tme")
                .build();
        Call<LoginResponse> login = checkPointManagementApi.login(loginRequest);

        login.enqueue(new Callback<LoginResponse>() {
            @SneakyThrows
            @Override
            public void onResponse(@Nullable Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                log.info("请求成功！{}", response);
                log.info("请求成功！{}", response.raw());


                /**
                 * 要先判断是一个成功请求还是一个失败请求，决定调用哪个方法获取返回数据
                 */
                if (response.isSuccessful()) {
                    // tasks available
                    log.info("tasks available");
                    log.info("请求成功！{}", response.body());
                } else {
                    // error response, no access to resource?
                    log.error("error response, no access to resource?");
                    log.error("请求失败！{}", response.errorBody().string());
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, Throwable t) {
                log.error("请求失败！", t.toString());
            }
        });

//        LoginResponse loginResponse = login.execute().body();
        return null;
    }

}
