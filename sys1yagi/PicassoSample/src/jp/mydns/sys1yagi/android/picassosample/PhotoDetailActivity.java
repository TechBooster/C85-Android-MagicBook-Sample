package jp.mydns.sys1yagi.android.picassosample;

import jp.mydns.sys1yagi.android.picassosample.photo.GrayScaleTransformation;
import uk.co.senab.photoview.PhotoViewAttacher;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PhotoDetailActivity extends Activity {
    private final static String TAG = PhotoDetailActivity.class.getSimpleName();

    private final static String PARAM_PHOTO_URL = "photo_url";

    public static Intent newIntent(Activity parent, String photoUrl) {
        Intent intent = new Intent(parent, PhotoDetailActivity.class);
        intent.putExtra(PARAM_PHOTO_URL, photoUrl);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.title_main_activity));
        setContentView(R.layout.activity_photo_detail);

        String url = getIntent().getExtras().getString(PARAM_PHOTO_URL);
        final ImageView photo = (ImageView) findViewById(R.id.photo_detail_image);

        Picasso.with(this).load(url)
        // .transform(new GrayScaleTransformation())
                .into(photo, new Callback() {
                    @Override
                    public void onError() {
                        Log.d(TAG, "error");
                    }

                    @Override
                    public void onSuccess() {
                        findViewById(R.id.progress_circular).setVisibility(
                                View.GONE);
                        // 以下はPhotoViewの機能です
                        PhotoViewAttacher attacher = new PhotoViewAttacher(
                                photo);
                    }
                });
    }
}
