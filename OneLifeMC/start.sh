#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"

echo "========================================" >> start.log
echo "Starting server from: $SCRIPT_DIR" >> start.log
echo "Time: $(date)" >> start.log

if [ -f "$SCRIPT_DIR/next-seed.txt" ]; then
    SEED=$(cat "$SCRIPT_DIR/next-seed.txt")
    echo "Found seed file with value: $SEED" >> start.log

    echo "Deleting old world folders to prevent chunk borders..." >> start.log
    rm -rf "$SCRIPT_DIR/world" "$SCRIPT_DIR/world_nether" "$SCRIPT_DIR/world_the_end"
    echo "Old world folders deleted." >> start.log

    if grep -q "^level-seed=" "$SCRIPT_DIR/server.properties"; then
        sed -i '' "s/^level-seed=.*/level-seed=$SEED/" "$SCRIPT_DIR/server.properties"
    else
        echo "level-seed=$SEED" >> "$SCRIPT_DIR/server.properties"
    fi

    if grep -q "^hardcore=" "$SCRIPT_DIR/server.properties"; then
        sed -i '' "s/^hardcore=.*/hardcore=true/" "$SCRIPT_DIR/server.properties"
    else
        echo "hardcore=true" >> "$SCRIPT_DIR/server.properties"
    fi

    if grep -q "^gamemode=" "$SCRIPT_DIR/server.properties"; then
        sed -i '' "s/^gamemode=.*/gamemode=survival/" "$SCRIPT_DIR/server.properties"
    else
        echo "gamemode=survival" >> "$SCRIPT_DIR/server.properties"
    fi

    if grep -q "^difficulty=" "$SCRIPT_DIR/server.properties"; then
        sed -i '' "s/^difficulty=.*/difficulty=hard/" "$SCRIPT_DIR/server.properties"
    else
        echo "difficulty=hard" >> "$SCRIPT_DIR/server.properties"
    fi

    echo "Current settings in server.properties:" >> start.log
    grep -E "level-seed=|hardcore=|gamemode=|difficulty=" "$SCRIPT_DIR/server.properties" >> start.log

    rm "$SCRIPT_DIR/next-seed.txt"
    echo "Applied seed: $SEED" >> start.log
else
    echo "No seed file found at $SCRIPT_DIR/next-seed.txt" >> start.log
fi

echo "Launching server..." >> start.log

echo "Creating world directories if they don't exist..." >> start.log
mkdir -p "$SCRIPT_DIR/world/playerdata"
mkdir -p "$SCRIPT_DIR/world/data"
mkdir -p "$SCRIPT_DIR/world_nether/DIM-1/data"
mkdir -p "$SCRIPT_DIR/world_the_end/DIM1/data"
echo "World directories verified." >> start.log

echo "Cleaning up old level data files..." >> start.log
rm -f "$SCRIPT_DIR/level.dat"
rm -f "$SCRIPT_DIR/level.dat_old"
rm -f "$SCRIPT_DIR/world/level.dat"
rm -f "$SCRIPT_DIR/world/level.dat_old"
rm -f "$SCRIPT_DIR/world_nether/level.dat"
rm -f "$SCRIPT_DIR/world_nether/level.dat_old"
rm -f "$SCRIPT_DIR/world_the_end/level.dat"
rm -f "$SCRIPT_DIR/world_the_end/level.dat_old"
echo "Level data files cleaned." >> start.log

sleep 1
java -Xms1G -Xmx6G -jar "$SCRIPT_DIR/paper-1.21.11-127.jar" --nogui
