#!/bin/bash

set -exu

BUILD_DIR=build

cd $(dirname $0)

sbt fullOptJS

if [[ -d $BUILD_DIR ]]; then
  rm -rf $BUILD_DIR
fi

mkdir $BUILD_DIR

cp target/scala-3.3.1/scala-gha-exercise-opt/* $BUILD_DIR/
cp -r node_modules $BUILD_DIR/

cd $BUILD_DIR

ncc build index.js
