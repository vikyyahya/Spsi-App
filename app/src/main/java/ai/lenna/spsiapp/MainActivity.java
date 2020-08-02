package ai.lenna.spsiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ai.lenna.spsiapp.home_karyawan.HomeFragment;

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

    private BottomNavigationView.OnNavigationItemSelectedListener  onClickBottomSheet = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_home:
                        fragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();

                    break;
                case R.id.navigation_profile:

                    break;
                case R.id.navigation_information:

                    break;
            }
            return true;
        }
    };
}