package com.example.visualprocessing;

import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class PictureCallBack implements Camera.PictureCallback{
    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        File pictureFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (pictureFile == null){
            Log.d("STORAGE", "Error creating media file, check storage permissions");
            return;
        }

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(data);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("STORAGE", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("STORAGE", "Error accessing file: " + e.getMessage());
        }
    }
}
