package com.example.s1ash.sqlitepractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView nameLabel;
    EditText nameInput;
    EditText descriptionInput;
    DBHandler dbHandler;
    Toast toast;

    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-7760606882887056~1468849138");

        adView = (AdView) findViewById( R.id.adView );
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        generateViewFiels();
        dbHandler = new DBHandler(this, null, null, 1);

    }

    public void addButtonClick(View view) {
        String name = nameInput.getText().toString();
        String description = descriptionInput.getText().toString();
        if ( name.equals("")  ||name.trim().equals("") || name.equals(null) ) {
            makeToastMessage("Name field is mandatory");
            System.out.println( "NAME AAAAAA :                  " +name);
            return;
        }
        Item item = new Item();
        item.set_itemname(name);
        item.set_itemdescription(description);


        dbHandler.addItem(item);
        makeToastMessage("Item " + name + " added!");
        resetInputFields();
    }
                                                                                //    ca-app-pub-7760606882887056~1468849138
    public void itemListButtonClick(View view) {
        ArrayList<String> itemsList = (ArrayList<String>) dbHandler.getDatabaseItems();

        Intent intent = new Intent(MainActivity.this, ListItemsActivity.class);
        startActivity(intent);
        //dbHandler.resetDatabase();
    }

    private void generateViewFiels() {

        nameInput = (EditText) findViewById(R.id.nameInput);
        descriptionInput = (EditText) findViewById(R.id.descriptionInput);
        nameLabel = (TextView) findViewById(R.id.nameLabel);

    }

    private void resetInputFields() {
        nameInput.setText("");
        descriptionInput.setText("");
    }

    private void makeToastMessage(String message) {

        if( toast != null) {
            toast.cancel();
        }

        System.out.println(message);
        toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG);
        toast.show();

    }
}
