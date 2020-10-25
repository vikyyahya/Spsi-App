package ai.lenna.spsiapp.home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.pixplicity.easyprefs.library.Prefs;

import ai.lenna.spsiapp.R;
import ai.lenna.spsiapp.berita.BeritaActivity;
import ai.lenna.spsiapp.kegiatan.KegiatanActivity;
import ai.lenna.spsiapp.login.LoginResponse;
import ai.lenna.spsiapp.network.ApiBuilder;
import ai.lenna.spsiapp.network.ApiService;
import ai.lenna.spsiapp.pengunduran.PengunduranActivity;
import ai.lenna.spsiapp.profile.ProfilActivity;
import ai.lenna.spsiapp.strukturorganisasi.StrukturOrganisasi;
import ai.lenna.spsiapp.util.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    ImageView ivProfile,ivPengunduran,ivKegiatan,ivStruktur,ivInformasi;
    ProgressDialog progressDialog;
    private static boolean isAnggota = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ivProfile = findViewById(R.id.iv_profile);
        ivPengunduran= findViewById(R.id.iv_pengunduran);
        ivKegiatan = findViewById(R.id.iv_kegiatan);
        ivStruktur = findViewById(R.id.iv_struktur);
        ivInformasi = findViewById(R.id.iv_informasi);
        progressDialog = new ProgressDialog(this);

        //
        ivProfile.setOnClickListener(goProfil);
        ivPengunduran.setOnClickListener(goPengunduran);
        ivInformasi.setOnClickListener(goInformasi);
        ivKegiatan.setOnClickListener(goKegiatan);
        ivStruktur.setOnClickListener(struktur);
        getDataProfile();
    }

    private View.OnClickListener goProfil = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // pindah aktivity / halaman
            Intent intent = new Intent(HomeActivity.this, ProfilActivity.class);
            startActivity(intent);
            finish();

        }
    };

    private View.OnClickListener goPengunduran = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isAnggota == true){
                Intent intent = new Intent(HomeActivity.this, PengunduranActivity.class);
                startActivity(intent);
                finish();
            }

        }
    };

    private View.OnClickListener goInformasi = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, BeritaActivity.class);
            startActivity(intent);
            finish();
        }
    };
    private View.OnClickListener goKegiatan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, KegiatanActivity.class);
            startActivity(intent);
            finish();
        }
    };

    private View.OnClickListener struktur = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, StrukturOrganisasi.class);
            startActivity(intent);
            finish();
        }
    };

    private void getDataProfile(){
        progressDialog.setTitle("Tunggu sebentar");
        progressDialog.setMessage("Tunggu sebentar");
        progressDialog.show();
        ApiService service = ApiBuilder.getClient().create(ApiService.class);
        Call<LoginResponse> call = service.getProfile("Bearer " + Prefs.getString(Constant.TOKEN,""));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body().getUser().getStatus().equals("belum diproses")){
                    isAnggota = false;
                }
                progressDialog.hide();

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

}
