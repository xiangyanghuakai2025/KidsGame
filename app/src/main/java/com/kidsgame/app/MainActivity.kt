package com.kidsgame.app

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.kidsgame.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCards()
    }

    private fun setupCards() {
        val popIn = AnimationUtils.loadAnimation(this, R.anim.pop_in)

        // 语文卡片
        binding.cardChinese.apply {
            startAnimation(popIn)
            setOnClickListener {
                startActivity(Intent(this@MainActivity, ChineseGameActivity::class.java))
            }
        }

        // 延迟显示数学卡片
        binding.cardMath.postDelayed({
            binding.cardMath.startAnimation(popIn)
        }, 100)
        binding.cardMath.setOnClickListener {
            startActivity(Intent(this, MathGameActivity::class.java))
        }

        // 延迟显示英语卡片
        binding.cardEnglish.postDelayed({
            binding.cardEnglish.startAnimation(popIn)
        }, 200)
        binding.cardEnglish.setOnClickListener {
            startActivity(Intent(this, EnglishGameActivity::class.java))
        }
    }
}
