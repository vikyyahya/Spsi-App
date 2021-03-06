package ai.lenna.spsiapp;

import android.app.Application;
import android.content.ContextWrapper;

import com.pixplicity.easyprefs.library.Prefs;

public class PrefsApplication extends Application {
    public static PrefsApplication context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        // Initialize the Prefs class
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }
}
