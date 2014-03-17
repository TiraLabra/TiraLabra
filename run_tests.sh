#!/bin/bash

git submodule init
git submodule update

cmake .
make

ctest --output-on-failure
