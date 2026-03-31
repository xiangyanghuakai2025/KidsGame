package com.kidsgame.app

import android.view.View

/**
 * 语文游戏 - 看拼音选汉字
 * 题库：苏教版一年级下册必学生字（含拼音）
 */
class ChineseGameActivity : BaseGameActivity() {

    data class ChineseQuestion(
        val pinyin: String,       // 拼音提示（大号显示）
        val correct: String,      // 正确汉字
        val options: List<String> // 4个选项（含正确答案，已打乱）
    )

    private val questionBank = listOf(
        // 一年级下册核心字 - 人教版/苏教版通用
        ChineseQuestion("chūn", "春", listOf("春", "秦", "泰", "椿")),
        ChineseQuestion("huā", "花", listOf("花", "华", "画", "化")),
        ChineseQuestion("niǎo", "鸟", listOf("鸟", "乌", "岛", "鸡")),
        ChineseQuestion("yú", "鱼", listOf("鱼", "渔", "飞", "羊")),
        ChineseQuestion("mǎ", "马", listOf("马", "鸟", "牛", "羊")),
        ChineseQuestion("niú", "牛", listOf("牛", "午", "羊", "马")),
        ChineseQuestion("yáng", "羊", listOf("羊", "洋", "样", "阳")),
        ChineseQuestion("mén", "门", listOf("门", "问", "闯", "闸")),
        ChineseQuestion("rì", "日", listOf("日", "目", "白", "月")),
        ChineseQuestion("yuè", "月", listOf("月", "日", "目", "用")),
        ChineseQuestion("shuǐ", "水", listOf("水", "冰", "永", "求")),
        ChineseQuestion("huǒ", "火", listOf("火", "灰", "秋", "山")),
        ChineseQuestion("shān", "山", listOf("山", "出", "仙", "由")),
        ChineseQuestion("tián", "田", listOf("田", "由", "甲", "申")),
        ChineseQuestion("rén", "人", listOf("人", "入", "八", "大")),
        ChineseQuestion("kǒu", "口", listOf("口", "日", "目", "旦")),
        ChineseQuestion("shǒu", "手", listOf("手", "毛", "才", "午")),
        ChineseQuestion("ěr", "耳", listOf("耳", "耳", "目", "口")).copy(
            options = listOf("耳", "目", "口", "鼻")
        ),
        ChineseQuestion("mù", "目", listOf("目", "日", "口", "耳")),
        ChineseQuestion("zú", "足", listOf("足", "走", "虫", "里")),
        ChineseQuestion("lín", "林", listOf("林", "森", "木", "树")),
        ChineseQuestion("sēn", "森", listOf("森", "林", "木", "树")),
        ChineseQuestion("mù", "木", listOf("木", "本", "末", "林")),
        ChineseQuestion("tǔ", "土", listOf("土", "士", "王", "主")),
        ChineseQuestion("tiān", "天", listOf("天", "夫", "太", "大")),
        ChineseQuestion("dì", "地", listOf("地", "他", "池", "坐")),
        ChineseQuestion("fēng", "风", listOf("风", "凤", "虹", "雨")),
        ChineseQuestion("yún", "云", listOf("云", "去", "厶", "么")),
        ChineseQuestion("xuě", "雪", listOf("雪", "雨", "雷", "霜")),
        ChineseQuestion("cǎo", "草", listOf("草", "早", "苦", "花"))
    )

    private lateinit var shuffledQuestions: List<ChineseQuestion>

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        shuffledQuestions = questionBank.shuffled().take(totalQuestions)
        super.onCreate(savedInstanceState)
    }

    override fun getGameTitle(): String = "📖 语文 - 看拼音选字"

    override fun loadQuestion(index: Int) {
        val q = shuffledQuestions[index]

        // 大号显示拼音
        binding.tvQuestion.text = q.pinyin
        binding.tvQuestion.textSize = 52f
        binding.tvQuestionSub.visibility = View.VISIBLE
        binding.tvQuestionSub.text = "这个拼音对应哪个汉字？"
        binding.tvQuestionHint.text = "选择正确的汉字"

        // 打乱选项顺序，找出正确答案的新位置
        val shuffledOptions = q.options.shuffled()
        correctAnswer = shuffledOptions.indexOf(q.correct)

        val buttons = getAnswerButtons()
        shuffledOptions.forEachIndexed { i, option ->
            buttons[i].text = option
            buttons[i].textSize = 32f
        }
    }
}
