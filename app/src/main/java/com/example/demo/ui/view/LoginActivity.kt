package com.example.demo.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        context = this@LoginActivity

        btn_login.setOnClickListener {
            val nextScreenIntent = Intent(this, MainActivity::class.java)
            startActivity(nextScreenIntent)
            finish()
        }

    }
}
