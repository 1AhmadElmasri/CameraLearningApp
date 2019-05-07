package com.example.visualprocessing;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.util.Log;

public class CameraClass {

    public static boolean checkCameraHardware(Context context) {//Detect the availability of a camera
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            Log.d("CAMERA", "Camera has been found");
            return true;
        } else {
            // no camera on this device
            Log.d("CAMERA", "No camera was found");
            return false;
        }
    }

    public static Camera getCameraInstance(){
        Camera c = null;
        int cameraId = 0;


        try {
            for(int i = 0; i < Camera.getNumberOfCameras(); i++){
                Camera.CameraInfo info = new Camera.CameraInfo();
                Camera.getCameraInfo(i, info);
                if(info.facing == Camera.CameraInfo.CAMERA_FACING_BACK){
                    cameraId = i;
                }
            }
            Log.d("////CAMERA","" + cameraId);

            c = Camera.open(cameraId); // attempt to get a Camera instance
            Log.d("\nCAMERA","Camera was opened successfully");

        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
            Log.d("////CAMERA","Camera could not be opened");
        }
        return c; // returns null if camera is unavailable
    }

}
