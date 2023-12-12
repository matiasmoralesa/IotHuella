package com.example.iothuella;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    // Declarar instancias de clases necesarias
    private FingerprintScanner fingerprintScanner;
    private FirebaseConnector firebaseConnector;
    private EditText usernameEditTextR;
    private EditText passwordEditTextR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializar instancias de clases
        fingerprintScanner = new FingerprintScanner();
        firebaseConnector = new FirebaseConnector();

        // Inicializar vistas
        usernameEditTextR = findViewById(R.id.usernameEditTextR);
        passwordEditTextR = findViewById(R.id.passwordEditTextR);

        // Configurar evento de clic para el botón de registro
        Button registerButton = findViewById(R.id.registerButtonR);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUserWithFingerprint();
            }
        });
    }

    // Función para registrar al usuario con huella digital
    private void registerUserWithFingerprint() {
        String fingerprintData = fingerprintScanner.scanFingerprint();
        if (fingerprintData != null) {
            String userData = // Obtener detalles del usuario
                    firebaseConnector.saveUserData(userData, fingerprintData);
            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al registrar la huella", Toast.LENGTH_SHORT).show();
        }
    }
}
