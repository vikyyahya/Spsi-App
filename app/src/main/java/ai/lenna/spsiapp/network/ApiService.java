package ai.lenna.spsiapp.network;

import ai.lenna.spsiapp.login.LoginRequest;
import ai.lenna.spsiapp.login.LoginResponse;
import ai.lenna.spsiapp.register.RegisterRequest;
import ai.lenna.spsiapp.register.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {


    @POST("api/login")
    Call<LoginResponse> submitLogin(@Body LoginRequest loginReq);

    @POST("api/register")
    Call<RegisterResponse> submitRegister(@Body RegisterRequest registerRequest);

    @GET("api/get-profil")
    Call<LoginResponse> getProfile(@Header("Authorization") String token);

}
