#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" DFT ilman atomiorbitaaleja. 
Tässä moduulissa kokeillaan metodien toimivuutta 
elektronitiheys on positiivinen luku
"""

import numpy as np
from scipy.optimize import fmin_powell, fmin, anneal
from gridi import *
from energiat import E_tot
import laskentaa
import piirtoa

elektroni_tiheys = Gridi(init_value=2.0)
V_hartree = Gridi(init_value=0)
ydin_tiheys = Gridi(init_value=0)
ydin_tiheys.gridi[2,2,0]=1.0/ydin_tiheys.get_volume_of_a_box()
ydin_tiheys.gridi[12,3,0]=1.0/ydin_tiheys.get_volume_of_a_box()

#elektroni_tiheys.gridi[:,:,:]=1.0/elektroni_tiheys.get_volume()
print elektroni_tiheys.get_volume()
print elektroni_tiheys.gridi
print elektroni_tiheys.to_1d_list()

print "ydintiheys",ydin_tiheys.gridi
#Elektronien määrä on vakio
elektroni_tiheys.set_summa()
ntot = np.sum(elektroni_tiheys.gridi)*V_hartree.get_volume_of_a_box()
print "elektronien kokonaisvaraus", ntot, elektroni_tiheys.get_summa_mennyt()
for step in range(10):
    for step2 in range(100):
        laskentaa.monte_carlo_yksi_askel_kaikki(elektroni_tiheys, V_hartree,
                                                ydin_tiheys,
                                                0.01*np.average(elektroni_tiheys.gridi))

    piirtoa.plot2d_simple(elektroni_tiheys)


