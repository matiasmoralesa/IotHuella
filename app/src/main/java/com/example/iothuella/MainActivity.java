package com.example.iothuella;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declarar instancias de clases necesarias
    private FingerprintScanner fingerprintScanner;
    private FirebaseConnector firebaseConnector;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView forgotPasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar instancias de clases
        fingerprintScanner = new FingerprintScanner();
        firebaseConnector = new FirebaseConnector();

        // Inicializar vistas
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);

        // Configurar evento de clic para el botón de inicio de sesión
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserWithFingerprint();
            }
        });

        // Configurar evento de clic para el enlace de olvidar la contraseña
        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementar lógica para manejar olvido de contraseña
            }
        });
    }

    // Función para iniciar sesión del usuario con huella digital
    private void loginUserWithFingerprint() {
        String fingerprintData = fingerprintScanner.scanFingerprint();
        if (fingerprintData != null) {
            String userData = firebaseConnector.authenticateUser(fingerprintData);
            if (userData != null) {
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al ingresar", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Error con el lector de huellas", Toast.LENGTH_SHORT).show();
        }
    }
}
