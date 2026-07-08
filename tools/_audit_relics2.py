"""Deep audit of one relic class for constructor pattern"""
import zipfile, re

jar = r"F:/steam/steamapps/workshop/content/646570/3559292072/kiana-mod.jar"
z = zipfile.ZipFile(jar)

# Check Celine relic - extract all ASCII strings >= 4 chars
for cls in ["kianamod/relics/Celine.class", "kianamod/relics/Acandleinthefiercewind.class"]:
    d = z.read(cls)
    strs = sorted(set(re.findall(rb'[ -~]{4,}', d)))
    interesting = [s.decode('ascii','ignore') for s in strs if any(k in s.decode('ascii','ignore').lower() for k in ['celine','relic','examplemod','relictier','commons','uncommon','boss','shop','special','starting','makeid','img','relics','id'])]
    print(f"\n=== {cls} ===")
    for s in interesting[:15]:
        print(f"  {s}")

# Check relics.json for IDs
import json
for p in z.namelist():
    if 'relics.json' in p.lower() and 'ZHS' in p:
        relics = json.loads(z.read(p).decode('utf-8','ignore'))
        for k, v in sorted(relics.items()):
            print(f"\n{k}: NAME={v.get('NAME','?')}, FLAVOR={v.get('FLAVOR') or v.get('FLAVORTEXT','?')[:60]}")
        break
