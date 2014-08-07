#!/bin/bash
#-----------------------------------------------------------------------------
# File:          
# Creation Date:
# Last Modified: Aug 07 2014 [13:34:05]
# Created By: Samuli Thomasson [SimSaladin] samuli.thomassonAtpaivola.fi
#-----------------------------------------------------------------------------

cabal clean \
   && cabal configure --enable-tests --enable-library-coverage \
   && cabal build \
   && cabal test \
   && cabal haddock \
   && rsync -a dist/doc/html/TiraLabra/ evo:public_html/TiraLabra \
   && rsync -a dist/hpc/html/TiraLabra*/ evo:public_html/TiraLabra-hpc
