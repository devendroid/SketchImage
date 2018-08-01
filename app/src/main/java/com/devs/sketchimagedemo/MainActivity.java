package com.devs.sketchimagedemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.devs.imagetosketch.R;
import com.devs.sketchimage.SketchImage;

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


        //Bitmap bmOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.usr);
        Bitmap bmOriginal = decodeSampledBitmapFromResource(getResources(), R.drawable.hd_img, 100, 100);

        target = (ImageView) findViewById(R.id.iv_targe);

        target.setImageBitmap(bmOriginal);

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


    /*
    For More Detail:
    https://developer.android.com/topic/performance/graphics/load-bitmap
    */
    private Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                   int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}

