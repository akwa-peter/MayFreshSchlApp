package ng.com.rad5.mayfreshmobile;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView logo = (ImageView) findViewById(R.id.logo);
        TextView name = (TextView) findViewById(R.id.txt_welcome);

        Animation animation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.splash_anim);
        logo.startAnimation(animation);
        name.startAnimation(animation);

        /*Create a thread method for the splash screen
         * and override the run method
         */
        Thread my_thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);

                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        //Start the thread
        my_thread.start();

    }

}
