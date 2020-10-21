package ai.lenna.spsiapp.register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.Calendar;
import java.util.Date;

import ai.lenna.spsiapp.MainActivity;
import ai.lenna.spsiapp.R;
import ai.lenna.spsiapp.login.LoginActivity;
import ai.lenna.spsiapp.util.Constant;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {
    EditText etNama,etNik,etPlan,etBagian,etTempatLahir,etTanggalLahir,etJenisKelamin,etAgama,etAlamat,etEmail,etPassword;
    Button btnRegister;
    ProgressDialog progressDialog;
    RegisterRequest registerRequest;
    private RegisterPresenter registerPresenter;
    DatePicker dt ;
    DatePickerDialog picker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran);
        etNama= findViewById(R.id.et_nama);
        etNik= findViewById(R.id.et_NIK);
        etBagian= findViewById(R.id.et_bagian);
        etPlan= findViewById(R.id.et_plan);
        etTempatLahir= findViewById(R.id.et_tempat);
        etTanggalLahir= findViewById(R.id.et_ttl);
        etJenisKelamin= findViewById(R.id.et_jenis_kel);
        etAgama= findViewById(R.id.et_agama);
        etAlamat= findViewById(R.id.et_alamat);
        etEmail= findViewById(R.id.et_email);
        etPassword= findViewById(R.id.et_password);

        //
//        etNama.setText("viky");
//        etNik.setText("127102987");
//        etBagian.setText("Jahit");
//        etPlan.setText("B");
//        etTempatLahir.setText("klaten");
//        etJenisKelamin.setText("Laki - laki");
//        etAgama.setText("Islam");
//        etAlamat.setText("Tangerang");
//        etEmail.setText("viky@yaho.com");
//        etPassword.setText("12345678");

        btnRegister= findViewById(R.id.btn_daftar);
        progressDialog= new ProgressDialog(this);
        registerPresenter = new RegisterPresenter(this);
        registerRequest = new RegisterRequest();
        final Date date = new Date();

        etTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                etTanggalLahir.setText(year+  "-"  + (monthOfYear + 1) + "-" + dayOfMonth  );
                            }
                        }, year, month, day);
                picker.show();

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerRequest.setName(etNama.getText().toString());
                registerRequest.setNik(etNik.getText().toString());
                registerRequest.setPlant(etPlan.getText().toString());
                registerRequest.setBagian(etBagian.getText().toString());
                registerRequest.setTempatLahir(etTempatLahir.getText().toString());
                registerRequest.setTanggalLahir(etTanggalLahir.getText().toString());
                registerRequest.setJenis_kelamin(etJenisKelamin.getText().toString());
                registerRequest.setAgama(etAgama.getText().toString());
                registerRequest.setAlamat(etAlamat.getText().toString());
                registerRequest.setEmail(etEmail.getText().toString());
                registerRequest.setPassword(etPassword.getText().toString());
                registerPresenter.requestDataFromServer(registerRequest);

            }
        });

    }


    @Override
    public void moveToHome(RegisterResponse resp) {
//        Prefs.putInt(Constant.LOGIN, 1);
//        Prefs.putString(Constant.NAME, resp.getUser().getName());
//        Prefs.putString(Constant.NIK,  resp.getUser().getNik());
//        Prefs.putString(Constant.EMAIL,  resp.getUser().getEmail());
//        Prefs.putString(Constant.PLANT,  resp.getUser().getPlant());
//        Prefs.putString(Constant.BAGIAN,  resp.getUser().getBagian());
//        Prefs.putString(Constant.TEMPAT_LAHIR,  resp.getUser().getTanggal_lahir());
//        Prefs.putString(Constant.TANGGAL_LAHIR,  resp.getUser().getTanggal_lahir());
//        Prefs.putString(Constant.AGAMA,  resp.getUser().getAgama() );
//        Prefs.putString(Constant.ALAMAT,  resp.getUser().getAlamat());
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showDialogGagal(String message) {
        showAllertValidation(message);

    }

    @Override
    public void showProgress() {
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setMessage("Mohon Tunggu");
        progressDialog.show();

    }

    @Override
    public void hideProgress() {

        progressDialog.hide();

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        showAllertValidation(throwable.toString());
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
