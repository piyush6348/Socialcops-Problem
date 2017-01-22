package socialcops.piyush6348.com.socialcopsproblem.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.VideoView;

import java.io.File;

import socialcops.piyush6348.com.socialcopsproblem.R;
import socialcops.piyush6348.com.socialcopsproblem.activities.UploadedData;

/**
 * Created by dell on 1/22/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    File[] fList;
    Context context;
    public ImageAdapter(File[] fList, Context con) {
        this.fList=fList;
        this.context=con;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.e("ImageAdapter: ",this.fList.length+" hey " );
    }

    @Override
    public int getCount() {
        return fList.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(
                    R.layout.grid_view_item, null);
            holder.imageview = (ImageView) convertView.findViewById(R.id.grid_item_image);
            holder.gridItemVideoOrNot=(ImageView) convertView.findViewById(R.id.grid_item_video_or_image);
            holder.videoView = (VideoView) convertView.findViewById(R.id.grid_item_video);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(fList[i].getName().endsWith(".jpg"))
        {
            Bitmap myBitmap= BitmapFactory.decodeFile(fList[i].getAbsolutePath());
            //holder.imageview.setVisibility(View.VISIBLE);
            holder.imageview.setImageBitmap(myBitmap);

        }
        else
        {
            Bitmap thumbNail= ThumbnailUtils.createVideoThumbnail(fList[i].getAbsolutePath(),
                    MediaStore.Images.Thumbnails.MINI_KIND);
            holder.gridItemVideoOrNot.setVisibility(View.VISIBLE);
            holder.gridItemVideoOrNot.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_play_circle_outline_black_24dp));
            holder.imageview.setImageBitmap(thumbNail);
        }
        return convertView;
    }
    class ViewHolder {
        ImageView imageview,gridItemVideoOrNot;
        VideoView videoView;
    }
}
