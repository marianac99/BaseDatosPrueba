package com.example.basededatosprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {

    EditText editNombresEmpresa,editLugar,editEmail,editTelefono,editFecha,editHoraLlegada,editHoraSalida;
    Button btnAgregarUsuario;
    //Button clear,save;
    Bitmap bitmap;
    SignatureView signatureView;
    String path;
    private static final String IMAGE_DIRECTORY = "/signdemo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signatureView =  (SignatureView) findViewById(R.id.signature_view);
        editNombresEmpresa=(EditText)findViewById(R.id.editNombresEmpresa);
        editLugar=(EditText)findViewById(R.id.editLugar);
        editEmail=(EditText)findViewById(R.id.editEmail);
        editTelefono=(EditText)findViewById(R.id.editTelefono);
        editFecha=(EditText)findViewById(R.id.editFecha);
        editHoraLlegada=(EditText)findViewById(R.id.editHoraLlegada);
        editHoraSalida=(EditText)findViewById(R.id.editHoraSalida);
        btnAgregarUsuario=(Button) findViewById(R.id.btnAgregarUsuario);

        //save = (Button) findViewById(R.id.save);

        //save.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //bitmap = signatureView.getSignatureBitmap();
                //path = saveImage(bitmap);
                //signatureView.clearCanvas();
            //}
        //});

        btnAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ejecutarServicio("http://ole-omx.mx/mariana/insertar_cliente.php");
                bitmap = signatureView.getSignatureBitmap();
                path = saveImage(bitmap);
                signatureView.clearCanvas();

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
            MediaScannerConnection.scanFile(MainActivity.this,
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
                parametros.put("nombreEmpresa",editNombresEmpresa.getText().toString());
                parametros.put("lugar",editLugar.getText().toString());
                parametros.put("email",editEmail.getText().toString());
                parametros.put("telefono",editTelefono.getText().toString());
                parametros.put("fecha",editFecha.getText().toString());
                parametros.put("horaLlegada",editHoraLlegada.getText().toString());
                parametros.put("horaSalida",editHoraSalida.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
