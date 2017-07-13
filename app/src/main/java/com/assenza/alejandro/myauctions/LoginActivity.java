package com.assenza.alejandro.myauctions;

/**
 * Created by alejandro on 09/07/17.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.assenza.alejandro.myauctions.models.Login.Login;
import com.assenza.alejandro.myauctions.models.Login.User;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_SIGNUP = 0;
    private EditText _user;
    private EditText _pass;
    private Button _loginButton;
    private TextView _signUp;
    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _user = (EditText)findViewById(R.id.login_user);
        _pass = (EditText)findViewById(R.id.login_pass);
        _loginButton = (Button)findViewById(R.id.login_button);
        _signUp = (TextView)findViewById(R.id.login_sigin);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TryLogin();
            }
        });

        _signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void TryLogin() {

        String user = _user.getText().toString();
        String password = _pass.getText().toString();

        if (!DataIsValid(user, password)) {
            OnLoginFailed();
            return;
        }

        this.user = new User(user, password);
        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                                            R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Iniciando Sesion...");
        progressDialog.show();

        final boolean success = Login.TryLogin(getApplicationContext(), this.user);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        if (success)
                            OnLoginSuccess();
                        else {
                            OnLoginFailed();
                            progressDialog.dismiss();
                        }
                    }
                }, 1500);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == Activity.RESULT_OK) {
                GoToHomepage();
            }
        }
    }

    private void GoToHomepage() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void OnLoginSuccess() {
        _loginButton.setEnabled(true);
        _user.setError(null);
        _pass.setError(null);
        GoToHomepage();
    }

    public void OnLoginFailed() {
        Toast.makeText(getBaseContext(), "No se pudo Iniciar Sesion, Usuario" +
                        " o Contrasena Invalida", Toast.LENGTH_LONG).show();
        this.user = null;
        _loginButton.setEnabled(true);
    }

    private boolean DataIsValid(String user, String pass) {

        boolean success = true;

        if (user.isEmpty()) {
            _user.setError("Ingrese un Usuario");
            success = false;
        }

        if (pass.isEmpty()) {
            _pass.setError("Ingrese una Contrasena");
            success = false;
        }

        return success;
    }
}

