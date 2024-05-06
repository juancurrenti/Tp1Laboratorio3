package com.ulp.tp1laboratorio.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;

import android.text.InputType;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ulp.tp1laboratorio.R;
import com.ulp.tp1laboratorio.ui.registro.RegistroActivity;
import com.ulp.tp1laboratorio.ui.registro.RegistroViewModel;

public class MainActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        EditText emailET = findViewById(R.id.editTextEmail);
        EditText passwordET = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.buttonLogin);
        Button registerButton = findViewById(R.id.buttonGoToRegister);
        CheckBox checkBoxShowPassword = findViewById(R.id.checkBoxShowPassword);


        loginButton.setOnClickListener(v -> {
            String email = emailET.getText().toString();
            String password = passwordET.getText().toString();
            loginViewModel.login(email, password);

        });
        registerButton.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
            startActivity(intent);
        });
        checkBoxShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
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
