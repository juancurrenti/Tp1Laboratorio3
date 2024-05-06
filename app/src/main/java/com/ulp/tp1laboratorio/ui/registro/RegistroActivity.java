package com.ulp.tp1laboratorio.ui.registro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ulp.tp1laboratorio.R;
import com.ulp.tp1laboratorio.model.Usuario;
import com.ulp.tp1laboratorio.request.SharedPreferencesManager;
import com.ulp.tp1laboratorio.ui.login.MainActivity;

public class RegistroActivity extends AppCompatActivity {
    private RegistroViewModel registroViewModel;
    private EditText emailET, passwordET, nombreET, apellidoET, dniET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        registroViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);

         emailET = findViewById(R.id.editTextEmail);
         passwordET = findViewById(R.id.editTextPassword);
         nombreET = findViewById(R.id.editTextNombre);
         apellidoET = findViewById(R.id.editTextApellido);
         dniET = findViewById(R.id.editTextDni);
        Button registerButton = findViewById(R.id.buttonRegister);
        CheckBox checkBoxShowPassword = findViewById(R.id.checkBoxShowPassword);

            if(getIntent().getBooleanExtra("cargarDatos", false)){
                cargarDatosUsuario();
            }
        registerButton.setOnClickListener(v -> {
            String email = emailET.getText().toString();
            String password = passwordET.getText().toString();
            String nombre = nombreET.getText().toString();
            String apellido =apellidoET.getText().toString();
            long dni = Long.parseLong(dniET.getText().toString());
            Usuario usuario = new Usuario(email, password, nombre, apellido, dni);
            registroViewModel.registrarUsuario(usuario);
            Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
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

        registroViewModel.getUsuarioGuardado().observe(this, guardado -> {
            if (guardado) {
                Toast.makeText(getApplicationContext(), "Usuario correctamente guardado!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Usuario no registrado.", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void cargarDatosUsuario(){
        Usuario usuario = SharedPreferencesManager.leer(this);
        if(usuario!=null){
            emailET.setText(usuario.getMail());
            passwordET.setText(usuario.getPassword());
            nombreET.setText(usuario.getNombre());
            apellidoET.setText(usuario.getApellido());
            dniET.setText(String.valueOf(usuario.getDni()));
        }
    }
}
