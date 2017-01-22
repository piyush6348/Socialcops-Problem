package socialcops.piyush6348.com.socialcopsproblem.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.User;

import socialcops.piyush6348.com.socialcopsproblem.MainApplication;
import socialcops.piyush6348.com.socialcopsproblem.R;
import socialcops.piyush6348.com.socialcopsproblem.utils.Constants;

public class StartingActivity extends AppCompatActivity {
    public static String TAG;
    private Button userSignUp, userLogin;
    private EditText userName, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        TAG = this.getClass().getSimpleName();

        userName = (EditText) findViewById(R.id.user_name);
        userPassword = (EditText) findViewById(R.id.user_password);
        userSignUp = (Button) findViewById(R.id.user_signup);
        userLogin = (Button) findViewById(R.id.user_login);
        final Intent intent = new Intent(StartingActivity.this, CameraActivity.class);
        SharedPreferences prefs = getSharedPreferences(Constants.SHARED_PREFS_NAME, MODE_PRIVATE);
        String authStr = prefs.getString("auth", null);
        if (authStr == "done") {
            startActivity(intent);
            finish();
        }

        if (MainApplication.getClient().user().isUserLoggedIn()) {
            startActivity(intent);
            finish();
        }
        userSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (check(userName, userPassword)) {
                    MainApplication.getClient().user().create(userName.getText().toString(),
                            userPassword.getText().toString(), new KinveyUserCallback() {
                                @Override
                                public void onSuccess(User user) {
                                    CharSequence text = user.getUsername() + ", your account has been created.";
                                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                                    editSharedPreference(userName, userPassword);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onFailure(Throwable throwable) {
                                    CharSequence text = "Already registered";
                                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                                    if (MainApplication.getClient().user().isUserLoggedIn()) {
                                        editSharedPreference(userName, userPassword);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                } else
                    Toast.makeText(StartingActivity.this, "Please enter a valid input", Toast.LENGTH_SHORT).show();
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (check(userName, userPassword)) {
                    MainApplication.getClient().user().login(userName.getText().toString(),
                            userPassword.getText().toString(), new KinveyUserCallback() {
                                @Override
                                public void onSuccess(User user) {
                                    CharSequence text = "Welcome back," + user.getUsername() + ".";
                                    editSharedPreference(userName, userPassword);
                                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onFailure(Throwable throwable) {
                                    CharSequence text = "Wrong username or password.";
                                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                                }
                            });
                } else
                    Toast.makeText(StartingActivity.this, "Please enter a valid input", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void editSharedPreference(EditText userName, EditText userPassword) {
        SharedPreferences.Editor editor = getSharedPreferences(Constants.SHARED_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("auth", "done");
        editor.putString("username", userName.getText().toString());
        editor.putString("userpassword", userPassword.getText().toString());
        editor.commit();
    }

    boolean check(EditText username, EditText password) {
        if (username.getText().toString().trim().length() != 0 &&
                password.getText().toString().trim().length() != 0)
            return true;
        else
            return false;
    }
}
