package ai.lenna.spsiapp.register;

import ai.lenna.spsiapp.GenericErrorResponseBean;
import ai.lenna.spsiapp.login.LoginContract;
import ai.lenna.spsiapp.login.LoginRequest;
import ai.lenna.spsiapp.login.LoginResponse;

public interface RegisterContract {
    interface View {
        void moveToHome(RegisterResponse resp);

        void showDialogGagal(String message);

        void showProgress();

        void hideProgress();

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {
        void onDestroy();

        void requestDataFromServer(RegisterRequest loginReq);
    }

    interface Model {
        interface OnFinishedListener {
            void onFinishedSuccess(RegisterResponse loginResp);
            void onFinishedFail(GenericErrorResponseBean genericErrorResponseBean);
            void onFailure(Throwable t);
        }

        void submitDataRegiter(RegisterContract.Model.OnFinishedListener onFinishedListener, RegisterRequest registerRequest);
    }
}
