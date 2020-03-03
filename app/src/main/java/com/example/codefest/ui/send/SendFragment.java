package com.example.codefest.ui.send;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.codefest.DatabaseHandler;
import com.example.codefest.R;
import com.example.codefest.TimeDate;
import com.example.codefest.ui.time.TimePickerFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SendFragment extends Fragment implements TimePickerDialog.OnTimeSetListener{

    private SendViewModel sendViewModel;
    private CalendarView calendarView;
    private TextView textSend;
    private Button btnTimePicker, btnUpload;
    private FragmentActivity myContext;
    final Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    String date;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        calendarView = root.findViewById(R.id.calendarView);
        textSend = root.findViewById(R.id.text_send);
        btnTimePicker = root.findViewById(R.id.buttonTime);
        btnUpload = root.findViewById(R.id.buttonUpload);
        final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final DatabaseHandler db = new DatabaseHandler(getActivity());

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = month + 1 + "/" + dayOfMonth + "/" + year;
                Toast.makeText(getActivity(), date, Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("date", date);


            }
        });
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
                String time = prefs.getString("time", null);

                TimeDate timeDate = new TimeDate();
                timeDate.setTime(time);
                timeDate.setDate_time(date);

                db.addDateTime(timeDate);
            }
        });
        return root;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }
}