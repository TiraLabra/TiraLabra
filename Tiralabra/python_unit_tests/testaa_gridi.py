#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" Testataan modulin gridi.py funktioita.


Density functional theory (DFT) without orbitals.
In this module we define a computational grids and some of its functions
"""
import sys, os
sys.path.insert(0, os.path.abspath("../src") )
import read_data

import numpy as np
import unittest

import gridi

class TestGridi(unittest.TestCase):
    """Testataan laskentagridiä esittävä luokka
    
    Parametrit:
    
    nx : int 
         number of grid points in x direction
    ny : int 
         number of grid points in y direction
    nz : int 
         number of grid points in z direction
     h : float
         grid spacing (in atomic units)
 init_value : float
         Alkuarvaus jossa sama arvo on jokaisessa gridipisteessä.
         Summa gridipisteiden arvoista kerrottuna simulaatio laatikon 
         tilavuudella antaa esim. elektronien lukumäärän.
 mylambda: float
         Penalty (rangaistus) funktion painokerroin. Mitä suurempi tämä on 
         sitä paremmin elektronien lukumäärä säilyy SD minimoinnissa.
 n_iter: montako MC tai SD iteraatiota korkeintaan tehdään.
    tol: float
         Kokonaisenergian konvergenssikriteeri
temperature: float
         Systeemin lämpötila (K), relevantti vain MC laskuissa.
tiheydenmuutos: float
         Tiheyden muutos funktionaaliderivaattaa otettaessa
d_rho  : float 
         Tiheyden muutos monte carlossa
   askel:  float
         Alkuaskel SD minimoinnissa.

    """

    def setUp(self):
        """ alustetaan testeihin liittyvät muuttujat """
        [mylambda, n_iter, tol, temperature,tiheydenmuutos, d_rho, askel,
         ny, nx, nz, elektroni_lkm, elektroni_tiheys,
         V_hartree, ydin_tiheys, ytimien_lkm, ydin_tiheys] = \
         read_data.read_data(filename = '../test/alkuarvot.txt_5x5x0')
        self.nx = nx
        self.ny = ny
        self.nz = nz
        self.h = 0.1
        self.askel = askel         
        self.mylambda = None
        self.n_iter = None
        self.tol = None
        self.temperature = None
        self.tiheydenmuutos = None
        self.d_rho = None
        self.askel = None
        init_value = 0.0
        
        self.summa = None
        
        #luodaan testimuuttuja
        self.testgrid = gridi.Gridi(nx=5,ny=5,nz=5,h=0.1,init_value=0.0)

    def test_mylamda(self):
        self.testgrid.set_mylambda(10)
        la = self.testgrid.get_mylambda()
        self.assertAlmostEqual(la, 10.0, places=4)

    def test_n_iter(self):
        self.testgrid.set_n_iter(10)
        n_iter = self.testgrid.get_n_iter()
        self.assertAlmostEqual(n_iter, 10.0, places=4)

    def test_tol(self):
        self.testgrid.set_tol(1e-3)
        tol = self.testgrid.get_tol()
        self.assertAlmostEqual(tol, 1e-3, places=4)

    def test_temperature(self):
        self.testgrid.set_temperature(999.9)
        temperature = self.testgrid.get_temperature()
        self.assertAlmostEqual(temperature, 999.9, places=4)

    def test_tiheydenmuutos(self):
        self.testgrid.set_tiheydenmuutos(1e-1)
        tiheydenmuutos = self.testgrid.get_tiheydenmuutos()
        self.assertAlmostEqual(tiheydenmuutos, 1e-1, places=4)

    def test_d_rho(self):
        self.testgrid.set_d_rho(1e-3)
        d_rho = self.testgrid.get_d_rho()
        self.assertAlmostEqual(d_rho, 1e-3, places=4)

    def test_askel(self):
        self.testgrid.set_askel(1e-3)
        askel = self.testgrid.get_askel()
        self.assertAlmostEqual(askel, 1e-3, places=4)

    def test_sisapisteiden_lkm(self):
        gridi = np.empty( (3,3,3) )
        self.testgrid.twodx = False 
        self.testgrid.twody = False 
        self.testgrid.twodz = False 
        self.testgrid.set_sisapisteet(gridi)
        lkm = self.testgrid.get_sisapisteiden_lkm()
        self.assertAlmostEqual(lkm, 27.0, places=4)

    def test_get_number_of_boxes(self):
        n = self.testgrid.get_number_of_boxes()
        self.assertAlmostEqual(n, 27.0, places=4)

    def test_get_grid_step(self):
        h = self.testgrid.get_grid_step()
        self.assertAlmostEqual(h, 0.1, places=4)



if __name__ == '__main__':
    unittest.main()
