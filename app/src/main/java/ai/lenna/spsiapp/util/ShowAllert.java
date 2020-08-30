package ai.lenna.spsiapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import ai.lenna.spsiapp.R;

public class ShowAllert {
    public static Activity activityShowAllert;
    public void allertWarning(Context context, String title, String text){
        new MaterialStyledDialog.Builder(context)
                .setTitle(title)
                .setDescription(text)
                .setIcon(R.drawable.ic_report_problem_black_50dp)
                .setHeaderColor(R.color.dialog_header_gagal)
                .setPositiveText("OK")
                .show();
    }

}
