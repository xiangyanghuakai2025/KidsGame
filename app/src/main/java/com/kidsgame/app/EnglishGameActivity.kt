package com.kidsgame.app

import android.view.View

/**
 * 英语游戏 - 看图选单词（emoji图 + 英文单词）
 * 苏州一年级英语：动物、颜色、数字、水果、身体部位
 */
class EnglishGameActivity : BaseGameActivity() {

    data class EnglishQuestion(
        val emoji: String,        // emoji图（替代图片）
        val chineseHint: String,  // 中文提示（辅助小朋友理解）
        val correct: String,      // 正确英文单词
        val options: List<String> // 4个选项
    )

    private val questionBank = listOf(
        // 动物 Animals
        EnglishQuestion("🐱", "猫咪", "cat", listOf("cat", "car", "bat", "hat")),
        EnglishQuestion("🐶", "小狗", "dog", listOf("dog", "log", "fog", "bog")),
        EnglishQuestion("🐸", "青蛙", "frog", listOf("frog", "from", "fog", "free")),
        EnglishQuestion("🐟", "鱼", "fish", listOf("fish", "dish", "wish", "kiss")),
        EnglishQuestion("🐦", "鸟", "bird", listOf("bird", "word", "born", "barn")),
        EnglishQuestion("🐰", "兔子", "rabbit", listOf("rabbit", "rabbit", "carrot", "cabbit")).copy(
            options = listOf("rabbit", "carrot", "parrot", "racket")
        ),
        EnglishQuestion("🐻", "熊", "bear", listOf("bear", "beer", "pear", "dear")),
        EnglishQuestion("🐘", "大象", "elephant", listOf("elephant", "elegant", "element", "elevator")),

        // 水果 Fruits
        EnglishQuestion("🍎", "苹果", "apple", listOf("apple", "ample", "ankle", "maple")),
        EnglishQuestion("🍌", "香蕉", "banana", listOf("banana", "bonnet", "canvas", "vanilla")),
        EnglishQuestion("🍊", "橙子", "orange", listOf("orange", "arrange", "manage", "strange")),
        EnglishQuestion("🍇", "葡萄", "grape", listOf("grape", "gripe", "grope", "grace")),
        EnglishQuestion("🍓", "草莓", "strawberry", listOf("strawberry", "blueberry", "raspberry", "cranberry")),
        EnglishQuestion("🍉", "西瓜", "watermelon", listOf("watermelon", "honeydew", "cantaloupe", "pineapple")),

        // 颜色 Colors
        EnglishQuestion("🔴", "红色", "red", listOf("red", "rod", "rid", "rag")),
        EnglishQuestion("🔵", "蓝色", "blue", listOf("blue", "glue", "clue", "flue")),
        EnglishQuestion("🟡", "黄色", "yellow", listOf("yellow", "fellow", "jello", "bellow")),
        EnglishQuestion("🟢", "绿色", "green", listOf("green", "greet", "creek", "queen")),
        EnglishQuestion("⚫", "黑色", "black", listOf("black", "blank", "blade", "bland")),
        EnglishQuestion("⚪", "白色", "white", listOf("white", "write", "while", "quite")),

        // 数字 Numbers
        EnglishQuestion("1️⃣", "数字1", "one", listOf("one", "one", "won", "ton")).copy(
            options = listOf("one", "two", "three", "four")
        ),
        EnglishQuestion("2️⃣", "数字2", "two", listOf("two", "one", "ten", "six")),
        EnglishQuestion("3️⃣", "数字3", "three", listOf("three", "tree", "free", "agree")),
        EnglishQuestion("5️⃣", "数字5", "five", listOf("five", "fine", "fire", "hire")),
        EnglishQuestion("🔟", "数字10", "ten", listOf("ten", "hen", "pen", "den")),

        // 身体部位 Body
        EnglishQuestion("👁️", "眼睛", "eye", listOf("eye", "ear", "arm", "leg")),
        EnglishQuestion("👃", "鼻子", "nose", listOf("nose", "rose", "pose", "hose")),
        EnglishQuestion("👂", "耳朵", "ear", listOf("ear", "eat", "year", "fear")),
        EnglishQuestion("👋", "手", "hand", listOf("hand", "land", "band", "sand")),
        EnglishQuestion("🦵", "腿", "leg", listOf("leg", "peg", "beg", "keg"))
    )

    private lateinit var shuffledQuestions: List<EnglishQuestion>

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        shuffledQuestions = questionBank.shuffled().take(totalQuestions)
        super.onCreate(savedInstanceState)
    }

    override fun getGameTitle(): String = "🔤 英语 - 看图选词"

    override fun loadQuestion(index: Int) {
        val q = shuffledQuestions[index]

        // 大号 emoji + 中文提示
        binding.tvQuestion.text = q.emoji
        binding.tvQuestion.textSize = 72f
        binding.tvQuestionSub.visibility = View.VISIBLE
        binding.tvQuestionSub.text = "（${q.chineseHint}）"
        binding.tvQuestionHint.text = "选出正确的英文单词"

        // 打乱选项
        val shuffledOptions = q.options.shuffled()
        correctAnswer = shuffledOptions.indexOf(q.correct)

        val buttons = getAnswerButtons()
        shuffledOptions.forEachIndexed { i, option ->
            buttons[i].text = option
            buttons[i].textSize = 22f
        }
    }
}
