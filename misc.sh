#!/bin/bash
#-----------------------------------------------------------------------------
# File:          
# Creation Date:
# Last Modified: Aug 29 2014 [00:55:18]
# Created By: Samuli Thomasson [SimSaladin] samuli.thomassonAtpaivola.fi
#-----------------------------------------------------------------------------

cabal clean \
   && cabal configure --enable-tests --enable-library-coverage \
   && cabal test --show-details=streaming \
   && cabal haddock \
   && [[ $USER == sim ]] \
   && rsync -a dist/doc/html/TiraLabra/ evo:public_html/TiraLabra \
   && rsync -a dist/hpc/html/TiraLabra*/ evo:public_html/TiraLabra-hpc \
   && cabal clean \
   && cabal bench --benchmark-option=--output=benchmark_results.html \
   && cabal configure --enable-benchmarks -fbenchmarkBuildGWT \
   && cabal bench --benchmark-option=--output=pointless_benchmark_results.html \
                  --benchmark-option=--time-limit=29 \
   && rsync -a {pointless_,}benchmark_results.html evo:public_html/TiraLabra/
