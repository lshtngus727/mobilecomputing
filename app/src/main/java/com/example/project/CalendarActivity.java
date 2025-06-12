package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class CalendarActivity extends AppCompatActivity {

    private RecyclerView calendarRecyclerView, recyclerViewSchedule;
    private DayAdapter dayAdapter;
    private ScheduleAdapter scheduleAdapter;
    private List<Day> dayList;
    private List<Schedule> allSchedules; // 전체 일정 목록
    private List<Schedule> scheduleList; // 현재 달의 일정 목록
    private TextView tvMonthTitle;
    private Button btnAddSchedule, btnPrevMonth, btnNextMonth;
    private Calendar currentCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        // View 연결
        tvMonthTitle = findViewById(R.id.tvMonthTitle);
        btnAddSchedule = findViewById(R.id.btnAddSchedule);
        btnPrevMonth = findViewById(R.id.btnPrevMonth);
        btnNextMonth = findViewById(R.id.btnNextMonth);
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        recyclerViewSchedule = findViewById(R.id.recyclerViewSchedule);

        // 관리자만 일정 추가 버튼 보이기
        SharedPreferences pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String role = pref.getString("role", "비회원");
        if (!role.equals("관리자")) {
            btnAddSchedule.setVisibility(Button.GONE);
        }

        // ✅ 일정 추가 버튼 클릭 시 이동
        btnAddSchedule.setOnClickListener(v -> {
            Intent intent = new Intent(CalendarActivity.this, ScheduleAddActivity.class);
            startActivityForResult(intent, 200);  // ← requestCode 반드시 200으로
        });

        // 현재 월 캘린더 초기화
        currentCalendar = Calendar.getInstance();

        // 전체 일정 샘플 데이터
        allSchedules = new ArrayList<>();
        allSchedules.add(new Schedule("5월 회비 사용 내역 제출", "2025-06-01", "2025-06-05"));
        allSchedules.add(new Schedule("5월 대표자 회의 DH101, 18:00", null, "2025-06-02"));
        allSchedules.add(new Schedule("국립한밭대 x 충남대 연합 MT", "2025-06-22", "2025-06-23"));
        allSchedules.add(new Schedule("4월 회비 사용 내역 제출", "2025-05-01", "2025-05-05"));
        allSchedules.add(new Schedule("폭싹 늙었수다 MT", "2025-05-05", "2025-05-06"));
        allSchedules.add(new Schedule("4월 대표자 회의 DH101, 18:00", null, "2025-05-15"));
        allSchedules.add(new Schedule("대동제 귀신의 집", "2025-05-21", "2025-05-23"));

        updateCalendar();

        btnPrevMonth.setOnClickListener(v -> {
            currentCalendar.add(Calendar.MONTH, -1);
            updateCalendar();
        });

        btnNextMonth.setOnClickListener(v -> {
            currentCalendar.add(Calendar.MONTH, 1);
            updateCalendar();
        });
    }


    private void updateCalendar() {
        int year = currentCalendar.get(Calendar.YEAR);
        int month = currentCalendar.get(Calendar.MONTH) + 1;

        // 월 타이틀 갱신
        tvMonthTitle.setText(year + "년 " + month + "월");

        // 날짜 목록 생성
        dayList = generateCalendarDays(currentCalendar);
        dayAdapter = new DayAdapter(dayList, this);
        calendarRecyclerView.setLayoutManager(new GridLayoutManager(this, 7));
        calendarRecyclerView.setAdapter(dayAdapter);

        // 일정 필터링 후 세팅
        scheduleList = filterSchedulesByMonth(year, month);
        scheduleAdapter = new ScheduleAdapter(scheduleList);
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSchedule.setAdapter(scheduleAdapter);
    }

    private List<Day> generateCalendarDays(Calendar cal) {
        List<Day> days = new ArrayList<>();
        Calendar calendar = (Calendar) cal.clone();
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int totalCells = 42;
        int dayNumber = 1;

        for (int i = 0; i < totalCells; i++) {
            if (i < firstDayOfWeek || dayNumber > maxDays) {
                days.add(new Day(0, false));
            } else {
                days.add(new Day(dayNumber++, true));
            }
        }
        return days;
    }

    private List<Schedule> filterSchedulesByMonth(int year, int month) {
        List<Schedule> filtered = new ArrayList<>();
        for (Schedule s : allSchedules) {
            String startDateStr = s.getStartDate();
            String endDateStr = s.getEndDate();

            if (startDateStr == null || startDateStr.isEmpty() ||
                    endDateStr == null || endDateStr.isEmpty()) {
                continue; // 날짜 정보가 없는 일정은 무시
            }

            LocalDate start = LocalDate.parse(startDateStr);
            LocalDate end = LocalDate.parse(endDateStr);

            // Schedule 시작일 또는 종료일이 현재 월에 포함되어야 표시됨
            if ((start.getYear() == year && start.getMonthValue() == month) ||
                    (end.getYear() == year && end.getMonthValue() == month) ||
                    (start.isBefore(LocalDate.of(year, month, 1)) &&
                            end.isAfter(LocalDate.of(year, month, 1)))) {
                filtered.add(s);  // 현재 달에 포함되는 일정만 보여줌
            }

        }
        return filtered;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 200 && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            String startDate = data.getStringExtra("startDate");
            String endDate = data.getStringExtra("endDate");

            Schedule newSchedule = new Schedule(title, startDate, endDate);
            allSchedules.add(newSchedule);  // 🔥 allSchedules에 추가
            updateCalendar();               // 🔥 화면 다시 그림
        }
    }



}


