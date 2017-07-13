package com.assenza.alejandro.myauctions;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.assenza.alejandro.myauctions.DbHandler.DbHandler;
import com.assenza.alejandro.myauctions.models.Login.SignUp;
import com.assenza.alejandro.myauctions.models.Login.User;

public class SignUpActivity extends AppCompatActivity {

    private EditText _user;
    private EditText _password;
    private Button _signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        _user = (EditText)findViewById(R.id.user);
        _password = (EditText)findViewById(R.id.password);
        _signUp = (Button)findViewById(R.id.signupButton);

        _signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });
    }

    public void SignUp() {

        String user = _user.getText().toString();
        String password = _password.getText().toString();

        if (!DataIsValid(user, password)) {
            OnSingUpFailed();
            return;
        }

        _signUp.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                                                     R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Registrando Usuario...");
        progressDialog.show();

        final long success = SignUp.TrySignUp(getApplicationContext(), new User(user, password));
        Log.d(">> ", String.valueOf(success));

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        if (success != DbHandler.USER_DOESNT_EXIST) {
                            Log.d(">>> ", String.valueOf(success));
                            OnSingUpSuccess(String.valueOf(success));
                        }
                        else
                            OnSingUpFailed();

                        progressDialog.dismiss();
                    }
                }, 1500);
    }

    public void OnSingUpSuccess(String userId) {
        _signUp.setEnabled(true);
        _user.setError(null);
        _password.setError(null);
        Log.d(">> ", userId);
        Intent intent = new Intent();
        intent.putExtra("id", userId);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void OnSingUpFailed() {
        _signUp.setEnabled(true);
        Toast.makeText(this, "No se pudo Registrar al Usuario", Toast.LENGTH_LONG).show();
    }

    private boolean DataIsValid(String user, String pass) {

        boolean success = true;

        if (user.isEmpty()) {
            _user.setError("Ingrese un Usuario");
            success = false;
        }

        if (pass.isEmpty()) {
            _password.setError("Ingrese una Contrasena");
            success = false;
        }

        return success;
    }

}
