package spa.lyh.cn.jumputil;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.window.OnBackInvokedCallback;

import spa.lyh.cn.lib_utils.translucent.BarUtils;
import spa.lyh.cn.lib_utils.translucent.TranslucentUtils;
import spa.lyh.cn.lib_utils.translucent.navbar.NavBarFontColorControler;
import spa.lyh.cn.lib_utils.translucent.statusbar.StatusBarFontColorControler;

@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
public class MainActivity extends AppCompatActivity{
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goGoogleSetting();
            }
        });
        TranslucentUtils.setTranslucentBoth(getWindow());
        checkStatusBarColor(getResources().getConfiguration());
    }

    private void goGoogleSetting(){
        try {
            Intent intent = new Intent();
            //intent.setClassName("com.google.android.gms","com.google.android.gms.app.settings.GoogleSettingsIALink");
            intent.setClassName("com.google.android.gms","com.google.android.gms.app.settings.GoogleSettingsLink");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this,"转跳失败，请检查手机是否存在或启用gms",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        checkStatusBarColor(newConfig);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            int currentNightMode = newConfig.uiMode & Configuration.UI_MODE_NIGHT_MASK;
            switch (currentNightMode) {
                case Configuration.UI_MODE_NIGHT_NO:
                case Configuration.UI_MODE_NIGHT_YES:
                    recreate();
                    break;
            }
        }

    }

    private void checkStatusBarColor(Configuration config){
        boolean isDarkFont = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            int currentNightMode = config.uiMode & Configuration.UI_MODE_NIGHT_MASK;
            switch (currentNightMode) {
                case Configuration.UI_MODE_NIGHT_NO:
                    isDarkFont = true;
                    break;
                case Configuration.UI_MODE_NIGHT_YES:
                    isDarkFont = false;
                    break;
            }
        }
        StatusBarFontColorControler.setStatusBarMode(getWindow(), isDarkFont);
        NavBarFontColorControler.setNavBarMode(getWindow(),isDarkFont);
    }

}