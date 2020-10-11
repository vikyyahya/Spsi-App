package ai.lenna.spsiapp.model;

import com.pixplicity.easyprefs.library.Prefs;

import ai.lenna.spsiapp.login.LoginResponse;
import ai.lenna.spsiapp.network.ApiBuilder;
import ai.lenna.spsiapp.network.ApiService;
import ai.lenna.spsiapp.profile.ProfileContract;
import ai.lenna.spsiapp.register.RegisterResponse;
import ai.lenna.spsiapp.util.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProfileModel implements ProfileContract.Model {
    @Override
    public void getDataProfile() {
        ApiService service = ApiBuilder.getClient().create(ApiService.class);
        Call<LoginResponse> call = service.getProfile(Prefs.getString(Constant.TOKEN,""));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }
}
