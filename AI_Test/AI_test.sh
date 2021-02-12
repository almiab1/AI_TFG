#!/bin/bash

# Magarena Test

# ---------------------------------------------------------------------------
# Set up
# ---------------------------------------------------------------------------
magarena_dir='/mnt/d/0-Varios/Universidad/TFG/Framework/Magarena/Magarena-1.96/'
cd $magarena_dir
# ---------------------------------------------------------------------------

# ---------------------------------------------------------------------------
# Test Configurations
# ---------------------------------------------------------------------------
games=1
duels=1
threads=4
lifes=1
# ---------------------------------------------------------------------------

# ---------------------------------------------------------------------------
# Test
# ---------------------------------------------------------------------------
testAI(){
    java -splash: -Xms512M -Xmx1024M -jar Magarena.jar --headless  \
        --ai1 MCTS --str1 8 --deck1 @ \
        --ai2 VEGAS --str2 8 --deck2 @ \
        --life $lifes --games $games --duels $duels --threads $threads
}
# ---------------------------------------------------------------------------

# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------
testAI