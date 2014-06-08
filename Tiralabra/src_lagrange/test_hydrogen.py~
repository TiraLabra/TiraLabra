#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" DFT ilman atomiorbitaaleja. 
Tässä moduulissa kokeillaan metodien toimivuutta """

from alustus import tee_gridi, aseta_gridin_alkuarvo
from energiat import E_T, E_vaihtokorrelaatio, E_elektroni_elektroni, \
    E_elektroni_ydin, E_tot
from ydin import Ydin
from piirtoa import plot2d
import numpy as np
from scipy.optimize import fmin_powell, fmin, anneal

nx = 5
ny = 5
nz = 1
dx = 0.2
dy = 0.2
dz = 0.2
V_hartree_init = tee_gridi(nx,ny,nz)
elektroni_tiheys_init = tee_gridi(nx,ny,nz)
ydin_tiheys_init = tee_gridi(nx,ny,nz)
tasa_tiheys = 1/(nx*ny*nz*dx*dy*dz)

V_hartree = aseta_gridin_alkuarvo(0, V_hartree_init)
elektroni_tiheys = aseta_gridin_alkuarvo(-0.1, elektroni_tiheys_init)
ydin_tiheys = aseta_gridin_alkuarvo(0, ydin_tiheys_init)
ydin_tiheys[1,1,0] = 1
ydin_tiheys[3,3,0] = 1

elektroni_tiheys_flat = elektroni_tiheys.ravel().tolist()
xopt = fmin(func=E_tot, x0=elektroni_tiheys_flat, \
               args = (V_hartree, ydin_tiheys, dx, dy, dz), \

V_hartree_xy = np.sum(V_hartree, axis=2)
print V_hartree_xy.shape
print V_hartree_xy
xopt.resize(V_hartree_xy.shape)
print xopt.shape
print xopt
#plot2d(V_hartree_xy, dx, dy, nx, ny)
plot2d(xopt, dx, dy, nx, ny)
#print "elektroneja oli ", np.sum(elektroni_tiheys_flat,axis=2)*dx*dy*dz
