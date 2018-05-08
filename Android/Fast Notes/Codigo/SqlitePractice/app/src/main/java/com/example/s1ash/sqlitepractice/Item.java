package com.example.s1ash.sqlitepractice;

/**
 * Created by s1ash on 11-Feb-18.
 */

public class Item {

    private int _id;
    private String _itemname;
    private String _itemdescription;

    public Item() {};

    public Item(String _itemname) {
        this._itemname = _itemname;
        this._itemdescription = _itemdescription;
    }

    public Item(String _itemname, String _itemdescription) {
        this._itemname = _itemname;
        this._itemdescription = _itemdescription;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_itemname() {
        return _itemname;
    }

    public void set_itemname(String _itemname) {
        this._itemname = _itemname;
    }

    public String get_itemdescription() {
        return _itemdescription;
    }

    public void set_itemdescription(String _itemdescription) {
        this._itemdescription = _itemdescription;
    }

    @Override
    public String toString() {
        return "Item{" +
                "_id=" + _id +
                ", _itemname='" + _itemname + '\'' +
                ", _itemdescription='" + _itemdescription + '\'' +
                '}';
    }
}
