package com.devs.sketchimagedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.devs.imagetosketch.R;
import com.devs.sketchimage.SketchImage;

// https://github.com/yaa110/Effects-Pro?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=1299
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ImageView target;
    private Bitmap bmOriginal;
    private SketchImage sketchImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.btn_sketch)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_blur)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_gray)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_invert)).setOnClickListener(this);

        Bitmap bmOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.usr);
        target = (ImageView) findViewById(R.id.iv_targe);

        sketchImage = new SketchImage.Builder(this, bmOriginal).build();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sketch:
                target.setImageBitmap(sketchImage.getImageAs(SketchImage.SKETCH));
                break;

            case R.id.btn_gray:
                target.setImageBitmap(sketchImage.getImageAs(SketchImage.GRAY));
                break;

            case R.id.btn_blur:
                target.setImageBitmap(sketchImage.getImageAs(SketchImage.BLUR));
                break;

            case R.id.btn_invert:
                target.setImageBitmap(sketchImage.getImageAs(SketchImage.INVERT));
                break;
        }
    }

}

