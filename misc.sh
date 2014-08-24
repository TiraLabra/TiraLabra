#!/bin/bash
#-----------------------------------------------------------------------------
# File:          
# Creation Date:
# Last Modified: Aug 24 2014 [18:34:13]
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
                  --benchmark-option=--time-limit=29 \
   && rsync -a benchmark_results.html evo:public_html/TiraLabra/
