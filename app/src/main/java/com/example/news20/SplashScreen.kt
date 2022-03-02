package com.example.news20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val newslogo : ImageView = findViewById(R.id.newslogo)
        newslogo.alpha = 0f
        newslogo.animate().setDuration(1500).alpha(1f).withEndAction{

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

        val newstext : TextView = findViewById(R.id.newstext)
        newstext.alpha = 0f
        newstext.animate().setDuration(1500).alpha(1f).withEndAction{

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}