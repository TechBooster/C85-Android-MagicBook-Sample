package jp.mydns.sys1yagi.android.picassosample.photo;

import java.util.ArrayList;
import java.util.List;

import jp.mydns.sys1yagi.android.picassosample.PhotoDetailActivity;
import jp.mydns.sys1yagi.android.picassosample.photo.HatenaPhotoFragment.HatenaPhotoLoadTask.OnLoadListener;
import jp.mydns.sys1yagi.android.picassosample.photo.PhotoListAdapter.OnPhotoClickListener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class HatenaPhotoFragment extends ListFragment {

    private final static String ARGS_FEED_URL = "feed_url";

    public static HatenaPhotoFragment newInstance(String feedUrl) {
        HatenaPhotoFragment fragment = new HatenaPhotoFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_FEED_URL, feedUrl);

        fragment.setArguments(args);
        return fragment;
    }

    private String mFeedUrl;
    private HatenaPhotoLoadTask mTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        mFeedUrl = args.getString(ARGS_FEED_URL);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setDivider(null);
        if (mTask != null) {
            mTask.cancel(true);
        }
        mTask = new HatenaPhotoLoadTask(new OnLoadListener() {
            @Override
            public void onLoad(List<Photo> photos) {
                setupPhotoList(photos);
            }
        });
        mTask.execute(mFeedUrl);
    }

    private void setupPhotoList(List<Photo> photos) {
        if (photos != null && !photos.isEmpty()) {
            PhotoListAdapter adapter = new PhotoListAdapter(getActivity(),
                    new OnPhotoClickListener() {
                        @Override
                        public void onClick(Photo photo) {
                            Intent intent = PhotoDetailActivity.newIntent(
                                    getActivity(), photo.getOriginUrl());
                            getActivity().startActivity(intent);
                        }
                    });
            PhotoRow row = null;
            for (Photo photo : photos) {
                if (row == null) {
                    row = new PhotoRow();
                    row.setLeft(photo);
                } else {
                    row.setRight(photo);
                    adapter.add(row);
                    row = null;
                }
            }
            setListAdapter(adapter);
        }
    }

    static class HatenaPhotoLoadTask extends
            AsyncTask<String, Void, List<Photo>> {
        interface OnLoadListener {
            public void onLoad(List<Photo> photos);
        }

        private OnLoadListener mOnLoadListener;

        public HatenaPhotoLoadTask(OnLoadListener listener) {
            mOnLoadListener = listener;
        }

        private String headToString(Elements elements) {
            if (elements != null && !elements.isEmpty()) {
                return elements.get(0).text();
            }
            return null;
        }

        @Override
        protected List<Photo> doInBackground(String... params) {
            try {
                List<Photo> photos = new ArrayList<Photo>();
                Document document = Jsoup.connect(params[0]).timeout(10000)
                        .parser(Parser.xmlParser()).get();
                Elements items = document.select("item");
                for (Element item : items) {
                    String origin = headToString(item.select("hatena|imageurl"));
                    String medium = headToString(item
                            .select("hatena|imageurlmedium"));
                    Photo photo = new Photo();
                    photo.setOriginUrl(origin);
                    photo.setMediumUrl(medium);
                    photos.add(photo);
                }
                return photos;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Photo> result) {
            if (!isCancelled()) {
                mOnLoadListener.onLoad(result);
            }
        }
    }

}
