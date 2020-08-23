package ai.lenna.spsiapp.login;

import ai.lenna.spsiapp.GenericErrorResponseBean;
import ai.lenna.spsiapp.model.LoginModel;

public class LoginPresenter implements LoginContract.Presenter, LoginContract.Model.OnFinishedListener  {


    private LoginContract.View view;
    private LoginContract.Model model;
    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.model = new LoginModel();
    }
    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void requestDataFromServer(LoginRequest loginReq) {
        if (view != null) {
            view.showProgress();
        }
        model.submitDataLogin(this, loginReq);
    }

    @Override
    public void onFinishedSuccess(LoginResponse loginResp) {
        if (view != null){
            view.hideProgress();
        }
        view.moveToHome(loginResp);
    }

    @Override
    public void onFinishedFail(GenericErrorResponseBean genericErrorResponseBean) {
        if (view != null){
            view.hideProgress();
        }
        view.showDialogGagal(genericErrorResponseBean.getMessage());

    }

    @Override
    public void onFailure(Throwable t) {
        if(view != null){
            view.hideProgress();
        }
        view.showDialogGagal(t.getMessage());
    }
}
