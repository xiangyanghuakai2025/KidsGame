"""
生成安卓 mipmap 图标 PNG（各分辨率）
颜色：珊瑚红 + 白色星星
"""
import os

try:
    from PIL import Image, ImageDraw
except ImportError:
    import subprocess, sys
    subprocess.check_call([sys.executable, "-m", "pip", "install", "pillow", "-q"])
    from PIL import Image, ImageDraw

BASE = r"c:\Users\Administrator\WorkBuddy\20260331183732\KidsGame\app\src\main\res"

sizes = {
    "mipmap-mdpi":    48,
    "mipmap-hdpi":    72,
    "mipmap-xhdpi":   96,
    "mipmap-xxhdpi":  144,
    "mipmap-xxxhdpi": 192,
}

BG_COLOR = (255, 107, 107)   # 珊瑚红
STAR_COLOR = (255, 255, 255)

def draw_star(draw, cx, cy, r_outer, r_inner, points=5, color=(255,255,255)):
    import math
    coords = []
    for i in range(points * 2):
        angle = math.radians(i * 180 / points - 90)
        r = r_outer if i % 2 == 0 else r_inner
        coords.append((cx + r * math.cos(angle), cy + r * math.sin(angle)))
    draw.polygon(coords, fill=color)

for folder, size in sizes.items():
    for name in ["ic_launcher.png", "ic_launcher_round.png"]:
        path = os.path.join(BASE, folder, name)
        os.makedirs(os.path.dirname(path), exist_ok=True)

        if "round" in name:
            img = Image.new("RGBA", (size, size), (0, 0, 0, 0))
            draw = ImageDraw.Draw(img)
            draw.ellipse([0, 0, size-1, size-1], fill=BG_COLOR)
        else:
            img = Image.new("RGB", (size, size), BG_COLOR)
            draw = ImageDraw.Draw(img)

        # 画星星
        cx, cy = size // 2, size // 2
        r_out = size * 0.35
        r_in  = size * 0.15
        draw_star(draw, cx, cy - size * 0.03, r_out, r_in, 5, STAR_COLOR)

        img.save(path)
        print(f"  saved: {path}")

print("All icons generated!")
