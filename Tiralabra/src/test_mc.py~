#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" DFT ilman atomiorbitaaleja. 
Tässä moduulissa kokeillaan metodien toimivuutta """

import numpy as np
from scipy.optimize import fmin_powell, fmin, anneal
from gridi import *
from energiat import E_tot

elektroni_tiheys = Gridi(init_value=0.0)
V_hartree = Gridi(init_value=0)
ydin_tiheys = Gridi(init_value=0)
ydin_tiheys.gridi[2,2,0]=1/ydin_tiheys.get_volume_of_a_box()
elektroni_tiheys.gridi[2,2,0]=1/elektroni_tiheys.get_volume_of_a_box()

print elektroni_tiheys.gridi
print elektroni_tiheys.to_1d_list()

#Elektronien määrä on vakio
ntot = np.sum(elektroni_tiheys.gridi)*V_hartree.get_volume_of_a_box()
print "elektronien kokonaisvaraus", ntot

#add lagrange multiplier in the end of the elektroni tiheys
lagrange = 1000.0
my_x0 = elektroni_tiheys.to_1d_list()
my_x0.append(lagrange)
print len(my_x0)
xopt = fmin(func=E_tot, x0=my_x0, \
                args = (V_hartree, ydin_tiheys, ntot), \
                )

