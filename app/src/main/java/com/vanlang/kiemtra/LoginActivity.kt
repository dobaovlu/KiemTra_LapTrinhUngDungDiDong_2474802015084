package com.vanlang.kiemtra

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    // Ánh xạ View bằng findViewById (KHÔNG dùng ViewBinding)
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvStatus = findViewById(R.id.tvStatus)

        btnLogin.setOnClickListener { validateLogin() }
    }

    private fun validateLogin() {
        val email = edtEmail.text.toString().trim()
        val password = edtPassword.text.toString()

        val error = when {
            email.isEmpty() && password.isEmpty() -> getString(R.string.err_both_empty)
            email.isEmpty() -> getString(R.string.err_email_empty)
            password.isEmpty() -> getString(R.string.err_password_empty)
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> getString(R.string.err_email_format)
            password.length < 6 -> getString(R.string.err_password_short)
            else -> null
        }

        if (error != null) {
            tvStatus.setTextColor(Color.parseColor("#D32F2F"))
            tvStatus.text = error
        } else {
            tvStatus.setTextColor(Color.parseColor("#2E7D32"))
            tvStatus.text = getString(R.string.validation_success)

            // Explicit Intent: chuyển sang ProfileActivity, gửi kèm email qua putExtra()
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra(ProfileActivity.EXTRA_EMAIL, email)
            startActivity(intent)
        }
    }
}
