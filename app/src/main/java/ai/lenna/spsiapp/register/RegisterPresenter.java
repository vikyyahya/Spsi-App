package ai.lenna.spsiapp.register;

import android.app.ProgressDialog;

import ai.lenna.spsiapp.GenericErrorResponseBean;
import ai.lenna.spsiapp.model.RegisterModel;


public class RegisterPresenter implements RegisterContract.Presenter, RegisterContract.Model.OnFinishedListener  {

    private RegisterContract.View view;
    private RegisterContract.Model model;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
        this.model = new RegisterModel();
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void requestDataFromServer(RegisterRequest registerRequest) {
        if (view != null) {
            view.showProgress();
        }
        model.submitDataRegiter(this,registerRequest);
    }

    @Override
    public void onFinishedSuccess(RegisterResponse loginResp) {
        if (view != null){
            view.hideProgress();
        }
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
