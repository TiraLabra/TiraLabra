#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" Verrataan monte carlo (MC) ja steepest descent (SD)
minimointien tuloksia ja nopeuksia"""
import numpy as np
from scipy.optimize import fmin_powell, fmin, anneal
from gridi import *
import energiat
import laskentaa
import laskentaa_sd
import piirtoa
import string
import read_data
import time

# MC ajot
piirra= True
do_mc = False
do_sd = True
filenames = ['alkuarvot.txt_5x5x0', 'alkuarvot.txt_10x10x0',
             'alkuarvot.txt_16x16x0']
#filenames = ['alkuarvot.txt_10x10x0',
#             'alkuarvot.txt_16x16x0']
#filenames = ['alkuarvot.txt_10x10x0']
#filenames = ['alkuarvot.txt_16x16x0']
if do_mc:
    for filename in filenames:
        print "MC", filename
        #Elektronien määrä on vakio

        [ny, nx, nz, elektroni_lkm, elektroni_tiheys,
         V_hartree, ydin_tiheys, ytimien_lkm, ydin_tiheys] = \
         read_data.read_data(filename = filename)

        elektroni_tiheys.set_summa()

        tiheydenmuutos = 0.1
        outfile = open('mc_'+filename+'energiat.txt', 'w')
        start = time.time()
        konvergoinut = laskentaa.minimoi_monte_carlolla(
            outfile, 
            elektroni_tiheys, V_hartree,
            ydin_tiheys,
            tiheydenmuutos,
            n_iter=10000, tol=1e-4) 
        if konvergoinut:
            print "konvergoi"
            end = time.time()
            outfile.write("#time elapsed"+str(end-start))
        outfile.close()


        ##piirtoa.plot3d(elektroni_tiheys)
        if piirra:
            piirtoa.plot2d_simple(elektroni_tiheys)
        print filename+"MC: ETOT", energiat.E_tot(
            elektroni_tiheys, V_hartree, ydin_tiheys)      

if do_sd:
    for filename in filenames:
        print "MD", filename
        [ny, nx, nz, elektroni_lkm, elektroni_tiheys,
         V_hartree, ydin_tiheys, ytimien_lkm, ydin_tiheys] = \
         read_data.read_data(filename = filename)

        #Elektronien määrä on vakio
        elektroni_tiheys.set_summa()

        tiheydenmuutos = 1e-3
        outfile = open('md_'+filename+'energiat.txt', 'w')
        start = time.time()
        konvergoinut = laskentaa_sd.minimoi_steepes_descentilla(
            10000.0, 0.01, outfile, 
            elektroni_tiheys, V_hartree,
            ydin_tiheys,
            tiheydenmuutos,
            n_iter=10000, tol=1e-4) 

        ##piirtoa.plot3d(elektroni_tiheys)
        print "piirra", piirra
        if piirra:
            print "piirretaan"
            piirtoa.plot2d_simple(elektroni_tiheys)            
        if konvergoinut:
            print "konvergoiii"
            end = time.time()
            outfile.write("#time elapsed (s) "+str(end-start))
        outfile.close()
        print filename+"MD: ETOT", energiat.E_tot(
            elektroni_tiheys, V_hartree, ydin_tiheys)      



