package com.example.visualprocessing;

import android.Manifest;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class MainActivity extends AppCompatActivity {



    private Camera camera = null;
    private boolean cameraFound = false;
    private boolean cameraOpen = false;
    private CameraPreview cameraPreview;
    private FrameLayout preview;
    private PictureCallBack picture;
    private Button captureButton;

    private class Listener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            camera.takePicture(null, null, picture);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);//Request permission to use the camera
        //Set the display of the camera to portrait by default
        //NOTE: Implement landscape mode later

        captureButton = findViewById(R.id.capture_button);//Creating the button and setting its listener
        Listener listener = new Listener();
        captureButton.setOnClickListener(listener);


        cameraFound = CameraClass.checkCameraHardware(this);//This will check whether or not a camera is available to use
        if(cameraFound){
            camera = CameraClass.getCameraInstance();
            if(camera != null){
                camera.setDisplayOrientation(0);
                cameraPreview = new CameraPreview(this, camera);
                preview = (FrameLayout) findViewById(R.id.camera_preview);
                preview.addView(cameraPreview);
            }
        }





    }



}
