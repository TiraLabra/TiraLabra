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
import string
import read_data


#try:

[ny, nx, nz, elektroni_lkm, elektroni_tiheys,
 V_hartree, ydin_tiheys, ytimien_lkm, ydin_tiheys] = read_data.read_data(filename = 'alkuarvot.txt')

print elektroni_tiheys.get_volume()
print elektroni_tiheys.gridi
print elektroni_tiheys.to_1d_list()

print "ydintiheys",ydin_tiheys.gridi
#Elektronien määrä on vakio
elektroni_tiheys.set_summa()
ntot = np.sum(elektroni_tiheys.gridi)*V_hartree.get_volume_of_a_box()
print "elektronien kokonaisvaraus", ntot, elektroni_tiheys.get_summa_mennyt()

# otetaan tavoitteeksi että siirto hyväksytään joka viides kerta
# silloin on sopivasti riskiä yrityksessä
tiheydenmuutos = 0.1
outfile = open('energiat.txt', 'w')
for step in range(10):
    konvergoinut = laskentaa.minimoi_monte_carlolla(
        outfile, 
        elektroni_tiheys, V_hartree,
        ydin_tiheys,
        tiheydenmuutos,
        n_iter=100, tol=1e-4) 
    
    if konvergoinut:
        print "konvergoi"
        break


    ##piirtoa.plot3d(elektroni_tiheys)
    piirtoa.plot2d_simple(elektroni_tiheys)
outfile.close()


