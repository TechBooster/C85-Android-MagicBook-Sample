package jp.mydns.sys1yagi.android.picassosample.photo;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.Config;

import com.squareup.picasso.Transformation;

public class GrayScaleTransformation implements Transformation {
    @Override
    public String key() {
        return "grayScale()";
    }

    @Override
    public Bitmap transform(Bitmap paramBitmap) {
        Bitmap bitmap = paramBitmap.copy(Config.ARGB_4444, true);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        try {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int color = bitmap.getPixel(i, j);
                    int gray = (Color.red(color) + Color.blue(color) + Color
                            .green(color)) / 3;
                    bitmap.setPixel(i, j, Color.rgb(gray, gray, gray));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        paramBitmap.recycle();
        return bitmap;
    }
}
