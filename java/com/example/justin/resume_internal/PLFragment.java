package com.example.justin.resume_internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 5/2/2016.
 */
public class PLFragment extends Fragment {

    private static final String LOG_TAG = "MainFragment";

    public PLFragment() {}

    public static PLFragment newInstance(Bundle args) {
        PLFragment fragment = new PLFragment();
        fragment.setArguments(args);
        return fragment;
    }

    FragDbHelper mDbHelper;
    Context context;
    List<Topics> subjects = new ArrayList<Topics>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        subjects.add(new Topics("Android/Java", "android"));
        subjects.add(new Topics("Python/REST API", "python"));
        subjects.add(new Topics("GAE/NoSQL", "gae"));
        subjects.add(new Topics("HTML5/CSS/JavaScript", "html5"));

        context = getActivity().getApplicationContext();
        mDbHelper = new FragDbHelper(context);

        super.onCreate(savedInstanceState);
    }

    FragmentAdapter fragmentAdapter;
    Bundle args = new Bundle();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        args = getArguments();
        fragmentAdapter = new FragmentAdapter(subjects, inflater);

        return inflater.inflate(R.layout.programming_languages, container, false);
    }

    ListView itemList;
    TextView textView;
    Pair<String, String> pair;
    Cursor cursor;
    SQLiteDatabase db;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        db = mDbHelper.getWritableDatabase();

        textView = (TextView) view.findViewById(R.id.textView4);
        textView.setMovementMethod(new ScrollingMovementMethod());

        itemList = (ListView) view.findViewById(R.id.listView2);
        itemList.setAdapter(fragmentAdapter);
        itemList.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int selection, long id) {

                cursor = queryData(subjects.get(selection).id);
                if(cursor.getCount() == 0){
                    Toast.makeText(context, "Inserting app data to db.", Toast.LENGTH_SHORT).show();
                    String key = subjects.get(selection).id;
                    String value = args.getString(key);
                    inputData(key, value);
                    cursor = queryData(subjects.get(selection).id);
                }

//                item = menu.getItem(R.id.retrieve);
//                if(item.isChecked()){
//
//                }

                cursor.moveToFirst();
                textView.setText(cursor.getString(0));
//                textView.setText(args.getString(subjects.get(selection).id));
            }
        });

        itemList.setOnItemLongClickListener(new ListView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int selection, long id){

                ConnectivityManager connMgr = (ConnectivityManager)
                        getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    Toast.makeText(context, "Retrieving data from cloud and inserting to db.", Toast.LENGTH_SHORT).show();
                    pair = new Pair<String, String>("PL", subjects.get(selection).id);
                    new DataFetch(context, textView, db).execute(pair);
                } else {
                    Toast.makeText(getContext(), "Please connect to Internet.", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return AnimationUtils.loadAnimation(getActivity(),
                enter ? android.R.anim.fade_in : android.R.anim.fade_out);
    }

    public Cursor queryData(String subject){
        db = mDbHelper.getReadableDatabase();

        String[] projection = {
                Columns.Titles.VALUE,
        };

        String selection = Columns.Titles.KEY + " LIKE ?";
        String[] selectionArgs = {subject};

        Cursor c = db.query(
                Columns.Titles.TABLE_NAME,                 // The table to query
                projection,                               // The columns to return
                selection,                                     // The columns for the WHERE clause
                selectionArgs,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                               // The sort order
        );

        return c;
    }

    ContentValues values = new ContentValues();
    long newRowId;

    public void inputData(String key, String value){
        db = mDbHelper.getWritableDatabase();

        values.put(Columns.Titles.KEY, key);
        values.put(Columns.Titles.VALUE, value);

        newRowId = db.insert(
                Columns.Titles.TABLE_NAME,
                Columns.Titles.COLUMN_NAME_NULLABLE,
                values);

        if (newRowId == -1){
            Toast.makeText(context, "Data input failed. :(", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Data input succes. Row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }
}
