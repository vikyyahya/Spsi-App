package ai.lenna.spsiapp.network;

import ai.lenna.spsiapp.login.LoginRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServeice {


    @POST("backend/api/auth/login")
    Call<LoginResponse> submitLogin(@Body LoginRequest loginReq);
}
