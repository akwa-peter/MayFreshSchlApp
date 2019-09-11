package ng.com.rad5.mayfreshmobile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    CardView cardTransfers;
    CardView cardAirtimeRecharge;
    CardView cardPayBills;
    CardView cardQRPayments;
    PreferenceManager preferenceManager;
    String currentAccountNum;
    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView txtAccountName;
    TextView txtAccountNum;
    TextView txtAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.MainActivityToolbar);
        setSupportActionBar(toolbar);

        txtAccountName = (TextView) findViewById(R.id.txt_accountName);
        txtAccountNum = (TextView) findViewById(R.id.txt_accountNum);
        txtAmount = (TextView) findViewById(R.id.txt_amount);
        preferenceManager = new PreferenceManager(MainActivity.this);
        currentAccountNum = String.valueOf(preferenceManager.getCurrentUserAccount());

        database = DatabaseUtil.getDatabase();
        myRef = database.getReference(currentAccountNum);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("debugger", "Current Account: " + dataSnapshot);
                String accountName = dataSnapshot.child("lastName").getValue() + " "
                        + dataSnapshot.child("firstName").getValue();
                String accounNum = String.valueOf(dataSnapshot.child("accountNum").getValue());
                String amount = String.valueOf(dataSnapshot.child("amount").getValue());

                txtAccountName.setText("Welcome, " + accountName);
                txtAccountNum.setText(accounNum);
                txtAmount.setText("NGN " + amount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        cardTransfers = (CardView) findViewById(R.id.card_transfers);
        cardTransfers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Transfers.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        cardAirtimeRecharge = (CardView) findViewById(R.id.card_mobileTransacts);
        cardAirtimeRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AirtimeRecharge.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        cardPayBills = (CardView) findViewById(R.id.card_PayBills);
        cardPayBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Pay Bills Clicked", Toast.LENGTH_LONG).show();
            }
        });

        cardQRPayments = (CardView) findViewById(R.id.card_QRPayments);
        cardQRPayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "QR Payments Clicked", Toast.LENGTH_LONG).show();
            }
        });

    }

    int c;

    @Override
    public void onBackPressed() {
        c++;

        if (c >= 2) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            super.onBackPressed();
        } else {
            Toast.makeText(this,"Tap Back once more to exit",Toast.LENGTH_LONG).show();
        }

    }

}
