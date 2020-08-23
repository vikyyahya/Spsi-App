package ai.lenna.spsiapp.login;

import ai.lenna.spsiapp.GenericErrorResponseBean;
import ai.lenna.spsiapp.login.LoginRequest;
import ai.lenna.spsiapp.login.LoginResponse;

public interface LoginContract {
    interface View {
        void moveToHome(LoginResponse resp);

        void showDialogGagal(String message);

        void showProgress();

        void hideProgress();

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {
        void onDestroy();

        void requestDataFromServer(LoginRequest loginReq);
    }

    interface Model {
        interface OnFinishedListener {
            void onFinishedSuccess(LoginResponse loginResp);
            void onFinishedFail(GenericErrorResponseBean genericErrorResponseBean);

            void onFailure(Throwable t);
        }

        void submitDataLogin(OnFinishedListener onFinishedListener, LoginRequest loginReq);
    }
}
