package ai.lenna.spsiapp.model;
import ai.lenna.spsiapp.login.LoginContract;
import ai.lenna.spsiapp.login.LoginRequest;
import ai.lenna.spsiapp.login.LoginResponse;
import ai.lenna.spsiapp.network.ApiBuilder;
import ai.lenna.spsiapp.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel implements LoginContract.Model{
    @Override
    public void submitDataLogin(final OnFinishedListener onFinishedListener, LoginRequest loginReq) {
        ApiService service = ApiBuilder.getClient().create(ApiService.class);
        Call<LoginResponse> call = service.submitLogin(loginReq);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    onFinishedListener.onFinishedSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }
}
