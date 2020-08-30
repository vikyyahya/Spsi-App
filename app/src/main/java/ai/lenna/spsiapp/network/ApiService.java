package ai.lenna.spsiapp.network;

import ai.lenna.spsiapp.login.LoginRequest;
import ai.lenna.spsiapp.login.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {


    @POST("api/login")
    Call<LoginResponse> submitLogin(@Body LoginRequest loginReq);
}
