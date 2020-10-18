package ai.lenna.spsiapp.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.Calendar;

import ai.lenna.spsiapp.R;
import ai.lenna.spsiapp.login.LoginResponse;
import ai.lenna.spsiapp.network.ApiBuilder;
import ai.lenna.spsiapp.network.ApiService;
import ai.lenna.spsiapp.register.RegisterActivity;
import ai.lenna.spsiapp.register.RegisterRequest;
import ai.lenna.spsiapp.register.RegisterResponse;
import ai.lenna.spsiapp.util.Constant;
import ai.lenna.spsiapp.util.ShowAllert;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahProfilActivity extends AppCompatActivity {
    TextView etNama,etNik,etPlan,etBagian,etTempatLahir,etTanggalLahir,etJenisKelamin,etAgama,etAlamat,etEmail,etPassword;
    ProgressDialog progressDialog;
    DatePickerDialog picker;
    Button btnUbah;
    RegisterRequest registerRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profil);
        progressDialog = new ProgressDialog(this);
        etNama= findViewById(R.id.et_nama);
        etBagian= findViewById(R.id.et_bagian);
        etPlan= findViewById(R.id.et_plan);
        etTempatLahir= findViewById(R.id.et_tempat);
        etTanggalLahir= findViewById(R.id.et_tl);
        etJenisKelamin= findViewById(R.id.et_jenis_kel);
        etAgama= findViewById(R.id.et_agama);
        etAlamat= findViewById(R.id.et_alamat);
        etEmail= findViewById(R.id.et_email);
        etNik = findViewById(R.id.et_NIK);
        btnUbah = findViewById(R.id.btn_ubah);
        etPassword = findViewById(R.id.et_password);
        getDataProfile();
        registerRequest = new RegisterRequest();

        etTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(UbahProfilActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                etTanggalLahir.setText(year+  "-" + dayOfMonth + "-" + (monthOfYear + 1)  );
                            }
                        }, year, month, day);
                picker.show();

            }
        });
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                progressDialog.setTitle("Tunggu sebentar");
                progressDialog.setMessage("Tunggu sebentar");
                progressDialog.show();
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
                ApiService service = ApiBuilder.getClient().create(ApiService.class);
                Call<RegisterResponse> call = service.updateProfile("Bearer "+  Prefs.getString(Constant.TOKEN,"") ,registerRequest);
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        progressDialog.hide();
                        new MaterialStyledDialog.Builder(v.getContext())
                                .setTitle("Berhasil")
                                .setDescription("Berhasil Update Profile")
                                .setIcon(R.drawable.ic_dialog_checked)
                                .setHeaderColor(R.color.dialog_header_success)
                                .setPositiveText("OK")
                                .show();
                        getDataProfile();
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {

                    }
                });


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UbahProfilActivity.this,ProfilActivity.class));
    }

    private void getDataProfile(){
        progressDialog.setTitle("Tunggu sebentar");
        progressDialog.setMessage("Tunggu sebentar");
        progressDialog.show();
        ApiService service = ApiBuilder.getClient().create(ApiService.class);
        Call<LoginResponse> call = service.getProfile("Bearer " + Prefs.getString(Constant.TOKEN,""));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                etNama.setText(response.body().getUser().getName());
                etNik.setText(response.body().getUser().getNik());
                etBagian.setText(response.body().getUser().getBagian());
                etPlan.setText(response.body().getUser().getPlant());
                etTempatLahir.setText(response.body().getUser().getTempat_lahir());
                etJenisKelamin.setText(response.body().getUser().getJenis_kelamin());
                etAgama.setText(response.body().getUser().getAgama());
                etAlamat.setText(response.body().getUser().getAlamat());
                etEmail.setText(response.body().getUser().getEmail());
                etTanggalLahir.setText(response.body().getUser().getTanggal_lahir());
                progressDialog.hide();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

}
