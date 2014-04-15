#!/bin/bash

cmake .
make

ctest --output-on-failure
