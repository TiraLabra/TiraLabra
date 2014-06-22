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
piirra= False
do_mc = True
do_sd = False
filenames = ['alkuarvot.txt_5x5x0', 'alkuarvot.txt_10x10x0',
             'alkuarvot.txt_16x16x0']

#filenames = ['alkuarvot.txt_10x10x0',
#             'alkuarvot.txt_16x16x0']
#filenames = ['alkuarvot.txt_5x5x0']
#filenames = ['alkuarvot.txt_10x10x0']
#filenames = ['alkuarvot.txt_16x16x0']
if do_mc:
    for filename in filenames:
        #Elektronien määrä on vakio
        [mylambda, n_iter, tol, temperature,tiheydenmuutos, d_rho, askel,
         ny, nx, nz, elektroni_lkm, elektroni_tiheys,
         V_hartree, ydin_tiheys, ytimien_lkm, ydin_tiheys] = \
         read_data.read_data(filename = filename)

        elektroni_tiheys.set_summa()
        elektroni_tiheys.set_mylambda(mylambda)
        elektroni_tiheys.set_n_iter(n_iter)
        elektroni_tiheys.set_tol(tol)
        elektroni_tiheys.set_temperature(temperature)
        elektroni_tiheys.set_tiheydenmuutos(tiheydenmuutos)
        elektroni_tiheys.set_d_rho(d_rho)
        elektroni_tiheys.set_askel(askel)

        outfile = open('mc_'+filename+'energiat.txt', 'w')
        start = time.time()
        konvergoinut = \
            laskentaa.minimoi_monte_carlolla(
            outfile, 
            elektroni_tiheys, V_hartree,
            ydin_tiheys)
        if konvergoinut:
            print "konvergoi"
            print "elektronimäärä", elektroni_tiheys.get_number_of_electrons()
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

        [mylambda, n_iter, tol, temperature,tiheydenmuutos,d_rho, askel,
         ny, nx, nz, elektroni_lkm, elektroni_tiheys,
         V_hartree, ydin_tiheys, ytimien_lkm, ydin_tiheys] = \
         read_data.read_data(filename = filename)

        elektroni_tiheys.set_summa()
        elektroni_tiheys.set_mylambda(mylambda)
        elektroni_tiheys.set_n_iter(n_iter)
        elektroni_tiheys.set_tol(tol)
        elektroni_tiheys.set_temperature(temperature)
        elektroni_tiheys.set_tiheydenmuutos(tiheydenmuutos)
        elektroni_tiheys.set_d_rho(d_rho)
        elektroni_tiheys.set_askel(askel)

        outfile = open('md_'+filename+'energiat.txt', 'w')
        start = time.time()
        [konvergoinut, uusi_elektroni_tiheys] = \
            laskentaa_sd.minimoi_steepest_descentilla(
            outfile, 
            elektroni_tiheys, V_hartree,
            ydin_tiheys)

        ##piirtoa.plot3d(elektroni_tiheys)
        if piirra:
            piirtoa.plot2d_simple(uusi_elektroni_tiheys)            
        if konvergoinut:
            print "SD konvergoi"
            end = time.time()
            outfile.write("#time elapsed (s) "+str(end-start))
        outfile.close()
        print filename+"SD: ETOT", energiat.E_tot(
            uusi_elektroni_tiheys, V_hartree, ydin_tiheys)      



