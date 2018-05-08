package com.example.s1ash.sqlitepractice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by s1ash on 13-Feb-18.
 */

public class CustomAdapter extends ArrayAdapter<String> {

    TextView itemLabel;
    Context context;

    public CustomAdapter(@NonNull Context context, List<String> itemList) {
        super(context, R.layout.custom_item, itemList);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater listInflater = LayoutInflater.from(getContext());

        View customView = listInflater.inflate( R.layout.custom_item, parent, false );

        String listItemName = getItem(position);
        itemLabel = (TextView) customView.findViewById(R.id.itemLabel);
        itemLabel.setText((listItemName));

        return customView;
    }
}
