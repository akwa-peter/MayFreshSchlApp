package ng.com.rad5.mayfreshmobile;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MobilePin extends AppCompatActivity {

    Toolbar toolbar;

    EditText edt_mobilePin;
    EditText edt_confirmPin;
    Button btnNext;

    View view;

    Intent intent;
    PreferenceManager preferenceManager;

    FirebaseDatabase database;

    Intent preedingIntent;
    String accountNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_pin);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        view = (View) findViewById(R.id.mobil_pin_activity);
        database = DatabaseUtil.getDatabase();

        preedingIntent = getIntent();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edt_mobilePin = (EditText) findViewById(R.id.edt_mobile_pin);
        edt_confirmPin = (EditText) findViewById(R.id.edt_confirm_mobile_pin);

        preferenceManager = new PreferenceManager(MobilePin.this);
        intent = new Intent(MobilePin.this, MainActivity.class);

        btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_mobilePin.getText().toString().isEmpty() || edt_confirmPin.getText().toString().isEmpty()){
                    Snackbar.make(view, "Please all fields are required", Snackbar.LENGTH_LONG).show();
                }else {
                    if ((edt_mobilePin.getText().toString()).equals(edt_confirmPin.getText().toString())){
                        registerPin(edt_mobilePin.getText().toString());
                        preferenceManager.setFirstTimeLaunch(false);
                        preferenceManager.setCurrentUserAccount(Long.valueOf(accountNum));
                        launchHomeScreen();
                    }else {
                        Snackbar.make(view, "Pin does not match", Snackbar.LENGTH_LONG).show();
                    }

                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.left_to_right_page_switch, R.anim.right_to_left_page_switch);
    }

    private void launchHomeScreen() {
        preferenceManager.setFirstTimeLaunch(false);
        startActivity(intent);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        finish();
    }

    private void registerPin(String pin){
        accountNum = preedingIntent.getStringExtra("accountNum");
        //get reference to the database
        DatabaseReference myRef = database.getReference(String.valueOf(accountNum));

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("mobilePin", pin);

        //update the medications with the new values
        myRef.updateChildren(childUpdates);
    }

}
