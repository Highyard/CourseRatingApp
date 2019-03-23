package com.example.courseratingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.example.courseratingapp.domain.User;

public class CreateUserActivity extends AppCompatActivity {

    private final static String TAG = "CreateUserActivity";


    private EditText email, password;
    private Button signUp;

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        Log.d(TAG, getString(R.string.onCreate));
        init();

        Context context = getApplication();
        final SharedPreferences sharedPreferences = context.getSharedPreferences(getString(R.string.shared_preferences_create_user_key), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, getString(R.string.onClick));

                Intent intent = new Intent();

                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());

                // We check if user already exists in our SharedPreferences //
                String userDetails = sharedPreferences.getString(user.getEmail() + user.getPassword(), "");
                if (userDetails.isEmpty()) {

                    // We put the user object as Extra to the Intent.
                    // This is used to set the login for the user when he returns to the MainActivity //
                    intent.putExtra(getString(R.string.new_user), user);


                    // For the sake of this assignment the login information will be stored in plaintext,
                    // and the key will directly correspond to the value //

                    // Store the KEY as a combination of EMAIL and PASSWORD //
                    editor.putString(
                            // KEY //
                            user.getEmail() + user.getPassword(),
                            //VALUE //
                            user.getEmail() + user.getPassword());
                    editor.apply();
                    setResult(RESULT_OK, intent);
                } else if (userDetails.contains(email.getText())){
                    Toast.makeText(CreateUserActivity.this, getString(R.string.user_already_exists), Toast.LENGTH_LONG).show();
                }
                // Destroy activity and free it from memory //
                CreateUserActivity.this.finish();
            }
        });



    }



    protected void init(){
        Log.d(TAG, getString(R.string.init));
        email = findViewById(R.id.et1);
        password = findViewById(R.id.et2);
        signUp = findViewById(R.id.b1);
    }
}