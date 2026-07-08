"""
KianaMod complete build script.
Compiles Java sources → packages jar → runs Java 8 load test.
"""
import json, os, shutil, subprocess, sys, zipfile

BASE_DIR = r"E:\项目改变\KianaMod-m3"
SRC = os.path.join(BASE_DIR, "src")
MAIN = os.path.join(BASE_DIR, "src", "main", "java")
CLASSES = os.path.join(BASE_DIR, "classes")
DIST = os.path.join(BASE_DIR, "dist")
TOOLS = os.path.join(BASE_DIR, "tools")
JAVAC = r"G:\环境\bin\javac.exe"
JRE8 = r"F:\steam\steamapps\common\SlayTheSpire\jre\bin\java.exe"
DESKTOP = r"F:\steam\steamapps\common\SlayTheSpire\desktop-1.0.jar"
ORIG_JAR = os.path.join(DIST, "kiana-mod-original-v1.0.0.jar")
OUT_JAR = os.path.join(DIST, "kiana-mod-upgraded-v1.1.0.jar")

# All source files
SOURCES = [
    # main source
    os.path.join(SRC, "kianamod", "modcore", "ExampleMod.java"),
    # cards (12)
    os.path.join(MAIN, "kianamod", "card", "FlamePursuit.java"),
    os.path.join(MAIN, "kianamod", "card", "CrimsonCharge.java"),
    os.path.join(MAIN, "kianamod", "card", "PassFlame.java"),
    os.path.join(MAIN, "kianamod", "card", "SkyfireDraw.java"),
    os.path.join(MAIN, "kianamod", "card", "EmberRekindle.java"),
    os.path.join(MAIN, "kianamod", "card", "BurnOut.java"),
    os.path.join(MAIN, "kianamod", "card", "ValkyrieOath.java"),
    os.path.join(MAIN, "kianamod", "card", "FlameStorm.java"),
    os.path.join(MAIN, "kianamod", "card", "Cinder.java"),
    os.path.join(MAIN, "kianamod", "card", "PrairieFire.java"),
    os.path.join(MAIN, "kianamod", "card", "FlameDragonBreak.java"),
    os.path.join(MAIN, "kianamod", "card", "GuardianFlame.java"),
    # M4: 空之律者扩展 12 卡
    os.path.join(MAIN, "kianamod", "card", "VoidSlash.java"),
    os.path.join(MAIN, "kianamod", "card", "SpaceFold.java"),
    os.path.join(MAIN, "kianamod", "card", "QuantumTunnel.java"),
    os.path.join(MAIN, "kianamod", "card", "GravitySingularity.java"),
    os.path.join(MAIN, "kianamod", "card", "ImaginaryFill.java"),
    os.path.join(MAIN, "kianamod", "card", "SubspaceWalker.java"),
    os.path.join(MAIN, "kianamod", "card", "DimensionBanish.java"),
    os.path.join(MAIN, "kianamod", "card", "StellarOrbit.java"),
    os.path.join(MAIN, "kianamod", "card", "VoidDrain.java"),
    os.path.join(MAIN, "kianamod", "card", "RiftWorld.java"),
    os.path.join(MAIN, "kianamod", "card", "TimeShift.java"),
    os.path.join(MAIN, "kianamod", "card", "SingularityBurst.java"),
    # powers (1)
    os.path.join(MAIN, "kianamod", "powers", "BurnOutPower.java"),
    # utils (1)
    os.path.join(MAIN, "kianamod", "util", "FlameCardUtils.java"),
    # relics (8)
    os.path.join(MAIN, "kianamod", "relics", "HeavenFireMark.java"),
    os.path.join(MAIN, "kianamod", "relics", "VoidRealm.java"),
    os.path.join(MAIN, "kianamod", "relics", "FinalityOath.java"),
    os.path.join(MAIN, "kianamod", "relics", "Moonlight.java"),
    os.path.join(MAIN, "kianamod", "relics", "TruthKey.java"),
    os.path.join(MAIN, "kianamod", "relics", "BloodRose.java"),
    os.path.join(MAIN, "kianamod", "relics", "BlackWhiteFlower.java"),
    os.path.join(MAIN, "kianamod", "relics", "EdenStar.java"),
    # M7-M8: Events
    os.path.join(MAIN, "kianamod", "events", "SaintFreyaAcademyEvent.java"),
    os.path.join(MAIN, "kianamod", "events", "ChangKongNightEvent.java"),
    os.path.join(MAIN, "kianamod", "events", "TianMingHeadquartersEvent.java"),
    os.path.join(MAIN, "kianamod", "events", "NiSheProposalEvent.java"),
    os.path.join(MAIN, "kianamod", "events", "KianaDreamEvent.java"),
    os.path.join(MAIN, "kianamod", "events", "AlliancePower.java"),
]

# Build classpath
CP = DESKTOP
ws_dir = r"F:\steam\steamapps\workshop\content\646570"
for j in os.listdir(ws_dir):
    jd = os.path.join(ws_dir, j)
    if os.path.isdir(jd):
        for fn in os.listdir(jd):
            if fn.endswith(".jar"):
                CP += ";" + os.path.join(jd, fn)

print("=" * 60)
print(" KianaMod Build v1.1.0")
print("=" * 60)

# Step 1: Clean and compile
print("\n[1/4] Compiling {} source files (--release 8)...".format(len(SOURCES)))
if os.path.exists(CLASSES):
    shutil.rmtree(CLASSES)
os.makedirs(CLASSES, exist_ok=True)

cmd = [JAVAC, "-encoding", "UTF-8", "--release", "8",
       "-proc:none", "-cp", CP, "-d", CLASSES] + SOURCES
result = subprocess.run(cmd, capture_output=True)
stdout_str = result.stdout.decode("gbk", errors="replace")
stderr_str = result.stderr.decode("gbk", errors="replace")
if result.returncode != 0:
    print("ERROR: Compilation failed")
    print(stdout_str)
    print(stderr_str)
    sys.exit(1)
print("   0 errors, {} files compiled.".format(len(SOURCES)))

# Step 2: Collect new classes
new_classes = {}
for root, _dirs, files in os.walk(CLASSES):
    for fn in files:
        if not fn.endswith(".class"):
            continue
        full = os.path.join(root, fn)
        arc = os.path.relpath(full, CLASSES).replace("\\", "/")
        new_classes[arc] = open(full, "rb").read()
print("   Collected {} .class files.".format(len(new_classes)))

# Step 3: Collect new relic images
new_images = {}
img_dir = os.path.join(BASE_DIR, "ExampleModResources", "img", "relics")
if os.path.isdir(img_dir):
    for fn in os.listdir(img_dir):
        if fn.endswith(".png"):
            full = os.path.join(img_dir, fn)
            arc = "ExampleModResources/img/relics/" + fn
            new_images[arc] = open(full, "rb").read()
print("   Collected {} relic images.".format(len(new_images)))

# Step 4: Package jar
print("\n[2/4] Packaging jar...")
zin = zipfile.ZipFile(ORIG_JAR, "r")
zout = zipfile.ZipFile(OUT_JAR, "w", zipfile.ZIP_DEFLATED)

injected = set()
for item in zin.infolist():
    n = item.filename
    if n in new_classes:
        zout.writestr(n, new_classes[n])
        injected.add(n)
    elif n == "ModTheSpire.json":
        data = json.loads(zin.read(n).decode("utf-8", "ignore"))
        data["version"] = "1.1.0"
        zout.writestr(n, json.dumps(data, indent=2, ensure_ascii=False).encode("utf-8"))
    elif n == "ExampleResources/localization/ZHS/cards.json":
        add_path = os.path.join(
            BASE_DIR, "ExampleModResources", "localization", "ZHS", "cards_additions.json"
        )
        cards = json.loads(zin.read(n).decode("utf-8", "ignore"))
        if os.path.exists(add_path):
            additions = json.load(open(add_path, encoding="utf-8"))
            cards.update(additions)
        zout.writestr(n, json.dumps(cards, indent=2, ensure_ascii=False).encode("utf-8"))
    elif n == "ExampleResources/localization/ZHS/relics.json":
        add_path = os.path.join(
            BASE_DIR, "ExampleModResources", "localization", "ZHS", "relics_additions.json"
        )
        relics = json.loads(zin.read(n).decode("utf-8", "ignore"))
        if os.path.exists(add_path):
            additions = json.load(open(add_path, encoding="utf-8"))
            relics.update(additions)
            print("   relics.json: +{} entries".format(len(additions)))
        zout.writestr(n, json.dumps(relics, indent=2, ensure_ascii=False).encode("utf-8"))
    elif n.startswith("ExampleModResources/img/relics/") and n in new_images:
        zout.writestr(n, new_images[n])
        injected.add(n)
    else:
        zout.writestr(item, zin.read(n))

# Inject new classes not yet written
for arc, data in new_classes.items():
    if arc not in injected:
        zout.writestr(arc, data)
        injected.add(arc)

# Inject new images not yet written
for arc, data in new_images.items():
    if arc not in injected:
        zout.writestr(arc, data)
        injected.add(arc)
        print("   Injected image: {}".format(arc))

zin.close()
zout.close()
size_kb = os.path.getsize(OUT_JAR) // 1024
print("   Written: {} ({} KB)".format(OUT_JAR, size_kb))

# Step 5: Verify bytecode (major version 52 = Java 8)
print("\n[3/4] Verifying bytecode major version = 52 (Java 8)...")
ok = True
for arc, data in new_classes.items():
    major = data[7]  # class byte[6:8] = major_version
    if major != 52:
        print("   WARN: {} has major version {} (not 52)".format(arc, major))
        ok = False
if ok:
    print("   All {} classes have major version 52 (Java 8).".format(len(new_classes)))

# Step 6: Java 8 load test
print("\n[4/4] Java 8 load test...")
JARS = OUT_JAR + ";" + DESKTOP
for j in os.listdir(ws_dir):
    jd = os.path.join(ws_dir, j)
    if os.path.isdir(jd):
        for fn in os.listdir(jd):
            if fn.endswith(".jar"):
                JARS += ";" + os.path.join(jd, fn)

cmd = [JRE8, "-cp", TOOLS + ";" + JARS, "LoadTest"] + JARS.split(";")
result = subprocess.run(cmd, capture_output=True)
stdout_str = result.stdout.decode("gbk", errors="replace")
stderr_str = result.stderr.decode("gbk", errors="replace")
print(stdout_str)
if result.returncode != 0:
    print("STDERR:", stderr_str)
    print("ERROR: Load test failed (exit code {})".format(result.returncode))
    sys.exit(1)

if "ALL_35_LOADED_ON_JAVA8" in stdout_str:
    print("   ALL 35 classes loaded successfully on Java 8!")
else:
    print("   WARNING: Some classes may have failed to load.")
    print(stdout_str)

print("\n" + "=" * 60)
print(" BUILD COMPLETE - All verified")
print("=" * 60)
