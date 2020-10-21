package ai.lenna.spsiapp.strukturorganisasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;

import ai.lenna.spsiapp.R;
import ai.lenna.spsiapp.berita.Berita;
import ai.lenna.spsiapp.berita.BeritaAdapter;
import ai.lenna.spsiapp.berita.BeritaResponse;
import ai.lenna.spsiapp.home.HomeActivity;
import ai.lenna.spsiapp.network.ApiBuilder;
import ai.lenna.spsiapp.network.ApiService;
import ai.lenna.spsiapp.util.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StrukturOrganisasi extends AppCompatActivity {

    RecyclerView rv_information;
    private StrukturAdapter strukturAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struktur_organisasi);
        rv_information = findViewById(R.id.rv_organisasai);
        progressDialog = new ProgressDialog(this);
        getDataInformasi();
    }

    private void getDataInformasi(){
        progressDialog.setTitle("Tunggu sebentar");
        progressDialog.setMessage("Tunggu sebentar");
        progressDialog.show();
        ApiService service = ApiBuilder.getClient().create(ApiService.class);
        Call<StrukturResponse> call = service.getStruktur("Bearer " + Prefs.getString(Constant.TOKEN,""));
        call.enqueue(new Callback<StrukturResponse>() {
            @Override
            public void onResponse(Call<StrukturResponse> call, Response<StrukturResponse> response) {
                ArrayList<Struktur> s = response.body().getS();
                strukturAdapter = new StrukturAdapter(response.body().getS());
                rv_information.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                rv_information.setAdapter(strukturAdapter);
                rv_information.setOnFlingListener(null);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.hide();
                    }
                },3000);
            }

            @Override
            public void onFailure(Call<StrukturResponse> call, Throwable t) {
                progressDialog.hide();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(StrukturOrganisasi.this, HomeActivity.class);
        startActivity(intent);

    }
}
