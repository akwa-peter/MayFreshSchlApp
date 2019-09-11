package ng.com.rad5.mayfreshmobile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class TransferToMayFresh extends AppCompatActivity {

    Toolbar toolbar;

    PreferenceManager preferenceManager;
    String currentAccountNum;

    FirebaseDatabase database;
    DatabaseReference myRef;

    TextView txtAmount;
    Button btnTransfer;

    EditText edtAccountNum;
    EditText edtAmount;

    View view;

    String amount;
    int balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_may_fresh);

        toolbar = (Toolbar) findViewById(R.id.TranserToMayFreshActivityToolbar);
        setSupportActionBar(toolbar);

        view = (View) findViewById(R.id.transferToMayfreshLayout);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        preferenceManager = new PreferenceManager(TransferToMayFresh.this);
        currentAccountNum = String.valueOf(preferenceManager.getCurrentUserAccount());

        database = DatabaseUtil.getDatabase();
        myRef = database.getReference(currentAccountNum);

        edtAccountNum = (EditText) findViewById(R.id.edt_account_number);
        edtAmount = (EditText) findViewById(R.id.edt_amount);

        txtAmount = (TextView) findViewById(R.id.txt_amount);
        btnTransfer = (Button) findViewById(R.id.btn_transfer);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                amount = String.valueOf(dataSnapshot.child("amount").getValue());
                txtAmount.setText("NGN " + amount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtAccountNum.getText().toString().isEmpty() || edtAmount.getText().toString().isEmpty()){
                    Snackbar.make(view, "Please all fields are required", Snackbar.LENGTH_LONG).show();
                }else {

                    if (Integer.parseInt(edtAmount.getText().toString()) > Integer.parseInt(amount)){
                        Snackbar.make(view, "Insufficient balance to perform transaction", Snackbar.LENGTH_LONG).show();
                    }else {
                        balance = Integer.parseInt(amount) - Integer.parseInt(edtAmount.getText().toString());
                        setBalance(balance);
                        txtAmount.setText(String.valueOf(balance));
                        Toast.makeText(TransferToMayFresh.this, "Transaction Successful", Toast.LENGTH_LONG).show();
                    }

                }
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

    public void setBalance(int balance){
        //get reference to the database
        DatabaseReference myRef = database.getReference(String.valueOf(currentAccountNum));

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("mobilePin", balance);

        //update the medications with the new values
        myRef.updateChildren(childUpdates);
    }

}
