package com.example.movieapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.R;
import com.example.movieapp.database.DataBaseHelper;
import com.example.movieapp.network.SharedPrefManager;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.BindView;

public class SignupActivity extends AppCompatActivity {
    DataBaseHelper mydb = new DataBaseHelper(SignupActivity.this,"User", null,4);
    private static final String TAG = "SignupActivity";
    SharedPrefManager sharedPrefManager;

    @BindView(R.id.input_name) EditText _nameText;
    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_gender)
    Spinner _genderText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.input_passwordConfirm) EditText _confirmPassword;
    @BindView(R.id.btn_signup) Button _signupButton;
    @BindView(R.id.input_phone) EditText _phoneText;
    @BindView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sharedPrefManager =SharedPrefManager.getInstance(this);
        ButterKnife.bind(this);
        ArrayList<String> genderList = new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genderList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _genderText.setAdapter(arrayAdapter);


        _signupButton.setOnClickListener(v -> signup());

        _loginLink.setOnClickListener(v -> {
            // Finish the registration screen and return to the Login activity
            finish();
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String gender = _genderText.getSelectedItem().toString();
        String password = _passwordText.getText().toString();
        String confirmPassword = _confirmPassword.getText().toString();
        String phone  = _phoneText.getText().toString();
        // TODO: Insert the hashed password instead !
        // Before inserting to database make sure that this user doesn't really exists
        Cursor c = mydb.getUserInfo(email);
        // if user doesn't exist then create a new one
        if (c == null ){
            onSignupSuccess();
            mydb.insert(email, name, gender, password, phone);
            Toast toast =Toast.makeText(SignupActivity.this, "Successfuly Signed up",Toast.LENGTH_LONG);
            toast.show();
            sharedPrefManager.writeString("email",email);
            sharedPrefManager.writeString("password",password);
            Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
            intent.putExtra("email",sharedPrefManager.readString("email","noValue"));
            SignupActivity.this.startActivity(intent);
            finish();
        }else{
            onSignupFailed();
        }

    }

    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "User already exists, please login instead", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String gender = _genderText.getSelectedItem().toString();
        String password = _passwordText.getText().toString();
        String confirmPassword = _confirmPassword.getText().toString();
        String phone  = _phoneText.getText().toString();

        if (name.isEmpty() || email.isEmpty() || gender == null || _genderText == null || password.isEmpty() ||
                confirmPassword.isEmpty() || phone.isEmpty())
        {
            _nameText.setError("Required.");
            _emailText.setError("Required.");
            _passwordText.setError("Required.");
            _confirmPassword.setError("Required.");
            _phoneText.setError("Required.");
            valid = false;
        }
        else
        {
            if (name.isEmpty() || name.length() < 3) {
                _nameText.setError("at least 3 characters");
                valid = false;
            } else {
                _nameText.setError(null);
            }

            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                _emailText.setError("enter a valid email address");
                valid = false;
            } else {
                _emailText.setError(null);
            }

            if (password.isEmpty() || password.length() < 3 || checkPass(password)==false) {
                _passwordText.setError("should be more than 3 alphanumeric characters");
                valid = false;
            } else {
                _passwordText.setError(null);
            }

            if (!confirmPassword.equals(password)){
                _confirmPassword.setError("Password doesn't match");
                valid = false;
            } else {
                _confirmPassword.setError(null);
            }

            if (phone.isEmpty() || phone.length() < 14 || !phone.substring(0,6).equals("009705")){
                _phoneText.setError("enter a valid phone number");
                valid = false;
            } else {
                _phoneText.setError(null);
            }
        }
        return valid;
    }

    private static boolean checkPass(String str) {
        char ch;
        String specialCharacters=" !#$%&'()*+,-./:;<=>?@[]^_`{|}";
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        boolean specialFlag = false;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            } else if (specialCharacters.contains(Character.toString(ch)))
                specialFlag = true;
            if(numberFlag && capitalFlag && lowerCaseFlag && specialFlag)
                return true;
        }
        return false;
    }
}