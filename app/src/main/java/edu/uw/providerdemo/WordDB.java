package edu.uw.providerdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by DANG on 5/11/2016.
 */
public class WordDB {
    private WordDB() {} // so that no one can instantiate this class

    private static final String DATABASE_NAME = "words.db";
    private  static final int DATABASE_VERSION = 1;

    // word table
    static class Words implements BaseColumns {
        // these are just names of the column that labels on the spreadsheet
        public static final String TABLE_NAME = "words";
        public static final String COL_WORD = "word";
        public static final String COL_COUNT = "count";

    }

    static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        private static final String CREATE_WORD_TABLE = "CREATE TABLE " + Words.TABLE_NAME + "(" +
                    Words._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Words.COL_WORD + " TEXT, " +
                    Words.COL_COUNT + " INTEGER" +
                ")";

        private static final String DROP_WORDS_TABLE = "DROP TABLE IF EXISTS " + Words.TABLE_NAME;


        // called when created
        // arg represents the actual SQL database that you can query
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_WORD_TABLE);

            ContentValues value = new ContentValues();
            value.put(Words.COL_WORD, "Embiggen");
            value.put(Words.COL_COUNT, 0);

            db.insert(Words.TABLE_NAME, null, value);


        }

        // called when version changes
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_WORDS_TABLE);
            onCreate(db);
        }
    }

}
