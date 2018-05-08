package com.example.s1ash.sqlitepractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ListItemsActivity extends AppCompatActivity {

    ListView listItemsView;
    String selectedItem;
    DBHandler dbHandler;
    CustomAdapter listAdapter;
    TextView itemLabel;
    Button viewBtn;
    Button deleteBtn;
    Toast toast;

    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        setLayoutFields();
        dbHandler = new DBHandler(this, null, null, 1);

        MobileAds.initialize(this, "ca-app-pub-7760606882887056~1468849138");

        adView = (AdView) findViewById( R.id.adView );
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        List<String> databaseItemsList = dbHandler.getDatabaseItems();

        createListViewAdapter(databaseItemsList);

        createListViewOnItemClickListener(listItemsView);

    }

    public void createListViewAdapter(List<String> list) {

        listAdapter = new CustomAdapter(this, list);

        //ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listItemsView.setAdapter(listAdapter);
    }

    public void updateListViewAdapter(){
        ArrayList<String> itemsList = (ArrayList<String>) dbHandler.getDatabaseItems();
        listAdapter = new CustomAdapter(this, itemsList);
        //ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsList);
        listItemsView.setAdapter(listAdapter);
    }

    public void deleteItem(View view) {

        if ( null == selectedItem) {
            makeToastMessage("Please select and item first");
            return;
        }

        System.out.println("ASDBHILHI SDBHASDFASDBHFASDKL FASASDFASD============================================================================sDASDASD");

        dbHandler.deleteItem(selectedItem);

        System.out.println( dbHandler.getDatabaseItems().toString() );
        updateListViewAdapter();
        makeToastMessage("Item " + selectedItem + " deleted");
        selectedItem = null;
        updateButtonsState(false);

    }

    private void updateButtonsState(boolean bol) {

        if (bol) {
            deleteBtn.setBackgroundResource(R.mipmap.bin_selected);
            viewBtn.setBackgroundResource(R.mipmap.eye_selected);
        } else {
            deleteBtn.setBackgroundResource(R.mipmap.bin);
            viewBtn.setBackgroundResource(R.mipmap.eye);
        }
    }

    private void createListViewOnItemClickListener(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                view.setSelected(true);

                itemLabel = (TextView) view.findViewById(R.id.itemLabel);

                System.out.println("itemLabel   " + itemLabel.getText().toString());

                selectedItem = String.valueOf(parent.getItemAtPosition(i));
                updateButtonsState(true);

            }
        });
    }

    public void goToMainActivity(View v) {
        Intent intent = new Intent(ListItemsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void setLayoutFields() {
        listItemsView = (ListView) findViewById(R.id.listItemsView);
        viewBtn = (Button) findViewById(R.id.viewBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
    }


    public void goToViewItemActivity(View v) {
        if ( null == selectedItem) {
            makeToastMessage("Please select and item first");
            return;
        }

        Intent intent = new Intent(ListItemsActivity.this, ViewItemActivity.class);
        intent.putExtra("selectedItem", selectedItem);
        startActivity(intent);
    }

    private void makeToastMessage(String message) {
        if( toast != null) {
            toast.cancel();
        }
        System.out.println(message);
        toast = Toast.makeText(ListItemsActivity.this, message, Toast.LENGTH_LONG);
        toast.show();

    }

}
