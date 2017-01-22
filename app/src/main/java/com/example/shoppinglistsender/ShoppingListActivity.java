package com.example.shoppinglistsender;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ShoppingListActivity extends FragmentActivity implements ChooseItemFragment.onChooseItemListener {

    CurrentListFragment listFragment;
    Button sendListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        listFragment = (CurrentListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.list_fragment);
        sendListButton = (Button) findViewById(R.id.buttonSendEmail);

        sendListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Lista");
                emailIntent.putExtra(Intent.EXTRA_TEXT, listFragment.returnSelectedItems());
                startActivity(emailIntent);
            }
        });

    }

    public void onChooseItemButtonClick(String item) {
        listFragment.addItemToList(item);
    }

}
