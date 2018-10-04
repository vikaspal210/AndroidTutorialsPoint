package com.example.cas.androidtutorialspoint;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.cas.androidtutorialspoint.data.PetContract;
import com.example.cas.androidtutorialspoint.data.PetDbHelper;

public class CatalogActivity extends AppCompatActivity {

    private PetDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        mDbHelper=new PetDbHelper(this);
        displayDatabaseInfo();
    }

    //not to be included in the final solution
    private void displayDatabaseInfo(){
        PetDbHelper mDbHelper=new PetDbHelper(this);
        SQLiteDatabase db=mDbHelper.getReadableDatabase();

        Cursor cursor=db.rawQuery("SELECT * FROM "+PetContract.PetEntry.TABLE_NAME,null);

        try{
            TextView displayView=(TextView)findViewById(R.id.text_view_pet);
            displayView.setText("Number of rows in pets database: "+cursor.getCount());
        }finally {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                displayDatabaseInfo();
                // Do nothing for now
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertPet() {
        SQLiteDatabase db=mDbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(PetContract.PetEntry.COLUMN_PET_NAME,"Toto");
        values.put(PetContract.PetEntry.COLUMN_PET_BREED,"Terrier");
        values.put(PetContract.PetEntry.COLUMN_PET_GENDER,PetContract.PetEntry.GENDER_MALE);
        values.put(PetContract.PetEntry.COLUMN_PET_WEIGHT,7);

        long newRowId=db.insert(PetContract.PetEntry.TABLE_NAME,null,values);
    }
}
