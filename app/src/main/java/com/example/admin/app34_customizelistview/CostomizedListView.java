package com.example.admin.app34_customizelistview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by admin on 3/2/2018.
 */

public class CostomizedListView extends BaseAdapter{

    Context context;                  // see documentation
    LayoutInflater layoutInflater;    // inflates customized_list_view.xml to content_main.xml


    // array of id of resources in this project
    int[] images = {R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,
                    R.drawable.dice5,R.drawable.dice6,R.drawable.happy,R.drawable.sad};

    String[] names = {"one", "two", "three", "four", "five", "six", "happy", "sad"};
    int[] power = {10, 20, 30, 40, 50, 60, 70, 80};
    int[] speed = {100, 200, 300, 400, 500, 600, 700, 800};

    CostomizedListView(Context context){
        this.context = context;

        // this is how you get layout inflater service.Initiating layoutInflater object
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);


    }


    // abstract methods of BaseAdapter class
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return images[i];
    }

    @Override
    public long getItemId(int i) {
        return i;      // item id is position of image object in array
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.costomized_list_view, null);
        ImageView imgImage = (ImageView) view.findViewById(R.id.imgImage);
        final TextView txtImage = (TextView) view.findViewById(R.id.txtImage);
        final TextView txtImagePower = (TextView) view.findViewById(R.id.txtImagePower);
        final TextView txtImageSpeed = (TextView) view.findViewById(R.id.txtImageSpeed);

        String oldtxtImage = txtImage.getText().toString();
        String oldTxtImagePower = txtImagePower.getText().toString();
        String oldTxtImageSpeed = txtImageSpeed.getText().toString();

        //imgImage.setImageResource(images[i]);

        // in order to reduce size of images in application we have to commented out line 68
        // and pasting this lines of codes..

        /*
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 3;                 //  if value increase then quality of image decreases.
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(),images[i],options);
        imgImage.setImageBitmap(bm);

        */
        //  this is not the right way to write our code ....so we are pasting the method provides by android
        //   to load a scaled down version into memory

        imgImage.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(),images[i],
                                30,30));

        txtImage.setText(oldtxtImage + names[i]);
        txtImagePower.setText(oldTxtImagePower + power[i]);
        txtImageSpeed.setText(oldTxtImageSpeed + speed[i]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,txtImage.getText() + "\n" +
                                        txtImagePower.getText() + "\n" +
                                        txtImageSpeed.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public static int calculateInSampleSize(
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

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
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
}


