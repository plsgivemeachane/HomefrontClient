#!/bin/bash
# Build and run HomeFrontClient on Linux.
#   ./startclient-linux.sh              compile, then launch
#   ./startclient-linux.sh --no-build   launch the last build
# Any other arguments are passed to Minecraft (e.g. --username Steve).
#
# Minecraft 1.8.9 uses LWJGL 2, which only works on Java 8.
# Point at a different Java 8 JDK with JAVA_HOME_8=/path/to/jdk8 if needed.
set -e
cd "$(dirname "$0")"
ROOT="$PWD"

JAVA_HOME="${JAVA_HOME_8:-/usr/lib/jvm/java-8-openjdk-amd64}"
if [ ! -x "$JAVA_HOME/bin/javac" ]; then
    echo "Java 8 JDK not found at $JAVA_HOME. Install it with: sudo apt install openjdk-8-jdk" >&2
    exit 1
fi

OUT="$ROOT/bin/minecraft"
NATIVES="$ROOT/jars/versions/1.8.9/1.8.9-natives-linux"
LIBS=$(find "$ROOT/jars/libraries" -name '*.jar' ! -name '*natives*' | sort | tr '\n' ':')

BUILD=1
if [ "$1" = "--no-build" ]; then BUILD=0; shift; fi

if [ $BUILD = 1 ]; then
    echo "Compiling..."
    mkdir -p "$OUT"
    find "$ROOT/src/minecraft" -name '*.java' > "$ROOT/temp/sources-linux.txt"
    "$JAVA_HOME/bin/javac" -nowarn -encoding UTF-8 -J-Xmx3g \
        -cp "$LIBS" -d "$OUT" @"$ROOT/temp/sources-linux.txt"
fi

# Compiled classes first, then the client's own assets in src/, then the
# libraries, then the vanilla jar (for vanilla textures and sounds).
CP="$OUT:$ROOT/src/minecraft:$LIBS$ROOT/jars/versions/1.8.9/1.8.9.jar"

cd "$ROOT/jars"
exec "$JAVA_HOME/bin/java" -Xmx2G \
    -Djava.library.path="$NATIVES" \
    -cp "$CP" Start "$@"
