package com.example.shoppinglistsender;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class ShoppingListActivity extends FragmentActivity implements ChooseItemFragment.onChooseItemListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
    }

    public void onChooseItemButtonClick(String item) {
        CurrentListFragment listFragment = (CurrentListFragment)
                getSupportFragmentManager().findFragmentById(R.id.list_fragment);

        listFragment.addItemToList(item);

    }

}
