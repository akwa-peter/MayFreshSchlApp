package ng.com.rad5.mayfreshmobile;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    SharedPreferences preference;
    SharedPreferences.Editor editor;
    Context context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "androidhive-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    private static final String ACCOUNT_NUMBER = "accountNum";

    public PreferenceManager(Context context) {
        this.context = context;
        preference = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preference.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return preference.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setCurrentUserAccount(long accountNum){
        editor.putLong(ACCOUNT_NUMBER, accountNum);
        editor.commit();
    }

    public long getCurrentUserAccount(){
        return preference.getLong(ACCOUNT_NUMBER, 1);
    }

}
