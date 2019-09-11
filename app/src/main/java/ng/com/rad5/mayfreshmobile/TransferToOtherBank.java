package ng.com.rad5.mayfreshmobile;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class TransferToOtherBank extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_other_bank);

        toolbar = (Toolbar) findViewById(R.id.TransferToOtherBankToolbar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        //attach a list of months to the months spinner
        Spinner spinnerBanks = (Spinner) findViewById(R.id.spinner_bank);

        String[] months = {"Select Bank", "UBA", "GTB", "FIRST BANK", "STANBIC IBTC", "ECOBANK", "DIAMOND BANK", "KEYSTONE BANK", "FCMB"};

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, months);

        spinnerBanks.setAdapter(monthAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        overridePendingTransition(R.anim.left_to_right_page_switch, R.anim.right_to_left_page_switch);
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right_page_switch, R.anim.right_to_left_page_switch);
    }

}
