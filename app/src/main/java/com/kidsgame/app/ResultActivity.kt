package com.kidsgame.app

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.kidsgame.app.databinding.ActivityResultBinding

/**
 * 结果页面 - 显示最终得分和星级评价
 */
class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 10)
        val gameClass = intent.getStringExtra("gameClass") ?: ""

        displayResult(score, total)

        binding.btnPlayAgain.setOnClickListener {
            // 重新启动同一个游戏
            try {
                val clazz = Class.forName(gameClass)
                startActivity(Intent(this, clazz))
                finish()
            } catch (e: Exception) {
                finish()
            }
        }

        binding.btnBackHome.setOnClickListener {
            // 返回主页（清空返回栈）
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }

    private fun displayResult(score: Int, total: Int) {
        val percentage = score.toFloat() / total

        // 根据得分显示不同表情和消息
        val (emoji, message) = when {
            percentage >= 0.9f -> Pair("🏆", "超级棒！满分达人！")
            percentage >= 0.7f -> Pair("🌟", "真厉害！继续加油！")
            percentage >= 0.5f -> Pair("😊", "不错哦！再练练！")
            else               -> Pair("💪", "继续努力，你能行！")
        }

        // 星星评分（最多5颗星）
        val stars = when {
            percentage >= 0.9f -> "⭐⭐⭐⭐⭐"
            percentage >= 0.7f -> "⭐⭐⭐⭐"
            percentage >= 0.5f -> "⭐⭐⭐"
            percentage >= 0.3f -> "⭐⭐"
            else               -> "⭐"
        }

        binding.tvResultEmoji.text = emoji
        binding.tvFinalScore.text = "答对了 $score / $total 道题\n$stars\n$message"

        // 动画
        val popIn = AnimationUtils.loadAnimation(this, R.anim.pop_in)
        binding.tvResultEmoji.startAnimation(popIn)
    }
}
