package com.example.justin.resume_internal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Justin on 5/2/2016.
 */
public class Contact extends Fragment {

    private static final String LOG_TAG = "Contact";

    public Contact() {}

    public static Contact newInstance() {
        Contact fragment = new Contact();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.contact, container, false);
    }

    ImageView imageView;
    Intent intent, chooser;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
//        imageView = (ImageView) view.findViewById(R.id.imageView);
//        imageView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//
//                intent = new Intent(Intent.ACTION_SENDTO);
//                intent.putExtra(Intent.EXTRA_EMAIL, "jkatzwhite@gmail.com");
//                intent.putExtra(Intent.EXTRA_SUBJECT, "Resume Contact");
//
//                chooser = Intent.createChooser(intent, "Please select an e-mail service.");
//
//                PackageManager packageManager = getActivity().getPackageManager();
//                List activities = packageManager.queryIntentActivities(intent,
//                        PackageManager.MATCH_DEFAULT_ONLY);
//                boolean isIntentSafe = activities.size() > 0;
//
//                Log.d(LOG_TAG, "E-mail service available: " + isIntentSafe);
//
//                if (isIntentSafe) {
//                    startActivity(chooser);
//                }else {
//                    Toast.makeText(getContext(), "Please install an e-mail service.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return AnimationUtils.loadAnimation(getActivity(),
                enter ? android.R.anim.fade_in : android.R.anim.fade_out);
    }
}
