package ng.com.rad5.mayfreshmobile;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegisterActivity1 extends AppCompatActivity {

    Toolbar toolbar;

    EditText edt_cardNumber;
    EditText edt_cardPin;
    Button btnNext;

    View view;

    FirebaseDatabase database;
    DatabaseReference myRef;

    BankAccount bankAccount;
    ArrayList<BankAccount> bankAccounts;

    long accountNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        view = (View) findViewById(R.id.register_activity);

        database = DatabaseUtil.getDatabase();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edt_cardNumber = (EditText) findViewById(R.id.edt_card_number);
        edt_cardPin = (EditText) findViewById(R.id.edt_card_pin);

        bankAccount = new BankAccount();
        bankAccounts = new ArrayList<>();

        final Intent intent = new Intent(RegisterActivity1.this, MobilePin.class);

        btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_cardNumber.getText().toString().isEmpty() || edt_cardPin.getText().toString().isEmpty()){
                    Snackbar.make(view, "Please all fields are required", Snackbar.LENGTH_LONG).show();
                }else {

                    myRef = database.getReference();
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()){

                                bankAccount = ds.getValue(BankAccount.class);
                                bankAccounts.add(bankAccount);

                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

                for (int i = 0; i < bankAccounts.size(); i++) {
                    BankAccount account = bankAccounts.get(i);

                    if (account.getCardNum() == (Long.valueOf(edt_cardNumber.getText().toString()))
                            && account.getCardPin() == (Long.valueOf(edt_cardPin.getText().toString()))) {

                        Log.d("debugger", "account num: " + account.getAccountNum());
                        intent.putExtra("accountNum", String.valueOf(account.getAccountNum()));

                        startActivity(intent);
                        overridePendingTransition(R.anim.enter, R.anim.exit);

                    } else {
                        Snackbar.make(view, "Account Does Not Exist", Snackbar.LENGTH_LONG).show();
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
        finish();
        overridePendingTransition(R.anim.left_to_right_page_switch, R.anim.right_to_left_page_switch);
    }
}
