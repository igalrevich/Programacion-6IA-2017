package com.revich.mobile.tpn07;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class MainActivity extends AppCompatActivity {
    Button btnSacarFoto, btnSeleccionarFoto, btnVerEstadisticas;
    String mCurrentPhotoPath;
    ImageView imgFotoGaleria;
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
        imgFotoGaleria = (ImageView) findViewById(R.id.imgFotoGaleria);
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

            int PICK_IMAGE=2;

            Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
            getIntent.setType("image/*");

            Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickIntent.setType("image/*");

            Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

            startActivityForResult(chooserIntent, PICK_IMAGE);

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
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE,"FotoTP7.jpg");
            Uri ContentUri= getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,ContentUri);
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            /*File photoFile = null;
            try
            {
                photoFile = CrearArchivoDeImagen();
            }
            catch (IOException ex)
            {

                ex.printStackTrace();
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.revich.mobile.tpn07",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }*/
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
                    msg = Toast.makeText(getApplicationContext(), "La foto se saco con exito", Toast.LENGTH_SHORT);
                    msg.show();
                    break;
                case 2:
                    PonerFotoEnImageView(data);
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

    public static void addImageToGallery(final String filePath, final Context context) {

        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.MediaColumns.DATA, filePath);

        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    private void PonerFotoEnImageView(Intent data)
    {
        try
        {
            InputStream stream = getContentResolver().openInputStream(data.getData());
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            stream.close();
            imgFotoGaleria.setImageBitmap(bitmap);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}

