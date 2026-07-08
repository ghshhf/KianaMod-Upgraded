"""Extract HHkey placeholder images and copy for all 8 new relics."""
import os, zipfile, shutil

BASE = r"E:\项目改变\KianaMod-m3"
ORIG_JAR = r"F:\steam\steamapps\workshop\content\646570\3559292072\kiana-mod.jar"
IMG_DIR = os.path.join(BASE, "ExampleModResources", "img", "relics")

# New relic class names
RELIC_NAMES = [
    "HeavenFireMark", "VoidRealm", "FinalityOath", "Moonlight",
    "TruthKey", "BloodRose", "BlackWhiteFlower", "EdenStar"
]

os.makedirs(IMG_DIR, exist_ok=True)

# Extract HHkey.png and HHkey_Outline.png from original jar
with zipfile.ZipFile(ORIG_JAR, "r") as z:
    hhkey_png = z.read("ExampleModResources/img/relics/HHkey.png")
    hhkey_outline = z.read("ExampleModResources/img/relics/HHkey_Outline.png")
    print(f"Extracted HHkey.png: {len(hhkey_png)} bytes")
    print(f"Extracted HHkey_Outline.png: {len(hhkey_outline)} bytes")

# Write each new relic image
for name in RELIC_NAMES:
    png_path = os.path.join(IMG_DIR, f"{name}.png")
    outline_path = os.path.join(IMG_DIR, f"{name}_Outline.png")
    with open(png_path, "wb") as f:
        f.write(hhkey_png)
    with open(outline_path, "wb") as f:
        f.write(hhkey_outline)
    print(f"Written: {name}.png / {name}_Outline.png")

print(f"\nAll {len(RELIC_NAMES)} relic images ready in {IMG_DIR}")
