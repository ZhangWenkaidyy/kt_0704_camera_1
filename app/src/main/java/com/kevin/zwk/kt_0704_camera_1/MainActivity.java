package com.kevin.zwk.kt_0704_camera_1;


import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    private SurfaceView surfaceView;
    private Camera camera;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
    }

    public void click(View view) {
        camera = Camera.open();
        try {
            camera.setPreviewDisplay(surfaceHolder);
            Camera.Parameters parameters = camera.getParameters();
            camera.setParameters(parameters);
            camera.setDisplayOrientation(90);
            camera.startPreview();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void paiclick(View view) {
        camera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(data);
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                camera.startPreview();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    public void jujiaoclick(View view) {
        camera.autoFocus(new Camera.AutoFocusCallback(){
            @Override
            // 聚焦返回的方法
            public void onAutoFocus(boolean success, Camera camera) {
                Toast.makeText(MainActivity.this, "已聚焦", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void shanguangclidck(View view) {
        Camera.Parameters parameters=camera.getParameters();
        //获取闪光的模式
        String mode=parameters.getFlashMode();
        Toast.makeText(MainActivity.this, "闪光已开"+mode, Toast.LENGTH_SHORT).show();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
        camera.setParameters(parameters);
    }
}
