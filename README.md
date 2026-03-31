# 🚀 KidsGame - 一年级学习游戏

这是一个为苏州一年级下学期小朋友设计的 Android 学习游戏应用，包含**语文、数学、英语**三个游戏模块。

## 📱 游戏特色

| 学科 | 游戏类型 | 题数 |
|------|---------|------|
| 📖 **语文** | 看拼音选汉字 | 30题 |
| 🔢 **数学** | 20以内加减法 | 随机出题 |
| 🔤 **英语** | 看emoji图选英文单词 | 30题 |

## 🎯 核心特性

✅ **一年级友好的UI** - 大按钮、彩色界面、简单操作
✅ **即时反馈** - 答对绿色✓，答错红色✗ + 摇动动画
✅ **进度追踪** - 实时显示做对了几题
✅ **星级评价** - 完成游戏后显示成绩评分
✅ **Material Design 3** - 现代化设计风格

## 🛠️ 技术栈

- **语言**: Kotlin
- **构建系统**: Gradle
- **最低版本**: Android 5.0 (API 21)
- **目标版本**: Android 14 (API 34)
- **布局**: ViewBinding + Material Design 3

## 📦 如何编译

### 方案一：使用 Android Studio（推荐）
1. 下载 [Android Studio](https://developer.android.google.cn/studio)
2. 打开此项目文件夹
3. 等待 Gradle 同步
4. 点击 **Run ▶** 运行

### 方案二：使用 GitHub Actions（无需本地编译）
1. 创建 GitHub 仓库并推送代码
2. GitHub Actions 自动编译 APK
3. 在 Actions 页面下载 `app-debug.apk`

## 🔧 项目结构

```
KidsGame/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/kidsgame/app/
│           │   ├── MainActivity.kt           # 主页面
│           │   ├── ChineseGameActivity.kt    # 语文游戏
│           │   ├── MathGameActivity.kt       # 数学游戏
│           │   ├── EnglishGameActivity.kt    # 英语游戏
│           │   ├── ResultActivity.kt         # 结果页面
│           │   └── BaseGameActivity.kt       # 游戏基类
│           ├── res/
│           │   ├── layout/        # UI布局
│           │   ├── drawable/      # 图片和颜色
│           │   ├── values/        # 字符串和主题
│           │   └── anim/          # 动画
│           └── AndroidManifest.xml
├── build.gradle.kts                # 项目配置
└── settings.gradle.kts
```

## 📝 快速开始

### 本地开发
```bash
# 编译
./gradlew assembleDebug

# 运行到设备
./gradlew installDebug
```

### 推送到 GitHub（自动编译）
在 PowerShell 中运行：
```powershell
.\push-to-github.ps1
```

然后访问：`https://github.com/xiangyanghuakai2025/KidsGame`

## 🎮 游戏玩法

### 📖 语文游戏
- 看拼音或汉字，选择对应的选项
- 10道题，每题 10 秒
- 答对得分，答错显示正确答案

### 🔢 数学游戏
- 看算式，从 4 个选项中选择正确答案
- 20以内加减法，随机出题
- 答对绿色✓，答错红色✗

### 🔤 英语游戏
- 看 emoji 图，选择对应的英文单词
- 涵盖动物、水果、颜色、数字、身体部位
- 30题题库

## 📊 游戏统计

每局游戏 10 道题，结束后显示：
- ⭐ 成绩评级（1-5 星）
- 📈 得分百分比
- 🔄 再玩一次 / 回主页

## 🔐 权限说明

此应用无需：
- 网络权限
- 存储权限
- 定位权限
- 摄像头权限

完全离线运行，100% 安全

## 📧 联系方式

如有问题或建议，请联系开发者。

---

**祝小朋友玩得开心，学习进步！** 🎉
