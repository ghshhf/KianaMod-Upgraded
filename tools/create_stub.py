#!/usr/bin/env python3
"""
Generate a minimal stub jar for desktop-1.0.jar (Slay the Spire game jar).

Scans src/ for all imports from 'com.megacrit.cardcrawl', finds the actual
.class files in a reference desktop-1.0.jar (if available), or creates
minimal stubs for compilation-only purposes.

In CI, this creates a minimal stub jar with just the types/methods needed
for compilation verification. The stub has no method bodies — just signatures.
"""

import os
import re
import zipfile
import shutil
import tempfile
import subprocess
import sys

BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC_DIR = os.path.join(BASE_DIR, "src")
OUTPUT_JAR = os.path.join(BASE_DIR, "lib", "desktop-stub-1.0.jar")
REFERENCE_JAR = os.path.join(BASE_DIR, "lib", "desktop-1.0.jar")


def find_imports():
    """Find all imports from com.megacrit.cardcrawl in source files."""
    imports = set()
    pattern = re.compile(r'^import\s+(com\.megacrit\.cardcrawl\.[\w.]+);\s*$', re.MULTILINE)
    
    for root, _dirs, files in os.walk(SRC_DIR):
        for fn in files:
            if fn.endswith(".java"):
                path = os.path.join(root, fn)
                with open(path, "r", encoding="utf-8") as f:
                    content = f.read()
                for m in pattern.finditer(content):
                    imports.add(m.group(1))
    
    return sorted(imports)


def extract_from_reference(imports, ref_jar):
    """Extract needed .class files from reference jar."""
    if not os.path.exists(ref_jar):
        print("Reference desktop-1.0.jar not found. Stubs will be generated.")
        return None
    
    temp_dir = tempfile.mkdtemp()
    
    # Build mapping from type name to class file path in jar
    needed_types = set()
    for imp in imports:
        parts = imp.split(".")
        class_name = parts[-1]
        package = ".".join(parts[:-1])
        needed_types.add((package, class_name))
    
    with zipfile.ZipFile(ref_jar, "r") as zf:
        extracted = 0
        for entry in zf.infolist():
            if not entry.filename.endswith(".class"):
                continue
            # Build full class name from path
            path_parts = entry.filename.replace("\\", "/").split("/")
            class_name = path_parts[-1].replace(".class", "")
            # Handle inner classes
            if "$" in class_name:
                outer = class_name.split("$")[0]
            else:
                outer = class_name
            package = ".".join(path_parts[:-1]).replace("/", ".")
            
            for needed_pkg, needed_cls in needed_types:
                if package == needed_pkg and outer == needed_cls:
                    zf.extract(entry, temp_dir)
                    extracted += 1
                    break
    
    print(f"Extracted {extracted} class files from reference jar")
    if extracted > 0:
        # Create output jar
        os.makedirs(os.path.dirname(OUTPUT_JAR), exist_ok=True)
        with zipfile.ZipFile(OUTPUT_JAR, "w", zipfile.ZIP_DEFLATED) as zout:
            for root, _dirs, files in os.walk(temp_dir):
                for fn in files:
                    fp = os.path.join(root, fn)
                    arc = os.path.relpath(fp, temp_dir).replace("\\", "/")
                    zout.write(fp, arc)
        
        shutil.rmtree(temp_dir)
        size_kb = os.path.getsize(OUTPUT_JAR) // 1024
        print(f"Created stub jar: {OUTPUT_JAR} ({size_kb} KB)")
        return True
    
    shutil.rmtree(temp_dir)
    return None


def generate_stubs(imports):
    """Generate minimal Java stub sources from import list."""
    stubs = {}
    
    # Collect imports by class
    class_imports = {}
    for imp in imports:
        parts = imp.split(".")
        cls_name = parts[-1]
        pkg = ".".join(parts[:-1])
        key = f"{pkg}.{cls_name}"
        if key not in class_imports:
            class_imports[key] = []
        class_imports[key].append(imp)
    
    print(f"Found {len(class_imports)} unique StS type references")
    
    # Group by package
    package_classes = {}
    for key in class_imports:
        pkg, cls = key.rsplit(".", 1)
        package_classes.setdefault(pkg, []).append(cls)
    
    temp_dir = tempfile.mkdtemp()
    
    for pkg, classes in sorted(package_classes.items()):
        pkg_dir = os.path.join(temp_dir, "src", *pkg.split("."))
        os.makedirs(pkg_dir, exist_ok=True)
        
        for cls in sorted(classes):
            # Create a minimal class stub
            stub_path = os.path.join(pkg_dir, f"{cls}.java")
            inner_class = "$" in cls
            
            if not inner_class:
                lines = []
                lines.append(f"package {pkg};")
                lines.append("")
                if "Abstract" in cls:
                    lines.append(f"public abstract class {cls} {{")
                else:
                    lines.append(f"public class {cls} {{")
                
                # Add a minimal set of common methods used by mods
                for method in [
                    "public int cost;",
                    "public int baseDamage;",
                    "public int baseBlock;",
                    "public int baseMagicNumber;",
                    "public int magicNumber;",
                    "public int damage;",
                    "public int block;",
                    "public String name;",
                    "public String id;",
                    "public int rarity;",
                    "public int type;",
                    "public int target;",
                ]:
                    lines.append(f"    {method}")
                
                lines.append("}")
                with open(stub_path, "w") as f:
                    f.write("\n".join(lines) + "\n")
    
    print(f"Generated stub sources in {temp_dir}/src")
    return temp_dir


def compile_stubs(temp_dir):
    """Compile stub Java sources into class files and package as jar."""
    src_dir = os.path.join(temp_dir, "src")
    out_dir = os.path.join(temp_dir, "out")
    os.makedirs(out_dir, exist_ok=True)
    
    # Find all .java files
    src_files = []
    for root, _dirs, files in os.walk(src_dir):
        for fn in files:
            if fn.endswith(".java"):
                src_files.append(os.path.join(root, fn))
    
    if not src_files:
        print("No stub sources to compile")
        return False
    
    # Compile with --release 8
    cmd = [
        "javac", "--release", "8",
        "-d", out_dir,
        "-proc:none",
    ] + src_files
    
    result = subprocess.run(cmd, capture_output=True, text=True)
    if result.returncode != 0:
        print(f"Stub compilation failed:\n{result.stderr}")
        return False
    
    print(f"Compiled {len(src_files)} stub source files")
    
    # Package as jar
    os.makedirs(os.path.dirname(OUTPUT_JAR), exist_ok=True)
    with zipfile.ZipFile(OUTPUT_JAR, "w", zipfile.ZIP_DEFLATED) as zf:
        for root, _dirs, files in os.walk(out_dir):
            for fn in files:
                fp = os.path.join(root, fn)
                arc = os.path.relpath(fp, out_dir).replace("\\", "/")
                zf.write(fp, arc)
    
    size_kb = os.path.getsize(OUTPUT_JAR) // 1024
    print(f"Created stub jar: {OUTPUT_JAR} ({size_kb} KB)")
    return True


def clean_up(temp_dir):
    shutil.rmtree(temp_dir)


if __name__ == "__main__":
    print("=== KianaMod StS Stub Generator ===")
    imports = find_imports()
    print(f"Found {len(imports)} StS imports in source files")
    
    # First try extracting from reference jar
    result = extract_from_reference(imports, REFERENCE_JAR)
    if result:
        print("Done using reference jar extraction.")
        sys.exit(0)
    
    # Fall back to generating stubs
    print("Generating stubs from imports...")
    temp_dir = generate_stubs(imports)
    result = compile_stubs(temp_dir)
    clean_up(temp_dir)
    
    if result:
        print("Done - stub jar created successfully.")
    else:
        print("FAILED to create stub jar.")
        sys.exit(1)
