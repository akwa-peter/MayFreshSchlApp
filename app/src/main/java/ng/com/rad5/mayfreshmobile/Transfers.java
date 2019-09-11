package ng.com.rad5.mayfreshmobile;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class Transfers extends AppCompatActivity {

    Toolbar toolbar;

    CardView cardTransferToMayFresh;
    CardView cardTransferToOtherBank;

    PreferenceManager preferenceManager;
    String currentAccountNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfers);

        toolbar = (Toolbar) findViewById(R.id.TransfersToolbar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        preferenceManager = new PreferenceManager(Transfers.this);
        currentAccountNum = String.valueOf(preferenceManager.getCurrentUserAccount());

        cardTransferToMayFresh = (CardView) findViewById(R.id.btn_transferToMayFresh);
        cardTransferToMayFresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Transfers.this, TransferToMayFresh.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        cardTransferToOtherBank = (CardView) findViewById(R.id.btn_transferToOtherBank);
        cardTransferToOtherBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Transfers.this, TransferToOtherBank.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

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
