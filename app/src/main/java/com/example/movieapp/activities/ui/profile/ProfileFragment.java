package com.example.movieapp.activities.ui.profile;

import androidx.annotation.BoolRes;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.activities.HomeActivity;
import com.example.movieapp.activities.LoginActivity;
import com.example.movieapp.database.DataBaseHelper;

import butterknife.BindView;

public class ProfileFragment extends Fragment {

    @BindView(R.id.profile_editName)
    EditText _name;
    @BindView(R.id.profile_editEmail)
    TextView _email;
    @BindView(R.id.profile_editGender)
    TextView _gender;
    @BindView(R.id.profile_editPhone)
    TextView _phone;
    @BindView(R.id.profile_editPassword)
    EditText _passwordEdit;
    @BindView(R.id.profile_editNewPassword)
    EditText _newPasswordEdit;
    @BindView(R.id.profile_editConfirmPassword)
    EditText _confirmPasswordEdit;
    @BindView(R.id.profile_updateButton)
    Button _updateButton;
    @BindView(R.id.profile_password)
    TextView _password;
    @BindView(R.id.profile_newPassword)
    TextView _newPassword;
    @BindView(R.id.profile_confirmPassword)
    TextView _confirmPassword;
    @BindView(R.id.profile_checkBox)
    CheckBox _checkBox;
    boolean flag = false;

    private ProfileViewModel mViewModel;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        _email = view.findViewById(R.id.profile_editEmail);
        _name = view.findViewById(R.id.profile_editName);
        _gender = view.findViewById(R.id.profile_editGender);
        _phone = view.findViewById(R.id.profile_editPhone);
        _password = view.findViewById(R.id.profile_password);
        _newPassword = view.findViewById(R.id.profile_newPassword);
        _confirmPassword = view.findViewById(R.id.profile_confirmPassword);
        _passwordEdit = view.findViewById(R.id.profile_editPassword);
        _newPasswordEdit = view.findViewById(R.id.profile_editNewPassword);
        _confirmPasswordEdit = view.findViewById(R.id.profile_editConfirmPassword);
        _checkBox = view.findViewById(R.id.profile_checkBox);
        _updateButton = view.findViewById(R.id.profile_updateButton);
        String email = HomeActivity.shared;
        _email.setText(email);
        DataBaseHelper mydb = new DataBaseHelper(getContext(),"User", null,4);
         Cursor c = mydb.getUserInfo(HomeActivity.shared);
         c.moveToFirst();
        _name.setText(c.getString(c.getColumnIndex("NAME")));
        _gender.setText(c.getString(c.getColumnIndex("GENDER")));
        _phone.setText(c.getString(c.getColumnIndex("PHONE")));
        _password.setVisibility(view.GONE);
        _passwordEdit.setVisibility(view.GONE);
        _newPassword.setVisibility(view.GONE);
        _newPasswordEdit.setVisibility(view.GONE);
        _confirmPassword.setVisibility(view.GONE);
        _confirmPasswordEdit.setVisibility(view.GONE);

        _checkBox.setOnClickListener(view12 -> {
            flag = !flag; // flag is true == meaning the check box is checked
             if (flag == true){
                 _password.setVisibility(view12.VISIBLE);
                 _passwordEdit.setVisibility(view12.VISIBLE);
                 _newPassword.setVisibility(view12.VISIBLE);
                 _newPasswordEdit.setVisibility(view12.VISIBLE);
                 _confirmPassword.setVisibility(view12.VISIBLE);
                 _confirmPasswordEdit.setVisibility(view12.VISIBLE);
             } else {
                 _passwordEdit.setText("");
                 _newPasswordEdit.setText("");
                 _confirmPasswordEdit.setText("");
                 _password.setVisibility(view.GONE);
                 _passwordEdit.setVisibility(view.GONE);
                 _newPassword.setVisibility(view.GONE);
                 _newPasswordEdit.setVisibility(view.GONE);
                 _confirmPassword.setVisibility(view.GONE);
                 _confirmPasswordEdit.setVisibility(view.GONE);

             }
        });

        _updateButton.setOnClickListener(view1 -> {
            if (!_checkBox.isChecked()){
                if (!validateName()){
                    Toast.makeText(getContext(), "at least 3 characters", Toast.LENGTH_LONG).show();
                } else
                    mydb.updateUserName("User", email, _name.getText().toString());
            }
            else {
                if (!validateName())
                    Toast.makeText(getContext(), "at least 3 characters", Toast.LENGTH_LONG).show();
                else
                    mydb.updateUserName("User", email, _name.getText().toString());

                if (!validatePasswords())
                    Toast.makeText(getContext(), "should be more than 3 alphanumeric characters", Toast.LENGTH_LONG).show();
                else{
                    mydb.updatePassword("User", email, _newPassword.getText().toString());
                    Toast.makeText(getContext(), "Information Updated", Toast.LENGTH_LONG).show();
                  /*  _passwordEdit.setText("");
                    _newPassword.setText("");
                    _confirmPassword.setText("");
                    _password.setVisibility(view.GONE);
                    _passwordEdit.setVisibility(view.GONE);
                    _newPassword.setVisibility(view.GONE);
                    _newPasswordEdit.setVisibility(view.GONE);
                    _confirmPassword.setVisibility(view.GONE);
                    _confirmPasswordEdit.setVisibility(view.GONE); */
                }


            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
      /*  mViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                _name.setText(s);
            }
        });*/
        // TODO: Use the ViewModel
    }

    public boolean validatePasswords() {
        boolean valid = true;

        DataBaseHelper mydb = new DataBaseHelper(getContext(),"User", null,4);
        Cursor c = mydb.getUserInfo(HomeActivity.shared);
        c.moveToFirst();
        String passwordDB = c.getString(c.getColumnIndex("PASSWORD"));

        String password  = _passwordEdit.getText().toString();
        String newPassword = _newPassword.getText().toString();
        String conformPassword = _confirmPassword.getText().toString();


        if (password.isEmpty() || !password.equals(passwordDB)) {
            _passwordEdit.setError("Wrong Password");
            valid = false;
        } else {
            _passwordEdit.setError(null);
        }

        if (newPassword.isEmpty() || newPassword.length() < 3 || checkPass(newPassword)==false) {
            _newPasswordEdit.setError("Empty or less than 3 characters");
            valid = false;
        } else {
            _newPasswordEdit.setError(null);
        }

        if (!conformPassword.equals(newPassword)){
            _confirmPassword.setError("Password doesn't match");
            valid = false;
        } else {
            _confirmPassword.setError(null);
        }

        return valid;
    }

    public boolean validateName() {
        boolean valid = true;

        String name = _name.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _name.setError("at least 3 characters");
            valid = false;
        } else {
            _name.setError(null);
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