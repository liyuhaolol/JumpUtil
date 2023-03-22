package spa.lyh.cn.jumputil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
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

}