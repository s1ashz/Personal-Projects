package com.example.s1ash.sqlitepractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class ViewItemActivity extends AppCompatActivity {

    TextView itemNameLabel;
    TextView itemDescriptionLabel;
    DBHandler dbHandler;

    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        setLayoutFields();

        MobileAds.initialize(this, "ca-app-pub-7760606882887056~1468849138");

        adView = (AdView) findViewById( R.id.adView );
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        dbHandler = new DBHandler(this, null, null, 1);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String selectedItem = (String) bundle.get("selectedItem");

        System.out.println("ISTO ESTAVA LÀ    " + selectedItem);

        Item dbItem = dbHandler.getItem(selectedItem);

        updateFields(dbItem);

    }

    private void updateFields(Item dbItem) {
        itemNameLabel.setText(dbItem.get_itemname());
        itemDescriptionLabel.setText(dbItem.get_itemdescription());
        itemDescriptionLabel.setMovementMethod(new ScrollingMovementMethod());
    }

    private void setLayoutFields() {
        itemNameLabel = (TextView) findViewById(R.id.itemNameLabel);
        itemDescriptionLabel = (TextView) findViewById(R.id.itemDescriptionLabel);
    }

    public void goToViewItemActivity(View v) {
        Intent intent = new Intent(ViewItemActivity.this, ListItemsActivity.class);
        startActivity(intent);
    }

}
