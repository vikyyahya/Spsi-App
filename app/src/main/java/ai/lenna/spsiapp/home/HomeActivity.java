package ai.lenna.spsiapp.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.pixplicity.easyprefs.library.Prefs;

import ai.lenna.spsiapp.R;
import ai.lenna.spsiapp.pengunduran.PengunduranActivity;
import ai.lenna.spsiapp.profile.ProfilActivity;
import ai.lenna.spsiapp.util.Constant;

public class HomeActivity extends AppCompatActivity {
    ImageView ivProfile,ivPengunduran,ivKegiatan,ivStruktur,ivInformasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ivProfile = findViewById(R.id.iv_profile);
        ivPengunduran= findViewById(R.id.iv_pengunduran);
        ivKegiatan = findViewById(R.id.iv_kegiatan);
        ivStruktur = findViewById(R.id.iv_struktur);
        ivInformasi = findViewById(R.id.iv_informasi);

        ivProfile.setOnClickListener(goProfil);
        ivPengunduran.setOnClickListener(goPengunduran);
    }

    private View.OnClickListener goProfil = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, ProfilActivity.class);
            startActivity(intent);
            finish();

        }
    };

    private View.OnClickListener goPengunduran = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, PengunduranActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
