package com.devs.sketchimagedemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.devs.imagetosketch.R;
import com.devs.sketchimage.SketchImage;

public class MainActivity extends AppCompatActivity  {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ImageView target;
    private Bitmap bmOriginal;
    private SketchImage sketchImage;
    private int MAX_PROGRESS = 100;
    private int effectType = SketchImage.ORIGINAL_TO_GRAY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bmOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.usr);
        //Bitmap bmOriginal = decodeSampledBitmapFromResource(getResources(), R.drawable.usr, 100, 100);

        target = (ImageView) findViewById(R.id.iv_target);
        target.setImageBitmap(bmOriginal);

        sketchImage = new SketchImage.Builder(this, bmOriginal).build();

        final SeekBar seek = (SeekBar) findViewById(R.id.simpleSeekBar);
        final ProgressBar pb = (ProgressBar) findViewById(R.id.pb);
        final TextView tvPB = (TextView) findViewById(R.id.tv_pb);

        tvPB.setText(String.format("%d %%", MAX_PROGRESS));
        seek.setMax(MAX_PROGRESS);
        seek.setProgress(MAX_PROGRESS);
        target.setImageBitmap(sketchImage.getImageAs(effectType,
                MAX_PROGRESS));

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Original to Gray"));
        tabLayout.addTab(tabLayout.newTab().setText("Original to Sketch"));
        tabLayout.addTab(tabLayout.newTab().setText("Original to Colored Sketch"));
        tabLayout.addTab(tabLayout.newTab().setText("Original to Soft Sketch"));
        tabLayout.addTab(tabLayout.newTab().setText("Original to Soft Color Sketch"));
        tabLayout.addTab(tabLayout.newTab().setText("Gray to Sketch"));
        tabLayout.addTab(tabLayout.newTab().setText("Gray to Colored Sketch"));
        tabLayout.addTab(tabLayout.newTab().setText("Gray to Soft Sketch"));
        tabLayout.addTab(tabLayout.newTab().setText("Gray to Soft Color Sketch"));
        tabLayout.addTab(tabLayout.newTab().setText("Sketch to Color Sketch"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                effectType = tabLayout.getSelectedTabPosition();
                tvPB.setText(String.format("%d %%", MAX_PROGRESS));
                seek.setMax(MAX_PROGRESS);
                seek.setProgress(MAX_PROGRESS);
                target.setImageBitmap(sketchImage.getImageAs(effectType,
                        MAX_PROGRESS));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvPB.setText(String.format("%d %%", seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                pb.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pb.setVisibility(View.INVISIBLE);
                target.setImageBitmap(sketchImage.getImageAs(effectType,
                        seekBar.getProgress()));
            }
        });

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

