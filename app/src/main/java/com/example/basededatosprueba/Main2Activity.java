package com.example.basededatosprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kyanogen.signatureview.SignatureView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class Main2Activity extends MainActivity {

    Bitmap bitmap;
    Button clear;
    Button btnAgregarUsuario;
    SignatureView signatureView;
    String path;
    private static final String IMAGE_DIRECTORY = "/signdemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        signatureView =  (SignatureView) findViewById(R.id.signature_view);
        clear = (Button) findViewById(R.id.Borrar);
        btnAgregarUsuario = (Button) findViewById(R.id.btnAgregarUsuario);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureView.clearCanvas();
            }
        });

        btnAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ejecutarServicio("https://ole-omx.mx/mariana/insertar_cliente.php");
                bitmap = signatureView.getSignatureBitmap();
                path = saveImage(bitmap);
                signatureView.clearCanvas();
                editNombrePersona.clearComposingText();
                editApellidPaterno.clearComposingText();
                editApellidoMaterno.clearComposingText();
                editCURP.clearComposingText();
                editNSS.clearComposingText();
                editRFC.clearComposingText();
                editFechaNacimiento.clearComposingText();
                editGenero.clearComposingText();
                editEstadoCivil.clearComposingText();
                editTelefono.clearComposingText();
                editCelular.clearComposingText();
                editEmail.clearComposingText();
                editCalle.clearComposingText();
                editNumeroExterior.clearComposingText();
                editNumeroInterior.clearComposingText();
                editColonia.clearComposingText();
                editDelegacion.clearComposingText();
                editEstado.clearComposingText();
                editNacionalidad.clearComposingText();
                editCodigoPostal.clearComposingText();
                editContactoEmergencia.clearComposingText();
                editTelefonoEmergencia.clearComposingText();


            }
        });

    }
    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY /*iDyme folder*/);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
            Log.d("hhhhh",wallpaperDirectory.toString());
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(Main2Activity.this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void ejecutarServicio (String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(), "Operacion Exitosa", Toast.LENGTH_SHORT).show();

            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("nombre",editNombrePersona.getText().toString());
                parametros.put("apellidoPaterno",editApellidPaterno.getText().toString());
                parametros.put("apellidoMaterno",editApellidoMaterno.getText().toString());
                parametros.put("curp",editCURP.getText().toString());
                parametros.put("nss",editNSS.getText().toString());
                parametros.put("rfc",editRFC.getText().toString());
                parametros.put("fechaNacimiento",editFechaNacimiento.getText().toString());
                parametros.put("genero",editGenero.getText().toString());
                parametros.put("estadoCivil",editEstadoCivil.getText().toString());
                parametros.put("telefono",editTelefono.getText().toString());
                parametros.put("celular",editCelular.getText().toString());
                parametros.put("email",editEmail.getText().toString());
                parametros.put("calle",editCalle.getText().toString());
                parametros.put("numeroExterior",editNumeroExterior.getText().toString());
                parametros.put("numeroInterior",editNumeroInterior.getText().toString());
                parametros.put("colonia",editColonia.getText().toString());
                parametros.put("delegacion",editDelegacion.getText().toString());
                parametros.put("estado",editEstado.getText().toString());
                parametros.put("nacionalidad",editNacionalidad.getText().toString());
                parametros.put("codigoPostal",editCodigoPostal.getText().toString());
                parametros.put("contactoDeEmergencia",editContactoEmergencia.getText().toString());
                parametros.put("telefonoDeEmergencia",editTelefonoEmergencia.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

