package com.example.shoppinglistsender;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Luis on 10/01/2017.
 */

public class CurrentListFragment extends ListFragment {

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        if (sharedPref.contains("current_shopping_items")) {
            Set<String> listItemsSet = sharedPref.getStringSet("current_shopping_items", null);
            listItems.addAll(listItemsSet);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shopping_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Activity activity = getActivity();
        ListView listView = (ListView) activity.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, listItems);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listItems.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Set<String> set = new HashSet<String>();
        set.addAll(listItems);
        editor.putStringSet("current_shopping_items", set);
        editor.apply();
    }

    public void addItemToList(String item) {
        listItems.add(item);
        adapter.notifyDataSetChanged();
    }

    public String returnSelectedItems() {
        String selectedItems = "";
        int numberSelectedItems = getListView().getCount();
        SparseBooleanArray sparseBooleanArray = getListView().getCheckedItemPositions();

        for (int i = 0; i < numberSelectedItems; i++) {
            if (sparseBooleanArray.get(i)) {
                selectedItems = selectedItems + "- " + listItems.get(i) + "\n";
            }
        }
        return selectedItems;
    }



}
