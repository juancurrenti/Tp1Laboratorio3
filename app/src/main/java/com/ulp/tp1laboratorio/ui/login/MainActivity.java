package com.ulp.tp1laboratorio.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.content.Intent;

import android.text.InputType;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ulp.tp1laboratorio.R;
import com.ulp.tp1laboratorio.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        EditText emailET = findViewById(R.id.etEmail);
        EditText passwordET = findViewById(R.id.etPassword);
        Button loginBtn = findViewById(R.id.btnLogin);
        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        CheckBox cbPassword = findViewById(R.id.cbPassword);


        loginBtn.setOnClickListener(v -> {
            String email = emailET.getText().toString();
            String password = passwordET.getText().toString();
            loginViewModel.login(email, password);

        });
        btnRegistrar.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
            startActivity(intent);
        });
        cbPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            passwordET.setSelection(passwordET.getText().length());
        });


        loginViewModel.getUsuarioMutableLiveData().observe(this, usuario -> {
            if (usuario != null) {
                Toast.makeText(getApplicationContext(), "Usuario correctamente logueado!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                intent.putExtra("cargarDatos", true);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(getApplicationContext(), "Datos incorrectos o usuario no registrado.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
