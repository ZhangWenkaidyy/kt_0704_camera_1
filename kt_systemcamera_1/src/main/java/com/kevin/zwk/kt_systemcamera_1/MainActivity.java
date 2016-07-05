package com.kevin.zwk.kt_systemcamera_1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void startclick(View view) {
        //调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri uri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, 0x234);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0x234:
                    //缩略图
                    // Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    //imageView.setImageBitmap(bitmap);
                    try {

                        //存入系统的contentResolver
                        String path = MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), null, null);
                        //的到文件的Uri
                        Uri uri = Uri.parse(path);
                        //展示在imageview上
                        imageView.setImageURI(uri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    }

                    break;

            }
        }

    }
}

