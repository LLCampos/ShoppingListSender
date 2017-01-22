package com.example.shoppinglistsender;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Luis on 10/01/2017.
 */

public class ChooseItemFragment extends Fragment {

    onChooseItemListener mCallback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (onChooseItemListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onChooseItemListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.choose_item, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        Button button = (Button) view.findViewById(R.id.choose_item_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = (EditText) getView().findViewById(R.id.EditTextShoppingItem);

                String item_choosen = editText.getText().toString();
                mCallback.onChooseItemButtonClick(item_choosen);
                editText.setText("");
            }
        });
    }

    public interface onChooseItemListener {
        public void onChooseItemButtonClick(String item);
    }

}
