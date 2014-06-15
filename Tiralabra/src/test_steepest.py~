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




# yritetään lukea alkuarvot filestä alkuarvot.txt
#try:
if True:
    infile=open('alkuarvot.txt','r')
    nx=int(infile.readline().strip())
    ny=int(infile.readline().strip())
    nz=int(infile.readline().strip())
    elektroni_lkm=float(infile.readline().strip())
    elektroni_tiheys = Gridi(nx=nx,ny=ny,nz=nz,init_value=elektroni_lkm)
    V_hartree = Gridi(nx=nx,ny=ny,nz=nz,init_value=0)
    ydin_tiheys = Gridi(nx=nx,ny=ny,nz=nz,init_value=0)
    ytimien_lkm=int(infile.readline().strip())
    for i_ydin in range(ytimien_lkm):
        line=infile.readline()
        col=string.split(line)
        ix=int(col[0])
        iy=int(col[1])
        iz=int(col[2])
        z =float(col[3])
        ydin_tiheys.gridi[ix,iy,iz]=1.0/ydin_tiheys.get_volume_of_a_box()


#except: # mennään oletusinputeilla
#    print "tiedoston luku tiedosta 'alkuarvot.txt' EI onnistunut"
#    print "käytetään oletusarvoja"
#    V_hartree = Gridi(init_value=0)
#    ydin_tiheys = Gridi(init_value=0)
#    elektroni_tiheys = Gridi(init_value=2.0)
#    ydin_tiheys.gridi[2,2,0]=1.0/ydin_tiheys.get_volume_of_a_box()
#    ydin_tiheys.gridi[12,3,0]=1.0/ydin_tiheys.get_volume_of_a_box()

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


