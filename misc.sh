#!/bin/bash
#-----------------------------------------------------------------------------
# File:          
# Creation Date:
# Last Modified: Aug 08 2014 [03:36:41]
# Created By: Samuli Thomasson [SimSaladin] samuli.thomassonAtpaivola.fi
#-----------------------------------------------------------------------------

cabal clean \
   && cabal configure --enable-tests --enable-library-coverage \
   && cabal build \
   && cabal test --show-details=streaming \
   && cabal haddock \
   && [[ $USER == sim ]] \
   && rsync -a dist/doc/html/TiraLabra/ evo:public_html/TiraLabra \
   && rsync -a dist/hpc/html/TiraLabra*/ evo:public_html/TiraLabra-hpc
