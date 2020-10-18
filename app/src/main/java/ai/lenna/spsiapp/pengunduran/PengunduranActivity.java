package ai.lenna.spsiapp.pengunduran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.pixplicity.easyprefs.library.Prefs;

import ai.lenna.spsiapp.R;
import ai.lenna.spsiapp.home.HomeActivity;
import ai.lenna.spsiapp.login.LoginActivity;
import ai.lenna.spsiapp.login.LoginResponse;
import ai.lenna.spsiapp.network.ApiBuilder;
import ai.lenna.spsiapp.network.ApiService;
import ai.lenna.spsiapp.profile.ProfilActivity;
import ai.lenna.spsiapp.util.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengunduranActivity extends AppCompatActivity {

    EditText etNama,etNik,etPlan,etBagian;
    Button btnPengunduran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengunduran);
        etNama = findViewById(R.id.et_nama);
        etNik = findViewById(R.id.et_NIK);
        etPlan = findViewById(R.id.et_plan);
        etBagian = findViewById(R.id.et_bagian);
        btnPengunduran = findViewById(R.id.btnPengunduran);

        etNama.setText(Prefs.getString(Constant.NAME,""));
        etNik.setText(Prefs.getString(Constant.NIK,""));
        etPlan.setText(Prefs.getString(Constant.PLANT,""));
        etBagian.setText(Prefs.getString(Constant.BAGIAN,""));

        btnPengunduran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialStyledDialog.Builder(v.getContext())
                        .setTitle("Konfirmasi")
                        .setStyle(Style.HEADER_WITH_ICON)
                        .setDescription("Apakah anda mau mengundurkan diri")
                        .setPositiveText("OK")
                        .setIcon(R.drawable.ic_dialog_alert)
                        .setHeaderColor(R.color.red_notification)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                ApiService service = ApiBuilder.getClient().create(ApiService.class);
                                Call<LoginResponse> call = service.submipengundurandiri("Bearer " + Prefs.getString(Constant.TOKEN,""));
                                call.enqueue(new Callback<LoginResponse>() {
                                    @Override
                                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                        Prefs.putInt(Constant.LOGIN,0);
                                        Intent intent = new Intent(PengunduranActivity.this, LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                                    }
                                });
                            }
                        })
                        .show();


            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PengunduranActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
