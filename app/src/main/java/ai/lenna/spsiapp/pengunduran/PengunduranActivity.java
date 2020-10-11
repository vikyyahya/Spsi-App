package ai.lenna.spsiapp.pengunduran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.pixplicity.easyprefs.library.Prefs;

import ai.lenna.spsiapp.R;
import ai.lenna.spsiapp.home.HomeActivity;
import ai.lenna.spsiapp.profile.ProfilActivity;
import ai.lenna.spsiapp.util.Constant;

public class PengunduranActivity extends AppCompatActivity {

    EditText etNama,etNik,etPlan,etBagian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengunduran);
        etNama = findViewById(R.id.et_nama);
        etNik = findViewById(R.id.et_NIK);
        etPlan = findViewById(R.id.et_plan);
        etBagian = findViewById(R.id.et_bagian);

        etNama.setText(Prefs.getString(Constant.NAME,""));
        etNik.setText(Prefs.getString(Constant.NIK,""));
        etPlan.setText(Prefs.getString(Constant.PLANT,""));
        etBagian.setText(Prefs.getString(Constant.BAGIAN,""));


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PengunduranActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
