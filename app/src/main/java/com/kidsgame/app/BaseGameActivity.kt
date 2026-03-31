package com.kidsgame.app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kidsgame.app.databinding.ActivityGameBinding

/**
 * 游戏基础类 - 所有学科游戏继承此类
 * 提供统一的答题流程、计分、进度管理
 */
abstract class BaseGameActivity : AppCompatActivity() {

    protected lateinit var binding: ActivityGameBinding

    protected var currentScore = 0
    protected var currentQuestion = 0
    protected val totalQuestions = 10
    protected var correctAnswer = 0
    protected var answered = false

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTitle.text = getGameTitle()
        binding.progressBar.max = totalQuestions

        binding.btnBack.setOnClickListener { finish() }

        setupAnswerButtons()
        showNextQuestion()
    }

    private fun setupAnswerButtons() {
        val buttons = getAnswerButtons()
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                if (!answered) {
                    checkAnswer(index)
                }
            }
        }
    }

    protected fun getAnswerButtons(): List<Button> {
        return listOf(
            binding.btnAnswer0,
            binding.btnAnswer1,
            binding.btnAnswer2,
            binding.btnAnswer3
        )
    }

    private fun checkAnswer(selectedIndex: Int) {
        answered = true
        val buttons = getAnswerButtons()

        if (selectedIndex == correctAnswer) {
            // 答对
            currentScore++
            buttons[selectedIndex].background = getDrawable(R.drawable.btn_correct_bg)
            buttons[selectedIndex].setTextColor(getColor(R.color.text_white))
            showFeedback(true)
        } else {
            // 答错 - 摇动按钮，标红，同时高亮正确答案
            val shakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake)
            buttons[selectedIndex].startAnimation(shakeAnim)
            buttons[selectedIndex].background = getDrawable(R.drawable.btn_wrong_bg)
            buttons[selectedIndex].setTextColor(getColor(R.color.text_white))
            // 显示正确答案
            buttons[correctAnswer].background = getDrawable(R.drawable.btn_correct_bg)
            buttons[correctAnswer].setTextColor(getColor(R.color.text_white))
            showFeedback(false)
        }

        updateScore()

        // 1.2秒后进入下一题
        handler.postDelayed({
            currentQuestion++
            if (currentQuestion >= totalQuestions) {
                showResult()
            } else {
                resetButtons()
                showNextQuestion()
            }
        }, 1200)
    }

    private fun showFeedback(correct: Boolean) {
        binding.layoutFeedback.visibility = android.view.View.VISIBLE
        if (correct) {
            binding.tvFeedback.text = listOf(
                "太棒了！🎉", "答对了！✨", "真厉害！🌟", "棒棒哒！👏", "你真聪明！🎊"
            ).random()
            binding.tvFeedback.setTextColor(getColor(R.color.btn_correct))
        } else {
            binding.tvFeedback.text = listOf(
                "再想想哦～", "没关系，继续加油！", "下次一定行！💪", "要记住哦～"
            ).random()
            binding.tvFeedback.setTextColor(getColor(R.color.btn_wrong))
        }
        val popIn = AnimationUtils.loadAnimation(this, R.anim.pop_in)
        binding.layoutFeedback.startAnimation(popIn)
    }

    private fun resetButtons() {
        answered = false
        binding.layoutFeedback.visibility = android.view.View.GONE
        val normalBg = getDrawable(R.drawable.btn_answer_bg)
        getAnswerButtons().forEach { btn ->
            btn.background = normalBg
            btn.setTextColor(getColor(R.color.text_primary))
        }
    }

    private fun updateScore() {
        binding.tvScore.text = "⭐ $currentScore"
        binding.progressBar.progress = currentQuestion + 1
        binding.tvProgress.text = "${currentQuestion + 1}/$totalQuestions"
    }

    private fun showResult() {
        val intent = android.content.Intent(this, ResultActivity::class.java)
        intent.putExtra("score", currentScore)
        intent.putExtra("total", totalQuestions)
        intent.putExtra("gameClass", this::class.java.name)
        startActivity(intent)
        finish()
    }

    protected fun showNextQuestion() {
        binding.progressBar.progress = currentQuestion
        binding.tvProgress.text = "$currentQuestion/$totalQuestions"
        answered = false

        val popIn = AnimationUtils.loadAnimation(this, R.anim.pop_in)
        binding.layoutQuestion.startAnimation(popIn)

        loadQuestion(currentQuestion)
    }

    /** 子类实现：返回游戏标题 */
    abstract fun getGameTitle(): String

    /** 子类实现：加载第 index 题 */
    abstract fun loadQuestion(index: Int)
}
