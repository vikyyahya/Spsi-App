package ai.lenna.spsiapp.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pixplicity.easyprefs.library.Prefs;

import ai.lenna.spsiapp.R;
import ai.lenna.spsiapp.home.HomeActivity;
import ai.lenna.spsiapp.login.LoginActivity;
import ai.lenna.spsiapp.login.LoginResponse;
import ai.lenna.spsiapp.network.ApiBuilder;
import ai.lenna.spsiapp.network.ApiService;
import ai.lenna.spsiapp.util.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {
    TextView etNama,etNik,etPlan,etBagian,etTempatLahir,etTanggalLahir,etJenisKelamin,etAgama,etAlamat,etEmail;
    ProgressDialog progressDialog;
    Button btnUbah,btnKeluar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        progressDialog = new ProgressDialog(this);

        etNama= findViewById(R.id.et_nama);
        etBagian= findViewById(R.id.et_bagian);
        etPlan= findViewById(R.id.et_plant);
        etTempatLahir= findViewById(R.id.et_tempat_lahir);
        etTanggalLahir= findViewById(R.id.et_tanggal_lahir);
        etJenisKelamin= findViewById(R.id.et_jenis_kelamin);
        etAgama= findViewById(R.id.et_agama);
        etAlamat= findViewById(R.id.et_alamat);
        etEmail= findViewById(R.id.et_email);
        etNik = findViewById(R.id.et_nik);
        btnUbah = findViewById(R.id.btn_ubah_profil);
        btnKeluar = findViewById(R.id.btn_logout);
        btnUbah.setOnClickListener(goUbahProfil);
        btnKeluar.setOnClickListener(goLogout);
        getDataProfile();


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

    private View.OnClickListener goUbahProfil = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(ProfilActivity.this,UbahProfilActivity.class));
            finish();
        }
    } ;

    private View.OnClickListener goLogout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Prefs.putInt(Constant.LOGIN,0);
            Intent intent = new Intent(ProfilActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    } ;

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ProfilActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}