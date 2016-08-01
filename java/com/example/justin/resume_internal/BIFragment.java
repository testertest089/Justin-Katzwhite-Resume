package com.example.justin.resume_internal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created by Justin on 5/2/2016.
 */
public class BIFragment extends Fragment {

    public BIFragment() {}

    public static BIFragment newInstance(Bundle args) {
        BIFragment fragment = new BIFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.background_information_v2, container, false);
    }

    TextView textView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        textView = (TextView) view.findViewById(R.id.textView5);
        textView.setText(getArguments().getString("bi"));
        textView.setMovementMethod(new ScrollingMovementMethod());

    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return AnimationUtils.loadAnimation(getActivity(),
                enter ? android.R.anim.fade_in : android.R.anim.fade_out);
    }

}
