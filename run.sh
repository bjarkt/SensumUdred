#!/usr/bin/env bash

dir=${PWD##*/}
if [[ $dir != "SensumUdred" ]]; then
    echo "ERROR: Must be in root of project, SensumUdred directory"
    exit 1
fi

rm -rf out/yee

echo "Compiler..."
./scripts/unix/compile.sh

echo "Starter program..."
./scripts/unix/launch.sh