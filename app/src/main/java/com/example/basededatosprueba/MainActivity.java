package com.example.basededatosprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    EditText editNombrePersona,editApellidPaterno,editApellidoMaterno,editCURP,editNSS,editRFC,editFechaNacimiento,editGenero,editEstadoCivil,editTelefono,
            editCelular,editEmail,editCalle,editNumeroExterior,editNumeroInterior,editColonia,editDelegacion,editEstado,editNacionalidad,editCodigoPostal,
            editContactoEmergencia,editTelefonoEmergencia;

    Button btnSiguiente;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNombrePersona=(EditText)findViewById(R.id.editNombrePersona);
        editApellidPaterno=(EditText)findViewById(R.id.editApellidPaterno);
        editApellidoMaterno=(EditText)findViewById(R.id.editApellidoMaterno);
        editCURP=(EditText)findViewById(R.id.editCURP);
        editNSS=(EditText)findViewById(R.id.editNSS);
        editRFC=(EditText)findViewById(R.id.editRFC);
        editFechaNacimiento=(EditText)findViewById(R.id.editFechaNacimiento);
        editGenero=(EditText)findViewById(R.id.editGenero);
        editEstadoCivil=(EditText)findViewById(R.id.editEstadoCivil);
        editTelefono=(EditText)findViewById(R.id.editTelefono);
        editCelular=(EditText)findViewById(R.id.editCelular);
        editEmail=(EditText)findViewById(R.id.editEmail);
        editCalle=(EditText)findViewById(R.id.editCalle);
        editNumeroExterior=(EditText)findViewById(R.id.editNumeroExterior);
        editNumeroInterior=(EditText)findViewById(R.id.editNumeroInterior);
        editColonia=(EditText)findViewById(R.id.editColonia);
        editDelegacion=(EditText)findViewById(R.id.editDelegacion);
        editEstado=(EditText)findViewById(R.id.editEstado);
        editNacionalidad=(EditText)findViewById(R.id.editNacionalidad);
        editCodigoPostal=(EditText)findViewById(R.id.editCodigoPostal);
        editContactoEmergencia=(EditText)findViewById(R.id.editContactoEmergencia);
        editTelefonoEmergencia=(EditText)findViewById(R.id.editTelefonoEmergencia);
        btnSiguiente=(Button)findViewById(R.id.btnSiguiente);



        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);

            }
        });






    }




}
