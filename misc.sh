#!/bin/bash
#-----------------------------------------------------------------------------
# File:          
# Creation Date:
# Last Modified: Aug 09 2014 [07:13:09]
# Created By: Samuli Thomasson [SimSaladin] samuli.thomassonAtpaivola.fi
#-----------------------------------------------------------------------------

cabal clean \
   && cabal configure --enable-benchmarks --enable-tests --enable-library-coverage \
   && cabal test --show-details=streaming \
   && cabal haddock \
   && [[ $USER == sim ]] \
   && rsync -a dist/doc/html/TiraLabra/ evo:public_html/TiraLabra \
   && rsync -a dist/hpc/html/TiraLabra*/ evo:public_html/TiraLabra-hpc \
   && cabal clean \
   && cabal bench --benchmark-option=--output=benchmark_results.html \
   && rsync -a benchmark_results.html evo:public_html/TiraLabra/
