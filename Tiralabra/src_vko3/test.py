#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" DFT ilman atomiorbitaaleja. 
Tässä moduulissa kokeillaan metodien toimivuutta """

from alustus import tee_gridi, aseta_gridin_alkuarvo
from energiat import E_T, E_vaihtokorrelaatio, E_elektroni_elektroni, \
    E_elektroni_ydin
from laskentaa import tee_gauss_seidel_yksi_askel
from ydin import Ydin
from piirtoa import plot2d

#atomiytimet
ytimet = []
ytimet.append(Ydin((0.15,0.15,0.15), +1))
ytimet.append(Ydin((0.05,0.05,0.05), +2))

nx = 4
ny = 4
nz = 1
dx = 0.1
dy = 0.1
dz = 0.1
V_hartree = tee_gridi(nx,ny,nz)
#print "V_hartree", V_hartree
elektroni_tiheys = tee_gridi(nx,ny,nz)
elektroni_tiheys[1,1,0] = -1
elektroni_tiheys[2,2,0] = -1
#elektroni_tiheys[3,0,1] = -3

aseta_gridin_alkuarvo(0, V_hartree)
#print "elektroni_tiheys", elektroni_tiheys
#print "V_hartree", V_hartree
#sys.exit()
for step in range(10):
    tee_gauss_seidel_yksi_askel(V_hartree, elektroni_tiheys, dx, dy, dz)
    #print V_hartree

print "kineettinen energia", E_T(elektroni_tiheys)
print "vaihtokorrelaatio energia", E_vaihtokorrelaatio(elektroni_tiheys)
print "elektroni_elektroni energia", \
    E_elektroni_elektroni(elektroni_tiheys,V_hartree)
print "elektroni_ydin energia", \
    E_elektroni_ydin(elektroni_tiheys,ytimet,dx,dy,dz)

plot2d(V_hartree, dx, dy, nx, ny)

