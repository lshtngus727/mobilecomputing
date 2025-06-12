package com.example.project;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleAddActivity extends AppCompatActivity {

    private EditText etScheduleTitle;
    private Button btnAddDate, btnSubmitSchedule;
    private ListView listSelectedDates;

    private ArrayList<String> selectedDates;
    private ArrayAdapter<String> dateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_schedule); // 이 레이아웃 이름이 정확한 XML 파일명

        etScheduleTitle = findViewById(R.id.etScheduleTitle);
        btnAddDate = findViewById(R.id.btnAddDate);
        btnSubmitSchedule = findViewById(R.id.btnSubmitSchedule);
        listSelectedDates = findViewById(R.id.listSelectedDates);

        selectedDates = new ArrayList<>();
        dateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedDates);
        listSelectedDates.setAdapter(dateAdapter);

        btnAddDate.setOnClickListener(v -> showDatePicker());

        btnSubmitSchedule.setOnClickListener(v -> {
            String title = etScheduleTitle.getText().toString();

            if (title.isEmpty() || selectedDates.isEmpty()) {
                Toast.makeText(this, "제목과 날짜를 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 시작일은 가장 빠른 날짜, 종료일은 가장 늦은 날짜로 자동 설정
            selectedDates.sort(String::compareTo);
            String startDate = selectedDates.get(0);
            String endDate = selectedDates.get(selectedDates.size() - 1);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("title", title);
            resultIntent.putExtra("startDate", startDate);
            resultIntent.putExtra("endDate", endDate);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, (view, y, m, d) -> {
            String dateStr = y + "-" + String.format("%02d", m + 1) + "-" + String.format("%02d", d);
            if (!selectedDates.contains(dateStr)) {
                selectedDates.add(dateStr);
                dateAdapter.notifyDataSetChanged();
            }
        }, year, month, day);

        dialog.show();
    }
}
