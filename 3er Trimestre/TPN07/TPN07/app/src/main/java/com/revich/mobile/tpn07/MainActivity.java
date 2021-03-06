package com.revich.mobile.tpn07;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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
import android.text.Html;
import android.util.Log;
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
import java.io.*;
import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.graphics.*;
import android.widget.*;
import android.provider.*;

import com.google.gson.Gson;
import com.microsoft.projectoxford.face.*;
import com.microsoft.projectoxford.face.contract.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class MainActivity extends AppCompatActivity {
    Button btnSacarFoto, btnSeleccionarFoto, btnVerEstadisticas;
    String mCurrentPhotoPath;
    ImageView imgFotoGaleria;
    SharedPreferences prefs;
    int CantHombres=0, CantMujeres=0, InvocacionesApi=0 ,CantVaronesSharedPreferences, CantMujeresSharedPreferences, InvocacionesApiSharedPreferences;
    ProgressDialog detectionProgressDialog;
    double EdadVarones=0, EdadMujeres=0,PromedioEdadVarones=0,PromedioEdadMujeres=0, EdadVaronesSharedPreferences,EdadMujeresSharedPreferences;
    Bitmap bitmap, bitmapConRectangulos;
    Boolean Sonrie=false;
    Uri tempUri;
    FaceServiceClient.FaceAttributeType [] faceAttributeTypes = new FaceServiceClient.FaceAttributeType [] {FaceServiceClient.FaceAttributeType.Age, FaceServiceClient.FaceAttributeType.Gender, FaceServiceClient.FaceAttributeType.Smile} ;
    private FaceServiceClient faceServiceClient =
            new FaceServiceRestClient("https://westcentralus.api.cognitive.microsoft.com/face/v1.0","b45ddadf2d904beab9fb0eb2edcf9adf");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ObtenerReferencias();
        detectionProgressDialog = new ProgressDialog(this);
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

            IrAActivityEstadisticas();

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
            bitmap = decodeSampledBitmapFromUri(data.getData(), imgFotoGaleria.getWidth(), imgFotoGaleria.getHeight());
            stream.close();
            tempUri = getImageUri(getApplicationContext(), bitmap);
            imgFotoGaleria.setImageBitmap(bitmap);
            DetectarFoto(bitmap);

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


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    private void DetectarFoto(final Bitmap imageBitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        final String RasgosABuscarFoto= "age, gender, smile";
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(outputStream.toByteArray());
        new detectTask().execute(inputStream);
    }

    private static Bitmap DibujarRectangulosEnFoto(Bitmap originalBitmap, Face[] faces) {
        Bitmap bitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        int stokeWidth = 2;
        paint.setStrokeWidth(stokeWidth);
        if (faces != null) {
            for (Face face : faces) {
                FaceRectangle faceRectangle = face.faceRectangle;
                canvas.drawRect(
                        faceRectangle.left,
                        faceRectangle.top,
                        faceRectangle.left + faceRectangle.width,
                        faceRectangle.top + faceRectangle.height,
                        paint);
            }
        }
        return bitmap;
    }

    private class detectTask extends  AsyncTask<InputStream, String, Face[]>
    {
        @Override
        protected Face[] doInBackground(InputStream... params) {
            try {
                Log.d("ProgressDialog", "AntesDeMostrar");
                publishProgress("Detecting...");
                Log.d("ProgressDialog", "DespuesDeMostrar");
                Face[] result = faceServiceClient.detect(
                        params[0],
                        true,         // returnFaceId
                        true,        // returnFaceLandmarks
                        faceAttributeTypes      // returnFaceAttributes: a string like "age, gender"
                );
                if (result == null)
                {
                    publishProgress("Detection Finished. Nothing detected");
                    return null;
                }
                publishProgress(
                        String.format("Detection Finished. %d face(s) detected",
                                result.length));
                return result;
            } catch (Exception e) {
                publishProgress("Detection failed");
                return null;
            }
        }
        @Override
        protected void onPreExecute() {
            //TODO: show progress dialog
        }
        @Override
        protected void onProgressUpdate(String... progress) {
            Log.d("ProgressDialog", "Mostrando");
            detectionProgressDialog.setMessage(progress[0]);
            detectionProgressDialog.show();
        }
        @Override
        protected void onPostExecute(Face[] result) {
            detectionProgressDialog.cancel();
            bitmapConRectangulos=DibujarRectangulosEnFoto(bitmap,result);
            imgFotoGaleria.setImageBitmap(bitmapConRectangulos);
            for(int i=0;i<result.length;i++)
            {
                InvocacionesApi++;
                Toast msg= Toast.makeText(getApplicationContext(),"Edad :"+String.valueOf(result[i].faceAttributes.age)+ Html.fromHtml("<br />") + "Genero :"+result[i].faceAttributes.gender + Html.fromHtml("<br />") + "Sonrisa :"+String.valueOf(result[i].faceAttributes.smile),Toast.LENGTH_SHORT);
                if (result[i].faceAttributes.gender.equals("male"))
                {
                    CantHombres++;
                    EdadVarones=EdadVarones+result[i].faceAttributes.age;
                }
                else
                {
                    CantMujeres++;
                    EdadMujeres=EdadMujeres+result[i].faceAttributes.age;
                }
                if(result[i].faceAttributes.smile>=1)
                {
                    Sonrie=true;
                }
                msg.show();
            }
            prefs = getSharedPreferences("EstadisticasFotos", MODE_PRIVATE);
            EdadVaronesSharedPreferences=prefs.getFloat("EdadVarones", -1);
            if(EdadVaronesSharedPreferences==-1)
            {
                InsertarSharedPreferences(EdadVarones,EdadMujeres,CantHombres,CantMujeres,Sonrie,InvocacionesApi);
            }
            else
            {
                LeerSharedPreferences();
                InsertarSharedPreferences(EdadVarones,EdadMujeres,CantHombres,CantMujeres,Sonrie,InvocacionesApi);
            }

        }
    };

    private void InsertarSharedPreferences(double edadVarones,double edadMujeres, int cantHombres, int cantMujeres, boolean sonrie, int invocacionesApi)
    {
        SharedPreferences.Editor editor = getSharedPreferences("EstadisticasFotos", MODE_PRIVATE).edit();
        editor.putFloat("EdadVarones", Float.parseFloat(String.valueOf(edadVarones)));
        editor.putFloat("EdadMujeres", Float.parseFloat(String.valueOf(edadMujeres)));
        editor.putInt("CantHombres",cantHombres);
        editor.putInt("CantMujeres",cantMujeres);
        editor.putBoolean("Sonrie",sonrie);
        editor.putInt("InvocacionesApi",invocacionesApi);
        editor.apply();
    }

    private  void LeerSharedPreferences ()
    {
        EdadMujeresSharedPreferences=prefs.getFloat("EdadMujeres",0);
        CantVaronesSharedPreferences=prefs.getInt("CantHombres",0);
        CantMujeresSharedPreferences=prefs.getInt("CantMujeres",0);
        InvocacionesApiSharedPreferences= prefs.getInt("InvocacionesApi",0);
        EdadVarones=EdadVarones+EdadVaronesSharedPreferences;
        EdadMujeres=EdadMujeres+EdadMujeresSharedPreferences;
        CantHombres=CantHombres+CantVaronesSharedPreferences;
        CantMujeres=CantMujeres+CantMujeresSharedPreferences;
        InvocacionesApi=InvocacionesApi+InvocacionesApiSharedPreferences;

    }

    private  void IrAActivityEstadisticas()
    {
        Intent intent = new Intent(MainActivity.this,Activity_Estadisticas.class);
        startActivity(intent);
        finish();
    }



    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float)height / (float)reqHeight);
            } else {
                inSampleSize = Math.round((float)width / (float)reqWidth);
            }
        }
        return inSampleSize;
    }

    public Bitmap decodeSampledBitmapFromUri(Uri uri, int reqWidth, int reqHeight) {

        Bitmap bm = null;

        try{
            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        return bm;
    }

}

