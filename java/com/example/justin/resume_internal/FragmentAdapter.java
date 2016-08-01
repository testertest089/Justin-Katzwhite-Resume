package com.example.justin.resume_internal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Justin on 5/30/2016.
 */
public class FragmentAdapter extends BaseAdapter {

    private static final String LOG_TAG = "FragmentAdapter";

    List<Topics> subjects;
    LayoutInflater inflater;

    public FragmentAdapter(List<Topics> subjects, LayoutInflater inflater) {
        this.subjects = subjects;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public String getItem(int position) {
        return subjects.get(position).subjects;
    }

    @Override
    public long getItemId(int position) {
//        subjects.get(position).id.hashCode();
        return subjects.size();
    }

    int layout = R.layout.text_layout_image;
    //int layout = R.layout.text_layout;

    View view;
    TextView name;
    ImageView image;
//    int color[] = {R.color.android, R.color.python, R.color.gae_blue, R.color.html5};
    int icons[] = {R.mipmap.ic_launcher_icon, R.mipmap.python, R.mipmap.cloud_platform_icon,
        R.mipmap.html5};
    int background[] = {R.drawable.andriod_background, R.drawable.python_background,
            R.drawable.gae_background, R.drawable.html_background};

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Log.d(LOG_TAG, "id: " + getItemId(position));

        view = inflater.inflate(layout, parent, false);

        if (convertView == null) {
            name = (TextView) view.findViewById(R.id.textView2);
            name.setText(getItem(position));
            name.setBackgroundResource(background[position]);

            image = (ImageView) view.findViewById(R.id.imageView4);
            image.setBackgroundResource(icons[position]);
            convertView = view;
        }

        return convertView;
    }

}
