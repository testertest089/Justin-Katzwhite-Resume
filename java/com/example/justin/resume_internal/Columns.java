package com.example.justin.resume_internal;

import android.provider.BaseColumns;

/**
 * Created by Justin on 7/28/2016.
 */
public class Columns {
    public Columns() {}

    public static abstract class Titles implements BaseColumns {
        public static final String TABLE_NAME = "FRAGMENT_DATA";
        public static final String KEY = "KEY";
        public static final String VALUE = "CONTENT";
        public static final String COLUMN_NAME_NULLABLE = null;
    }
}
