package jp.mydns.sys1yagi.android.picassosample;

import jp.mydns.sys1yagi.android.picassosample.photo.HatenaPhotoFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class PicassoSampleActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_sample);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            transaction.add(R.id.content_frame, HatenaPhotoFragment
                    .newInstance("http://f.hatena.ne.jp/hotfoto?mode=rss"));
            transaction.commit();
        }
    }

}
