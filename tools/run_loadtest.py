"""Run Java 8 load test for KianaMod classes."""
import os, subprocess, sys

BASE_DIR = r"E:\项目改变\KianaMod-m3"
TOOLS = os.path.join(BASE_DIR, "tools")
DIST = os.path.join(BASE_DIR, "dist")
JRE8 = r"F:\steam\steamapps\common\SlayTheSpire\jre\bin\java.exe"
DESKTOP = r"F:\steam\steamapps\common\SlayTheSpire\desktop-1.0.jar"
OUT_JAR = os.path.join(DIST, "kiana-mod-upgraded-v1.1.0.jar")

# Build classpath
CP = [OUT_JAR, DESKTOP]
ws_dir = r"F:\steam\steamapps\workshop\content\646570"
for j in os.listdir(ws_dir):
    jd = os.path.join(ws_dir, j)
    if os.path.isdir(jd):
        for fn in os.listdir(jd):
            if fn.endswith(".jar"):
                CP.append(os.path.join(jd, fn))

cp_str = ";".join(CP)
cmd = [JRE8, "-cp", TOOLS + ";" + cp_str, "LoadTest"] + CP

result = subprocess.run(cmd, capture_output=True)
stdout = result.stdout.decode("gbk", errors="replace")
stderr = result.stderr.decode("gbk", errors="replace")

print(stdout)
if stderr.strip():
    print("STDERR:", stderr)

if result.returncode != 0:
    print("ERROR: Load test failed (exit code {})".format(result.returncode))
    sys.exit(1)

if "ALL_23_LOADED_ON_JAVA8" in stdout:
    print("   ALL 23 classes loaded successfully on Java 8!")
else:
    print("   Some classes could not be verified.")
    sys.exit(1)
