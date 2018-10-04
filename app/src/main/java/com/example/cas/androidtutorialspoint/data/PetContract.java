package com.example.cas.androidtutorialspoint.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class PetContract {
    public PetContract() {
    }

    //content provider paths, authorities, base content
    public static final String CONTENT_AUTHORITY="com.example.cas.androidtutorialspoint";
    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_PETS="pets";

    public static final class PetEntry implements BaseColumns{

        //content uri
        public static final Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI,PATH_PETS);

        //contract constants
        public final static String TABLE_NAME="pets";
        public final static String _ID=BaseColumns._ID;
        public final static String COLUMN_PET_NAME="name";
        public final static String COLUMN_PET_BREED="breed";
        public final static String COLUMN_PET_GENDER="gender";
        public final static String COLUMN_PET_WEIGHT="weight";

        public final static int GENDER_UNKNOWN=0;
        public final static int GENDER_MALE=1;
        public final static int GENDER_FEMALE=2;
    }
}
