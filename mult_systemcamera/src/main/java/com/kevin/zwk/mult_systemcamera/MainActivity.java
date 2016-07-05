package com.kevin.zwk.mult_systemcamera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> mSelectPath = new ArrayList<>();
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.textView);
    }

    public void click(View view) {
        MultiImageSelector multiImageSelector = MultiImageSelector.create();
        //是否显示相机
        multiImageSelector.showCamera(true);
        //设置多选
        multiImageSelector.multi();
        //多选9张
        multiImageSelector.count(9);
        //选择的路径放list里
        multiImageSelector.origin(mSelectPath);
        //跳转下一页
        multiImageSelector.start(MainActivity.this,20);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 20:
                    List<String> paths =
                            data.getStringArrayListExtra
                                    (MultiImageSelector.EXTRA_RESULT);
                    for (String path : paths) {
                        Toast.makeText(MainActivity.this, path, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }

    }
}
