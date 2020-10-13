package ai.lenna.spsiapp.berita;

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
import ai.lenna.spsiapp.home.HomeActivity;
import ai.lenna.spsiapp.login.LoginResponse;
import ai.lenna.spsiapp.network.ApiBuilder;
import ai.lenna.spsiapp.network.ApiService;
import ai.lenna.spsiapp.profile.ProfilActivity;
import ai.lenna.spsiapp.util.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritaActivity extends AppCompatActivity {
    RecyclerView rv_information;
    private  BeritaAdapter beritaAdapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);
        rv_information = findViewById(R.id.rv_information);
        progressDialog = new ProgressDialog(this);
        getDataInformasi();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(BeritaActivity.this, HomeActivity.class);
        startActivity(intent);
    }
     private void getDataInformasi(){
         progressDialog.setTitle("Tunggu sebentar");
         progressDialog.setMessage("Tunggu sebentar");
         progressDialog.show();
         ApiService service = ApiBuilder.getClient().create(ApiService.class);
         Call<BeritaResponse> call = service.getBerita("Bearer " + Prefs.getString(Constant.TOKEN,""));
         call.enqueue(new Callback<BeritaResponse>() {
             @Override
             public void onResponse(Call<BeritaResponse> call, Response<BeritaResponse> response) {
                 ArrayList<Berita> beritas = response.body().getBerita();
                 beritaAdapter = new BeritaAdapter(response.body().getBerita());
                 rv_information.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                 rv_information.setAdapter(beritaAdapter);
                 rv_information.setOnFlingListener(null);
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         progressDialog.hide();
                     }
                 },3000);
             }

             @Override
             public void onFailure(Call<BeritaResponse> call, Throwable t) {
                 progressDialog.hide();

             }
         });

     }
}
