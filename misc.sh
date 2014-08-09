#!/bin/bash
#-----------------------------------------------------------------------------
# File:          
# Creation Date:
# Last Modified: Aug 09 2014 [05:34:09]
# Created By: Samuli Thomasson [SimSaladin] samuli.thomassonAtpaivola.fi
#-----------------------------------------------------------------------------

cabal clean \
   && cabal configure --enable-benchmarks --enable-tests --enable-library-coverage \
   && cabal build \
   && cabal test --show-details=streaming \
   && cabal haddock \
   && cabal bench --benchmark-option=--output=benchmark_results.html \
   && [[ $USER == sim ]] \
   && rsync -a dist/doc/html/TiraLabra/ evo:public_html/TiraLabra \
   && rsync -a benchmark_results.html evo:public_html/TiraLabra/ \
   && rsync -a dist/hpc/html/TiraLabra*/ evo:public_html/TiraLabra-hpc
