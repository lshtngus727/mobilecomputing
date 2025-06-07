package com.example.project;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private RecyclerView calendarRecyclerView, recyclerViewSchedule;
    private DayAdapter dayAdapter;
    private ScheduleAdapter scheduleAdapter;
    private List<Day> dayList;
    private List<Schedule> scheduleList;
    private TextView tvMonthTitle;
    private Button btnAddSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        // 1. View 연결
        tvMonthTitle = findViewById(R.id.tvMonthTitle);
        btnAddSchedule = findViewById(R.id.btnAddSchedule);
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        recyclerViewSchedule = findViewById(R.id.recyclerViewSchedule);

        // 2. 달력 데이터 구성
        dayList = generateCalendarDays();

        // 3. 어댑터 연결
        dayAdapter = new DayAdapter(dayList, this); // ✅ Context(this) 추가
        calendarRecyclerView.setLayoutManager(new GridLayoutManager(this, 7));
        calendarRecyclerView.setAdapter(dayAdapter);

        // 4. 일정 리스트 구성
        scheduleList = new ArrayList<>();
        scheduleList.add(new Schedule("5월 회비 사용 내역 제출", "2025-06-01"));
        scheduleList.add(new Schedule("정기 회의", "2025-06-10"));

        scheduleAdapter = new ScheduleAdapter(scheduleList);
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSchedule.setAdapter(scheduleAdapter);

        // 5. 상단 날짜 텍스트 설정
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        tvMonthTitle.setText(year + "년 " + month + "월");
    }

    private List<Day> generateCalendarDays() {
        List<Day> days = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        int totalCells = 42; // 6주 * 7일
        int dayNumber = 1;

        for (int i = 0; i < totalCells; i++) {
            if (i < firstDayOfWeek || dayNumber > maxDays) {
                days.add(new Day(0, false)); // 빈 셀
            } else {
                days.add(new Day(dayNumber++, true)); // 현재 월 날짜
            }
        }

        return days;
    }
}
