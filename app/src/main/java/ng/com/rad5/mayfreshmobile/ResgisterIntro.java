package ng.com.rad5.mayfreshmobile;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class ResgisterIntro extends AppCompatActivity {

    Toolbar toolbar;

    LinearLayout btn_Register;
    LinearLayout btn_activateDevice;

    PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check for first time launch - before calling setContentView()
        preferenceManager = new PreferenceManager(ResgisterIntro.this);
        if (!preferenceManager.isFirstTimeLaunch()) {
            launchSplashScreen();
            finish();
        }

        setContentView(R.layout.activity_resgister_intro);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_Register = (LinearLayout) findViewById(R.id.btn_register);
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResgisterIntro.this, RegisterActivity1.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        btn_activateDevice = (LinearLayout) findViewById(R.id.btn_activate_device);
        btn_activateDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResgisterIntro.this, ActivateDevice.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

    }

    private void launchSplashScreen(){
        preferenceManager.setFirstTimeLaunch(false);
        startActivity(new Intent(ResgisterIntro.this, SplashScreen.class));
        finish();
    }

}
