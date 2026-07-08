"""KianaMod jar packager.
Usage (called from build.bat):
  python build_pack.py

Reads compiled .class files from classes/, packages them into
dist/kiana-mod-upgraded-v1.1.0.jar using dist/kiana-mod-original-v1.0.0.jar as base.
Also injects new relic images and merges localization additions.
"""
import json, os, zipfile, fnmatch

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
DIST = os.path.join(BASE_DIR, "dist")
CLASSES = os.path.join(BASE_DIR, "classes")
RESOURCES = os.path.join(BASE_DIR, "ExampleModResources")
ORIG_JAR = os.path.join(DIST, "kiana-mod-original-v1.0.0.jar")
OUT_JAR = os.path.join(DIST, "kiana-mod-upgraded-v1.1.0.jar")

# Collect newly compiled classes (relative arcname = package path)
new_classes = {}
for root, _dirs, files in os.walk(CLASSES):
    for fn in files:
        if not fn.endswith(".class"):
            continue
        full = os.path.join(root, fn)
        arc = os.path.relpath(full, CLASSES).replace("\\", "/")
        new_classes[arc] = open(full, "rb").read()

print(f"  New .class files: {len(new_classes)}")

# Collect new relic images to inject
new_images = {}
img_dir = os.path.join(RESOURCES, "img", "relics")
if os.path.isdir(img_dir):
    for fn in os.listdir(img_dir):
        if fn.endswith(".png"):
            full = os.path.join(img_dir, fn)
            arc = "ExampleModResources/img/relics/" + fn
            new_images[arc] = open(full, "rb").read()
    print(f"  New relic images: {len(new_images)}")

# Collect new card images to inject (from ExampleModResources/images/)
new_card_images = {}
card_img_dir = os.path.join(RESOURCES, "images")
if os.path.isdir(card_img_dir):
    for fn in os.listdir(card_img_dir):
        if fn.endswith(".png"):
            full = os.path.join(card_img_dir, fn)
            arc = "ExampleModResources/images/" + fn
            new_card_images[arc] = open(full, "rb").read()
    print(f"  New card images: {len(new_card_images)}")

# Read base jar and rebuild
zin = zipfile.ZipFile(ORIG_JAR, "r")
zout = zipfile.ZipFile(OUT_JAR, "w", zipfile.ZIP_DEFLATED)

injected = set()
for item in zin.infolist():
    n = item.filename
    if n in new_classes:
        # Replace with newly compiled version
        zout.writestr(n, new_classes[n])
        injected.add(n)
    elif n == "ModTheSpire.json":
        data = json.loads(zin.read(n).decode("utf-8", "ignore"))
        old_ver = data.get("version")
        data["version"] = "1.1.0"
        zout.writestr(n, json.dumps(data, indent=2, ensure_ascii=False).encode("utf-8"))
        if old_ver != "1.1.0":
            print(f"  ModTheSpire.json: {old_ver} -> 1.1.0")
    elif n == "ExampleResources/localization/ZHS/cards.json":
        # Check for additions file and merge if present
        add_path = os.path.join(
            BASE_DIR, "ExampleModResources", "localization", "ZHS", "cards_additions.json"
        )
        cards = json.loads(zin.read(n).decode("utf-8", "ignore"))
        if os.path.exists(add_path):
            additions = json.load(open(add_path, encoding="utf-8"))
            cards.update(additions)
            print(f"  cards.json merged: +{len(additions)} entries")
        zout.writestr(n, json.dumps(cards, indent=2, ensure_ascii=False).encode("utf-8"))
    elif n == "ExampleResources/localization/ZHS/relics.json":
        # Check for relics additions file and merge if present
        add_path = os.path.join(
            BASE_DIR, "ExampleModResources", "localization", "ZHS", "relics_additions.json"
        )
        relics = json.loads(zin.read(n).decode("utf-8", "ignore"))
        if os.path.exists(add_path):
            additions = json.load(open(add_path, encoding="utf-8"))
            relics.update(additions)
            print(f"  relics.json merged: +{len(additions)} entries")
        zout.writestr(n, json.dumps(relics, indent=2, ensure_ascii=False).encode("utf-8"))
    elif n.startswith("ExampleModResources/img/relics/") and n in new_images:
        # Replace with new image (but keep original if we don't have a replacement)
        zout.writestr(n, new_images[n])
        injected.add(n)
    elif n.startswith("ExampleModResources/images/") and n in new_card_images:
        # Replace with new card image
        zout.writestr(n, new_card_images[n])
        injected.add(n)
    else:
        zout.writestr(item, zin.read(n))

# Inject any new classes not yet written
for arc, data in new_classes.items():
    if arc not in injected:
        zout.writestr(arc, data)
        injected.add(arc)
        print(f"  Injected class: {arc}")

# Inject any new images not yet written
for arc, data in new_images.items():
    if arc not in injected:
        zout.writestr(arc, data)
        injected.add(arc)
        print(f"  Injected image: {arc}")

# Inject any new card images not yet written
for arc, data in new_card_images.items():
    if arc not in injected:
        zout.writestr(arc, data)
        injected.add(arc)
        print(f"  Injected card image: {arc}")

zin.close()
zout.close()
size_kb = os.path.getsize(OUT_JAR) // 1024
print(f"  Written: {OUT_JAR} ({size_kb} KB)")
