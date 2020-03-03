package com.example.codefest.ui.dialogs;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.codefest.R;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public EditText cName, cNumber;
    public String conName, conNumber;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        cName = findViewById(R.id.etDialogTextMo);
        cNumber = findViewById(R.id.etDialogPhoneNumber);
        yes = (Button) findViewById(R.id.btn_update);
        no = (Button) findViewById(R.id.btn_delete);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                break;
            case R.id.btn_delete:

                break;
            default:
                break;
        }
        dismiss();
    }
}