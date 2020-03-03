package com.example.codefest.ui.extras;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.codefest.Contact;
import com.example.codefest.DatabaseHandler;
import com.example.codefest.MainActivity;
import com.example.codefest.R;
import com.example.codefest.ui.gallery.GalleryViewModel;
import com.example.codefest.ui.home.HomeFragment;
import com.example.codefest.ui.home.HomeViewModel;
import com.example.codefest.ui.slideshow.SlideshowFragment;
import com.example.codefest.ui.slideshow.SlideshowViewModel;

public class ExtrasFragment extends Fragment {

    private ExtrasModel extrasModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {


        extrasModel = ViewModelProviders.of(this).get(ExtrasModel.class);
        View root = inflater.inflate(R.layout.fragment_extras,container, false);
        final TextView textView = root.findViewById(R.id.text_extras);
        final EditText etName = root.findViewById(R.id.etTextMo);
        final EditText etPhoneNumber = root.findViewById(R.id.etPhoneNumber);
        final Button submit = root.findViewById(R.id.btnSubmitName);
        final DatabaseHandler db = new DatabaseHandler(getActivity());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String phoneNumber = etPhoneNumber.getText().toString();
//                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                //sharePrefs
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putString("fullName", name);
//                editor.commit();
//                SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
//                String defaultValue = "";
//                String fullNam = prefs.getString("fullName", null);
                db.addContact(new Contact(name, phoneNumber));

                etName.setText("");
                etPhoneNumber.setText("");
            }
        });
        extrasModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });

        return root;
    }

}
