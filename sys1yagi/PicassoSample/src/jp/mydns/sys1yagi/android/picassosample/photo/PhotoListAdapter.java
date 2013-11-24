package jp.mydns.sys1yagi.android.picassosample.photo;

import jp.mydns.sys1yagi.android.picassosample.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PhotoListAdapter extends ArrayAdapter<PhotoRow> {

    public interface OnPhotoClickListener {
        public void onClick(Photo photo);
    }

    LayoutInflater mLayoutInflater;
    OnPhotoClickListener mListener;
    Picasso mPicasso;

    public PhotoListAdapter(Activity activity, OnPhotoClickListener listener) {
        super(activity, -1);
        mLayoutInflater = activity.getLayoutInflater();
        mListener = listener;
        mPicasso = Picasso.with(activity);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.listitem_photo,
                    null, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        final PhotoRow photo = getItem(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        
        holder.mLeftImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(photo.getLeft());
            }
        });
        mPicasso.load(photo.getLeft().getMediumUrl())
                .placeholder(R.drawable.placeholder).into(holder.mLeftImage);
        
        holder.mRightImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(photo.getRight());
            }
        });
        mPicasso.load(photo.getRight().getMediumUrl())
                .placeholder(R.drawable.placeholder).into(holder.mRightImage);
        return convertView;
    }

    public class ViewHolder {
        public final ImageView mLeftImage;
        public final ImageView mRightImage;
        public final View mRoot;

        public ViewHolder(View root) {
            mLeftImage = (ImageView) root.findViewById(R.id.left_image);
            mRightImage = (ImageView) root.findViewById(R.id.right_image);
            this.mRoot = root;
        }
    }
}
