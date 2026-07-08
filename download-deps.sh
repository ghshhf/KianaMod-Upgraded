#!/bin/bash
# Download Slay the Spire modding dependencies for CI builds.
set -e

mkdir -p lib

# Download BaseMod from GitHub releases
echo "=== Downloading BaseMod ==="
curl -sL -o lib/BaseMod.jar \
  "https://github.com/daviscook477/BaseMod/releases/download/v5.5.0/BaseMod.jar"

# Download and extract ModTheSpire
echo "=== Downloading ModTheSpire ==="
curl -sL -o /tmp/mts.zip \
  "https://github.com/kiooeht/ModTheSpire/releases/download/v3.6.3/ModTheSpire.zip"
unzip -q -o /tmp/mts.zip ModTheSpire.jar -d lib/
rm -f /tmp/mts.zip

# Generate stub jar for desktop-1.0.jar (Slay the Spire game jar)
echo "=== Generating desktop stub ==="
if [ -f lib/desktop-1.0.jar ]; then
  echo "  Using existing desktop-1.0.jar from lib/"
else
  python3 tools/create_stub.py
fi

echo ""
echo "=== Dependencies ready ==="
ls -lh lib/
