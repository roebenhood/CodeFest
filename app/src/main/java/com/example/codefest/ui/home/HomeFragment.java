package com.example.codefest.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.codefest.Contact;
import com.example.codefest.DatabaseHandler;
import com.example.codefest.R;
import com.example.codefest.ui.dialogs.CustomDialogClass;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ListView listView;
    private ArrayList<String> contactArrayListName;
    private ArrayList<String> contactArrayListID;
    private ArrayAdapter<String> arrayAdapterName;
    private ArrayAdapter<String> arrayAdapterID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        final DatabaseHandler db = new DatabaseHandler(getActivity());

        listView = (ListView) root.findViewById(R.id.listview);
        contactArrayListName = new ArrayList<>();
        contactArrayListID = new ArrayList<>();
        final List<Contact> contactList = db.getAllContacts();


        for (Contact contact : contactList) {
            Log.d("MainActivity", "onCreate: " + contact.getName());
            contactArrayListName.add(contact.getName());
        }
        for (Contact contactID : contactList) {
            contactArrayListID.add(String.valueOf(contactID.getId()));
        }
//
        //create array adapter
        arrayAdapterID = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1,
                contactArrayListID
        );
        arrayAdapterName = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1,
                contactArrayListName
        );


        //add to our listview
        listView.setAdapter(arrayAdapterName);

        //Attach eventlistener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("List", "onItemClick: " + contactArrayListID.get(position));
                int pos = Integer.parseInt(contactArrayListID.get(position));
                Contact del = db.getContact(pos);
                db.deleteContact1(del);
                Log.d("List", "onItemClick: " + del);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                CustomDialogClass cdd=new CustomDialogClass(getActivity());
                cdd.show();

                cdd.cName.setText(contactArrayListName.get(position));
                cdd.cNumber.setText(contactArrayListName.get(position));

                return true;
            }
        });

        return root;
    }

}