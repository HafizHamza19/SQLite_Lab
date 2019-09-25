package com.example.databaselabe;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Spinner spncategory;
    EditText id, name, prize;
    TextView displayid,displayname,displayCategory,displayprize;
    Spinner category;
    DatabaseFile databaseFile;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spncategory = (Spinner) findViewById(R.id.Category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Category));
        spncategory.setAdapter(adapter);
        id = (EditText) findViewById(R.id.ID);
        name = (EditText) findViewById(R.id.Name);
        prize = (EditText) findViewById(R.id.Prize);
        displayid = (TextView) findViewById(R.id.id);
        displayname = (TextView) findViewById(R.id.name);
        displayCategory = (TextView) findViewById(R.id.cat);
        displayprize = (TextView) findViewById(R.id.prize);
        databaseFile = new DatabaseFile(this);
    }

    public void save(View view) {
        try {
            databaseFile.Addproducts(Integer.parseInt(id.getText().toString()), name.getText().toString(), spncategory.getSelectedItem().toString(), Double.parseDouble(prize.getText().toString()));
            Toast.makeText(this, "Record Save", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();

        }
    }

    public void update(View view) {
        databaseFile.UpdateProduct(Integer.parseInt(id.getText().toString()), name.getText().toString(), category.getSelectedItem().toString(), Double.parseDouble(prize.getText().toString()));
        Toast.makeText(this, "Update Id =" + id.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public void deleteProduct(View view) {
        databaseFile.Delete(Integer.parseInt(id.getText().toString()));
        Toast.makeText(this, "Delete The Column of Id =" + id.getText().toString(), Toast.LENGTH_SHORT).show();

    }

    public void select(View view) {
//        databaseFile = new DatabaseFile(MainActivity.this);
//        databaseFile.onOpen(databaseFile.getReadableDatabase());
//
//        String text[] = databaseFile.getAppCategoryDetail(); //this is the method to query
//        editText.setText(text.toString());
//        databaseFile.close();
//        // set text to your TextView
        databaseFile = new DatabaseFile(MainActivity.this);
        databaseFile.open();
        Cursor cursor = databaseFile.fetch(Integer.parseInt(id.getText().toString()));
        cursor.moveToFirst();
        displayid.setText(cursor.getString(0));
        displayname.setText(cursor.getString(1));
        displayCategory.setText(cursor.getString(2));
        displayprize.setText(cursor.getString(3));

    }
}












