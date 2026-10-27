package com.vanlang.kiemtra

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    // Ánh xạ View bằng findViewById (KHÔNG dùng ViewBinding)
    private lateinit var tvEmail: TextView
    private lateinit var btnLogout: Button
    private lateinit var lvSkills: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tvEmail = findViewById(R.id.tvEmail)
        btnLogout = findViewById(R.id.btnLogout)
        lvSkills = findViewById(R.id.lvSkills)

        // Nhận email được gửi từ LoginActivity qua Intent
        val email = intent.getStringExtra(EXTRA_EMAIL).orEmpty()
        tvEmail.text = getString(R.string.profile_email, email)

        // Đăng xuất: đóng màn hình hiện tại, quay về LoginActivity
        btnLogout.setOnClickListener { finish() }

        // Câu 3: hiển thị danh sách môn học/kỹ năng bằng ListView + ArrayAdapter
        val skills = listOf(
            "Kotlin Programming",
            "XML Layout Design",
            "Git/GitHub Management",
            "Intent Processing",
            "Android Architecture",
            "ListView & ArrayAdapter"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, skills)
        lvSkills.adapter = adapter

        lvSkills.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, "Bạn đã chọn: ${skills[position]}", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val EXTRA_EMAIL = "EXTRA_EMAIL"
    }
}
