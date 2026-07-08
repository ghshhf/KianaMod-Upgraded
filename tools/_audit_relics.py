"""Audit existing relic API patterns from kiana-mod.jar"""
import zipfile, re, json

jar = r"F:/steam/steamapps/workshop/content/646570/3559292072/kiana-mod.jar"
z = zipfile.ZipFile(jar)

names = z.namelist()
relics = sorted([n for n in names if n.startswith("kianamod/relics/") and n.endswith(".class")])
print(f"Existing relic classes ({len(relics)}):")
for r in relics:
    print(f"  {r}")

# Check ExampleMod for relic registration
d = z.read("kianamod/modcore/ExampleMod.class")
txt = d.decode('latin-1')
print("\nExampleMod relic references:")
for s in sorted(set(re.findall(r'[A-Za-z./]{6,}', txt))):
    if 'relic' in s.lower():
        print(f"  {s}")

# Check example relic for base class
for rel in relics[:3]:
    data = z.read(rel)
    txt2 = data.decode('latin-1')
    bases = [s for s in re.findall(r'[A-Za-z./]{6,}', txt2) if any(k in s.lower() for k in ['relic','customrelic','abstractrelic'])]
    print(f"\n{rel}:")
    for b in bases[:5]:
        print(f"  {b}")

# Check relics.json localization
for p in names:
    if 'relics.json' in p.lower():
        data = json.loads(z.read(p).decode('utf-8','ignore'))
        print(f"\n{p}: {len(data)} entries")
        for k, v in list(data.items())[:3]:
            print(f"  {k}: {v.get('NAME','?')}")
        break
