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

import ai.lenna.spsiapp.MainActivity;
import ai.lenna.spsiapp.R;
import ai.lenna.spsiapp.register.RegisterActivity;
import ai.lenna.spsiapp.util.ShowAllert;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{
    TextView register;
    Button button_login ;
    private LoginPresenter presenter;
    ProgressDialog progressDialog;
    ShowAllert showAllert;
    LoginRequest loginRequest;
    LoginResponse loginResponse;




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

    @Override
    public void moveToHome(LoginResponse resp) {

        if(resp.isSuccess()){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }


    }

    @Override
    public void showDialogGagal(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

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
