package ai.lenna.spsiapp.model;

import ai.lenna.spsiapp.GenericErrorResponseBean;
import ai.lenna.spsiapp.network.ApiBuilder;
import ai.lenna.spsiapp.network.ApiService;
import ai.lenna.spsiapp.register.RegisterContract;
import ai.lenna.spsiapp.register.RegisterRequest;
import ai.lenna.spsiapp.register.RegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterModel implements RegisterContract.Model {
    @Override
    public void submitDataRegiter(final OnFinishedListener onFinishedListener, RegisterRequest registerRequest) {
        ApiService service = ApiBuilder.getClient().create(ApiService.class);
        Call<RegisterResponse> call = service.submitRegister(registerRequest);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){
                    onFinishedListener.onFinishedSuccess(response.body());

                }else {
                    GenericErrorResponseBean errorResponse = new GenericErrorResponseBean();
                    errorResponse.setCode(5000);
                    errorResponse.setMessage("Terjadi Kesalahan");
                    onFinishedListener.onFinishedFail(errorResponse);
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                onFinishedListener.onFailure(t);

            }
        });

    }
}
