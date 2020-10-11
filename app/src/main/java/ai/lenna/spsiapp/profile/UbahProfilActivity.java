package ai.lenna.spsiapp.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import ai.lenna.spsiapp.R;

public class UbahProfilActivity extends AppCompatActivity {
    TextView etNama,etNik,etPlan,etBagian,etTempatLahir,etTanggalLahir,etJenisKelamin,etAgama,etAlamat,etEmail;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profil);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UbahProfilActivity.this,ProfilActivity.class));
    }
}
