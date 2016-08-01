package com.example.justin.resume_internal;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 5/9/2016.
 */
public class MainFragment extends Fragment implements ListView.OnItemClickListener{

    private static final String LOG_TAG = "MainFragment";

    public MainFragment() {}

    private OnSubjectSelectedListener mListener;
    public interface OnSubjectSelectedListener {
        void onSubjectSelected(String subject);
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    List<String> subjects = new ArrayList<String>();
    ArrayAdapter adapter;
    ListView itemList;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        subjects.add("Background Information");
        subjects.add("Programming Languages");
        subjects.add("Contact");

        try {
            mListener = (OnSubjectSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSubjectSelectedListener");
        }

        int layout = R.layout.text_layout;
        adapter = new ArrayAdapter<String>(activity, layout, subjects);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.content_main, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        itemList = (ListView) view.findViewById(R.id.listView3);
        itemList.setAdapter(adapter);
        itemList.setOnItemClickListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    String subject;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int selection, long id) {
//        Log.d(LOG_TAG, "Item selected");

        subject = subjects.get(selection);
        mListener.onSubjectSelected(subject);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return AnimationUtils.loadAnimation(getActivity(),
                enter ? android.R.anim.fade_in : android.R.anim.fade_out);
    }
}
