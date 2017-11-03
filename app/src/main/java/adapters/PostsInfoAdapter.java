package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hi_tech.socialapp.R;
import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.PostsInfo;

/**
 * Created by Hi-Tech on 10/19/2017.
 */

public class PostsInfoAdapter extends BaseAdapter {
    ArrayList<PostsInfo> postsInfos;
    LayoutInflater layoutInflater;

    public PostsInfoAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        try {
            return postsInfos.size();
        } catch (NullPointerException e) {
            return 1;
        }
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.newsfeed_listview, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        try {
            String myDate = postsInfos.get(i).getCreatedDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date;
            date = sdf.parse(myDate);
            long millis = date.getTime();
            holder.username.setText(postsInfos.get(i).getUsername());
            holder.timeTextView.setReferenceTime(millis);
            holder.pershkrimi.setText(postsInfos.get(i).getPershkrimi());
            Picasso.with(view.getContext())
                    .load(postsInfos.get(i).getPhotoUrl())
                    .into(holder.imageView);
        }
       catch (ParseException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        return view;
    }

    public class ViewHolder {
        TextView username, pershkrimi;
        RelativeTimeTextView timeTextView;
        ImageView imageView;

        public ViewHolder(View view) {
            username = view.findViewById(R.id.name_textview);
            pershkrimi = view.findViewById(R.id.pershkrimi_textview);
            timeTextView = view.findViewById(R.id.date_textview);
            imageView = view.findViewById(R.id.posted_image);
        }
    }

    public void setPostsInfos(ArrayList<PostsInfo> postsInfos) {
        this.postsInfos = postsInfos;
        notifyDataSetChanged();
    }
}
