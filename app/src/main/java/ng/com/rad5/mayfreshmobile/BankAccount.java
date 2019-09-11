package ng.com.rad5.mayfreshmobile;

import android.support.annotation.Nullable;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class BankAccount {

    private long accountNum;
    private long amount;
    private long cardNum;
    private long cardPin;
    private String firstName;
    private String lastName;
    private String mobilePin;

    public BankAccount() {
    }

    public BankAccount(long amount, long accountNum, long cardNum, long cardPin, String firstName, String lastName, @Nullable String mobilePin) {
        this.cardNum = cardNum;
        this.amount = amount;
        this.cardPin = cardPin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNum = accountNum;
    }

    public long getCardNum() {
        return cardNum;
    }

    public long getCardPin() {
        return cardPin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getAccountNum() {
        return accountNum;
    }

    public long getAmount() {
        return amount;
    }

    public String getMobilePin() {
        return mobilePin;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("accountNum", accountNum);
        result.put("cardNum", cardNum);
        result.put("cardPin", cardPin);
        result.put("firstName", firstName);
        result.put("lastName", lastName);
        return result;
    }
}
