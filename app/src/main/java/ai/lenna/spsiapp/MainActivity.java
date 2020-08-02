package ai.lenna.spsiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ai.lenna.spsiapp.home_karyawan.HomeFragment;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private Fragment fragment ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);

        BottomNavigationView bottomNavigationView =findViewById(R.id.bottomNavigationView);
//        Toolbar toolbars = findViewById(R.id.toolbar);

        if(fragment==null){
            fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
        }

    }
}