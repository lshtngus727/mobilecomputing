package com.example.project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etStudentId, etEmail, etPassword, etPasswordConfirm;
    Spinner spinnerRole, spinnerDepartment; // 학과 Spinner 추가
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 입력 필드 연결
        etName = findViewById(R.id.etName);
        etStudentId = findViewById(R.id.etStudentId);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        spinnerRole = findViewById(R.id.spinnerRole);
        spinnerDepartment = findViewById(R.id.spinnerDepartment); // 학과 Spinner 연결
        btnRegister = findViewById(R.id.btnRegister);

        // 역할 Spinner 초기화
        ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new String[]{"지원자", "동아리장", "관리자"}
        );
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(roleAdapter);

        // 학과 Spinner 초기화
        ArrayAdapter<CharSequence> departmentAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.departments,
                android.R.layout.simple_spinner_item
        );
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(departmentAdapter);

        // 버튼 클릭 이벤트 처리
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                String confirmPassword = etPasswordConfirm.getText().toString();

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                    return;
                }

                String name = etName.getText().toString();
                String studentId = etStudentId.getText().toString();
                String department = spinnerDepartment.getSelectedItem().toString(); // 학과 선택값 가져오기
                String email = etEmail.getText().toString();
                String role = spinnerRole.getSelectedItem().toString();

                // SharedPreferences에 저장
                SharedPreferences pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("name", name);
                editor.putString("studentId", studentId);
                editor.putString("department", department);
                editor.putString("email", email.trim());
                editor.putString("password", password.trim());
                editor.putString("role", role);
                editor.putBoolean("isLoggedIn", false);
                editor.apply();

                Toast.makeText(RegisterActivity.this, "회원가입 완료", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
