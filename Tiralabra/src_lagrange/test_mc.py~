#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" DFT ilman atomiorbitaaleja. 
Tässä moduulissa kokeillaan metodien toimivuutta """

import numpy as np
from scipy.optimize import fmin_powell, fmin, anneal
from gridi import *
from energiat import E_tot

electron_density = Gridi(init_value=0.0)
V_hartree = Gridi(init_value=0)
nucleus_density = Gridi(init_value=0)
nucleus_density.gridi[2,2,0]=1/nucleus_density.get_volume_of_a_box()
electron_density.gridi[2,2,0]=-1/electron_density.get_volume_of_a_box()

print electron_density.gridi
print electron_density.to_1d_list()

#Number of electrons is constant (ntot)
ntot = np.sum(electron_density.gridi)*V_hartree.get_volume_of_a_box()
print "total charge of electrons", ntot

#add lagrange multiplier in the end of the electron density
lagrange = 1000.0
my_x0 = electron_density.to_1d_list()
my_x0.append(lagrange)
print len(my_x0)
xopt = fmin(func=E_tot, x0=my_x0, \
                args = (V_hartree, nucleus_density, ntot), \
                )

