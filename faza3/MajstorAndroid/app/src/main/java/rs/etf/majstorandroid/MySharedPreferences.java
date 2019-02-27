package rs.etf.majstorandroid;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import rs.etf.majstorandroid.network.dto.UserDTO;

public class MySharedPreferences {
    private static final String TAG = MySharedPreferences.class.getSimpleName();

    private static final String MY_PREFS_NAME = "shared_preferences";

    private static final String SAVED_USER_KEY = "saved_user";

    private static MySharedPreferences instance = null;

    public static MySharedPreferences getInstance(Activity activity) {
        if (instance == null) {
            instance = new MySharedPreferences(activity);
        }

        return instance;
    }

    private SharedPreferences sharedPreferences = null;

    public MySharedPreferences(Activity activity) {
        sharedPreferences = activity.getApplicationContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public UserDTO getSavedUser() {
        try {
            String json = sharedPreferences.getString(SAVED_USER_KEY, null);
            if (json == null) {
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, UserDTO.class);
        } catch (Exception e) {
            Log.e(TAG, "Failed to get saved user", e);
            return null;
        }
    }

    public void saveUser(UserDTO userDTO) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(userDTO);
            sharedPreferences.edit().putString(SAVED_USER_KEY, json).apply();
        } catch (Exception e) {
            Log.e(TAG, "Failed to save user", e);
        }
    }

    public void deleteSavedUser() {
        try {
            sharedPreferences.edit().remove(SAVED_USER_KEY).apply();
        } catch (Exception e) {
            Log.e(TAG, "Failed to delete user", e);
        }
    }
}
