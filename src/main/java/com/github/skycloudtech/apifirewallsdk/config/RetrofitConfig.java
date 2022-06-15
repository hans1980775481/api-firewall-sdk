package com.github.skycloudtech.apifirewallsdk.config;

import com.github.skycloudtech.apifirewallsdk.firewall.checkpoint.CheckPointManagementApi;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiongmin
 * @since create by 2022/6/15 10:55
 */
@Configuration
public class RetrofitConfig {

    @Value("${retrofit.default.baseUrl}")
    private String baseUrl;

    private X509TrustManager x509TrustManager = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    };

    private OkHttpClient mHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), x509TrustManager)//配置
            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())//配置
            .build();


//    @Bean
//    public SangforApi sangforApi(){
//        //通过拦截器实现retrofit重试
//        Retrofit retrofit=new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(JacksonConverterFactory.create()).build();
//        //动态代理模式创建请求接口的实例
//        SangforApi service=retrofit.create(SangforApi.class);
//        return service;
//    }

    @Bean
    public CheckPointManagementApi checkPointManagementApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(mHttpClient) // 配置okhttp
                .build();
        //动态代理模式创建请求接口的实例
        CheckPointManagementApi service = retrofit.create(CheckPointManagementApi.class);
        return service;
    }
}
