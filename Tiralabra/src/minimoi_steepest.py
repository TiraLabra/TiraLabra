#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" DFT ilman atomiorbitaaleja. 
Tässä moduulissa kokeillaan steepest descent energian minimointia.
elektronitiheys on positiivinen luku
"""

import numpy as np
from scipy.optimize import fmin_powell, fmin, anneal
from gridi import *
import energiat
import laskentaa_sd
import piirtoa
import string
import read_data

[ny, nx, nz, elektroni_lkm, elektroni_tiheys,
 V_hartree, ydin_tiheys, ytimien_lkm, ydin_tiheys] = \
read_data.read_data(filename = 'alkuarvot.txt')

#Elektronien määrä on vakio, talletetaan tämä vakio
elektroni_tiheys.set_summa()

print "Energia penaltyn kanssa", energiat.E_tot_with_penalty(
    elektroni_tiheys, V_hartree, ydin_tiheys, mylambda=0.1)

askel = 0.01
for step1 in range(10):
    for step2 in range(10):

        [energia, uusi_tiheys, uusi_askel] = laskentaa_sd.tee_yksi_sd_askel(
            elektroni_tiheys, V_hartree,
            ydin_tiheys,
            askel, tiheyden_muutos = 0.01,
            tol=1e-6)
        print "energia", energia, "NEL", uusi_tiheys.get_summa_nykyinen()
        elektroni_tiheys = uusi_tiheys
        askel = uusi_askel
        print "uusi_askel", uusi_askel
    piirtoa.plot2d_simple(elektroni_tiheys)
