package ai.lenna.spsiapp.profile;

import ai.lenna.spsiapp.GenericErrorResponseBean;
import ai.lenna.spsiapp.login.LoginResponse;

public interface ProfileContract {
    interface View {
        void moveToHome(LoginResponse resp);

        void showDialogGagal(String message);

        void showProgress();

        void hideProgress();

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {
        void onDestroy();

        void requestDataFromServer();
    }

    interface Model {
        interface OnFinishedListener {
            void onFinishedSuccess(LoginResponse loginResp);
            void onFinishedFail(GenericErrorResponseBean genericErrorResponseBean);
            void onFailure(Throwable t);
        }

        void getDataProfile();
    }
}
