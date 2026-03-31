package com.kidsgame.app

import android.view.View

/**
 * 数学游戏 - 20以内加减法
 * 苏州一年级下册：10以内加减法 + 20以内进退位加减法
 */
class MathGameActivity : BaseGameActivity() {

    data class MathQuestion(
        val expression: String,   // 算式，如 "3 + 5 = ?"
        val answer: Int,          // 正确答案
        val options: List<Int>    // 4个选项
    )

    private lateinit var questions: List<MathQuestion>

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        questions = generateQuestions()
        super.onCreate(savedInstanceState)
    }

    private fun generateQuestions(): List<MathQuestion> {
        val result = mutableListOf<MathQuestion>()
        val random = java.util.Random()

        repeat(totalQuestions) {
            val q = generateOneQuestion(random)
            result.add(q)
        }
        return result
    }

    private fun generateOneQuestion(random: java.util.Random): MathQuestion {
        // 一年级下册：10以内 + 11-20以内
        val isAddition = random.nextBoolean()

        return if (isAddition) {
            // 加法：和不超过20
            val a = random.nextInt(10) + 1       // 1-10
            val b = random.nextInt(20 - a) + 1    // 1到(20-a)
            val answer = a + b
            val expression = "$a + $b = ?"
            MathQuestion(expression, answer, generateOptions(answer, 0, 20))
        } else {
            // 减法：被减数1-20，结果≥0
            val a = random.nextInt(19) + 2        // 2-20
            val b = random.nextInt(a) + 1         // 1到a
            val answer = a - b
            val expression = "$a - $b = ?"
            MathQuestion(expression, answer, generateOptions(answer, 0, a))
        }
    }

    private fun generateOptions(correct: Int, min: Int, max: Int): List<Int> {
        val options = mutableSetOf(correct)
        val random = java.util.Random()
        val range = (min..max).toList()

        // 生成3个错误但合理的答案
        val distractors = listOf(correct - 2, correct - 1, correct + 1, correct + 2)
            .filter { it in min..max && it != correct }
            .shuffled()
            .take(3)
            .toMutableList()

        // 补充随机选项
        while (distractors.size < 3) {
            val rand = range.random()
            if (rand !in options && rand !in distractors) {
                distractors.add(rand)
            }
        }

        options.addAll(distractors.take(3))
        return options.toList().shuffled()
    }

    override fun getGameTitle(): String = "🔢 数学 - 加减法"

    override fun loadQuestion(index: Int) {
        val q = questions[index]

        // 显示算式
        binding.tvQuestion.text = q.expression
        binding.tvQuestion.textSize = 42f
        binding.tvQuestionSub.visibility = View.GONE
        binding.tvQuestionHint.text = "算出正确答案"

        // 设置选项
        val shuffledOptions = q.options.shuffled()
        correctAnswer = shuffledOptions.indexOf(q.answer)

        val buttons = getAnswerButtons()
        shuffledOptions.forEachIndexed { i, option ->
            buttons[i].text = option.toString()
            buttons[i].textSize = 30f
        }
    }
}
