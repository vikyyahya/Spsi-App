package ai.lenna.spsiapp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.karan.churi.PermissionManager.PermissionManager;
import com.pixplicity.easyprefs.library.Prefs;

import ai.lenna.spsiapp.MainActivity;
import ai.lenna.spsiapp.R;
import ai.lenna.spsiapp.home.HomeActivity;
import ai.lenna.spsiapp.register.RegisterActivity;
import ai.lenna.spsiapp.util.Constant;
import ai.lenna.spsiapp.util.ShowAllert;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{
    TextView register;
    Button button_login ;
    private LoginPresenter presenter;
    ProgressDialog progressDialog;
    ShowAllert showAllert;
    LoginRequest loginRequest;
    LoginResponse loginResponse;
    PermissionManager permission;
    EditText edEmail,edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Context context = this;
        register = findViewById(R.id.text_view_register);
        button_login = findViewById(R.id.button_login);
        presenter = new LoginPresenter(this);
        edEmail = findViewById(R.id.edit_text_email);
        edPassword = findViewById(R.id.edit_text_password);
        loginRequest = new LoginRequest();
        progressDialog = new ProgressDialog(this);
        permission = new PermissionManager() {};

        //set test

//        edEmail.setText("viky@yahoo.com");
//        edPassword.setText("12345678");

        chekLogin();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String emailPatternId = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

                if(TextUtils.isEmpty(edEmail.getText())){
                    showAllertValidation("Email harap diisi");
                }
                else if(TextUtils.isEmpty(edPassword.getText())){
                    showAllertValidation("Password harap diisi");
                }else if(!edEmail.getText().toString().matches(emailPattern) && !edEmail.getText().toString().matches(emailPatternId)){
                    showAllert.allertWarning(context,"Perhatian","Harap mengisi email dengan benar");
                }else{
                    loginRequest.setEmail(edEmail.getText().toString());
                    loginRequest.setPassword(edPassword.getText().toString());
                    presenter.requestDataFromServer(loginRequest);

                }
            }
        });


    }

    private void chekLogin(){
        if (Prefs.getInt(Constant.LOGIN,0) == 1){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void moveToHome(LoginResponse resp) {

        if(resp.isSuccess()){
            Prefs.putInt(Constant.LOGIN, 1);
            Prefs.putString(Constant.TOKEN, resp.getAccessToken());
            Prefs.putString(Constant.NAME, resp.getUser().getName());
            Prefs.putString(Constant.NIK,  resp.getUser().getNik());
            Prefs.putInt(Constant.ID,  resp.getUser().getId());
            Prefs.putString(Constant.EMAIL,  resp.getUser().getEmail());
            Prefs.putString(Constant.PLANT,  resp.getUser().getPlant());
            Prefs.putString(Constant.BAGIAN,  resp.getUser().getBagian());
            Prefs.putString(Constant.TEMPAT_LAHIR,  resp.getUser().getTanggal_lahir());
            Prefs.putString(Constant.TANGGAL_LAHIR,  resp.getUser().getTanggal_lahir());
            Prefs.putString(Constant.AGAMA,  resp.getUser().getAgama() );
            Prefs.putString(Constant.ALAMAT,  resp.getUser().getAlamat());
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }


    }

    @Override
    public void showDialogGagal(String message) {
        showAllertValidation( message);
    }

    @Override
    public void showProgress() {
        progressDialog.setTitle("Tunggu sebentar");
        progressDialog.setMessage("Tunggu sebentar");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }

    private void showAllertValidation(String text) {
        new MaterialStyledDialog.Builder(this)
                .setTitle("Perhatian..")
                .setDescription(text)
                .setIcon(R.drawable.ic_report_problem_black_50dp)
                .setHeaderColor(R.color.dialog_header_gagal)
                .setPositiveText("OK")
                .show();
    }


}
