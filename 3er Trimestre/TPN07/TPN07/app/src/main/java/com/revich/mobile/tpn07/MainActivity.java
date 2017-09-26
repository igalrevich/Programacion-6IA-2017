package com.revich.mobile.tpn07;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnSacarFoto, btnSeleccionarFoto, btnVerEstadisticas;
    String mCurrentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ObtenerReferencias();
        SetearListeners();
    }

    private void ObtenerReferencias() {
        btnSacarFoto = (Button) findViewById(R.id.btnSacarFoto);
        btnSeleccionarFoto = (Button) findViewById(R.id.btnSeleccionarFoto);
        btnVerEstadisticas = (Button) findViewById(R.id.btnVerEstadisticas);
    }

    private void SetearListeners() {
        btnSacarFoto.setOnClickListener(btnSacarFoto_click);
        btnSeleccionarFoto.setOnClickListener(btnSeleccionarFoto_click);
        btnVerEstadisticas.setOnClickListener(btnVerEstadisticas_click);
    }

    private View.OnClickListener btnSacarFoto_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          IntentSacarFoto();
        }

    };

    private View.OnClickListener btnSeleccionarFoto_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private View.OnClickListener btnVerEstadisticas_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private File CrearArchivoDeImagen() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void IntentSacarFoto() {
        int REQUEST_TAKE_PHOTO = 1;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = CrearArchivoDeImagen();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.revich.mobile.tpn07",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast msg;
        if (resultCode == RESULT_OK)
        {
            switch (requestCode) {
                case 1:
                    galleryAddPic();
                    msg = Toast.makeText(getApplicationContext(), "La foto se saco con exito", Toast.LENGTH_SHORT);
                    msg.show();
                    break;
            }
        }
        else
        {
            msg = Toast.makeText(getApplicationContext(), "Ocurrio un error", Toast.LENGTH_SHORT);
            msg.show();
        }

    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

}

