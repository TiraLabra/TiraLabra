#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" Density functional theory (DFT) without orbitals.
In this module we define a computational grids and some of its functions
"""

import numpy as np
class Gridi(object):
    """Laskentagridiä esittävä luokka
    
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
    """

    def __init__(self, nx=20, ny=20, nz=0, h=0.1, init_value=0.0):
        self.nx = nx
        self.ny = ny
        self.nz = nz
        self.h = h         
        self.summa = None
        self.twodx = False; self.twody = False; self.twodz = False
        if (nz == 0): #twodz
            self.twodz = True
            self.gridi = np.empty( (nx,ny,1) )
            self.gridi[:, :, :] = 0.0 # zero boundary condition
            self.gridi[1:nx-1, 1:ny-1, 0] = init_value/self.get_volume()
        elif (ny == 0): #twody
            self.twody = True
            self.gridi = np.empty( (nx,1,nz) )
            self.gridi[:, :, :] = 0.0 # zero boundary condition
            self.gridi[1:nx-1, 0, 1:nz-1] = init_value/self.get_volume()
        elif (nx == 0): #twodx
            self.twodx = True
            self.gridi = np.empty( (1,ny,nz) )
            self.gridi[:, :, :] = 0.0 # zero boundary condition
            self.gridi[0, 1:ny-1, 1:nz-1] = init_value/self.get_volume()
        else: #3d
            self.gridi = np.empty( (nx,ny,nz) )
            self.gridi[:, :, :] = 0.0 # zero boundary condition
            self.gridi[1:nx-1, 1:ny-1, 1:nz-1] = init_value/self.get_volume()


        #print "grid", self.gridi.shape
        #print self.get_number_of_boxes()

    def to_1d_list(self):
        """ tehdään 3d gridistä 1d lista """
        return self.gridi.ravel().tolist()

    def get_sisapisteet(self):
        """ otetaan gridi jossa on vain sisäpisteet """
        if self.twodz:
            return self.gridi[1:self.nx-1, 1:self.ny-1, 0]
        elif self.twody:
            return self.gridi[1:self.nx-1, 0, 1:self.nz-1]
        elif self.twodx:
            return self.gridi[0, 1:self.ny-1, 1:self.nz-1]
        else:
            return self.gridi[1:self.nx-1, 1:self.ny-1, 1:self.nz-1]

    def set_sisapisteet(self, gridi):
        """ asetetaan gridin sisäpisteiden arvot """
        if self.twodz:
            self.gridi[1:self.nx-1, 1:self.ny-1, 0] = gridi
        elif self.twody:
            self.gridi[1:self.nx-1, 0, 1:self.nz-1] = gridi
        elif self.twodx:
            self.gridi[0, 1:self.ny-1, 1:self.nz-1] = gridi
        else:
            self.gridi[1:self.nx-1, 1:self.ny-1, 1:self.nz-1] = gridi


    def get_sisapisteiden_lkm(self):
        """ lasketaan montako sisäpistettä gridissä on
        esim 5x5x5 gridissä on 3x3x3 sisäpistettä
        """
        import sys
        kaikki_pisteet = list(self.gridi.shape)
        #print "kaikki_pisteet", kaikki_pisteet
        #sys.exit()
        sisapisteet = kaikki_pisteet
        if sisapisteet[0] >=3:
            sisapisteet[0] = sisapisteet[0] - 2
        if sisapisteet[1] >=3:
            sisapisteet[1] = sisapisteet[1] - 2
        if sisapisteet[2] >=3:
            sisapisteet[2] = sisapisteet[2] - 2
        return sisapisteet[0]*sisapisteet[1]*sisapisteet[2]
    
    def get_number_of_boxes(self):
        """ lasketaan montako laatikkoa laskenta-alueessa on """
        if self.twodz:
            return (self.nx-2)*(self.ny-2)
        elif self.twody:
            return (self.nx-2)*(self.nz-2)
        elif self.twodx:
            return (self.ny-2)*(self.nz-2)
        else:
            return (self.nx-2)*(self.ny-2)*(self.nz-2)

    def get_volume_of_a_box(self):
        """ laskentaan yhden laskentakuution tilavuus """
        if any([self.twodx, self.twody,self.twodz]):
            return self.h * self.h
        else:
            return self.h * self.h * self.h

    def get_volume(self):
        """ lasketaan laskenta-alueen tilavuus """
        if any([self.twodx, self.twody,self.twodz]):
            return self.h * self.h * self.get_number_of_boxes()
        else:
            return self.h * self.h * self.h * self.get_number_of_boxes()

    def get_grid_step(self):
        """ saadaan gridiväli atomiyksiköissä """
        return self.h

    def get_number_of_electrons(self):
        """ otetaan kokonaiselektronimäärä """
        return np.sum(self.gridi)*self.get_volume_of_a_box()

    def get_xy(self, i=0):
        """ otetaan i:s xy taso 3d matriisista esim. piirtoa varten """
        return self.gridi[:,:,i]

    def set_summa(self):
        """ asetetaan elektronien lukumäärä """
        self.summa = np.sum(self.gridi)*self.get_volume_of_a_box()

    def get_summa_mennyt(self):
        """otetaan aiemmin laskettu elektronien lukumäärä"""
        return self.summa

    def get_summa_nykyinen(self):
        """otetaan elektronien nykyinen lukumäärä"""
        return np.sum(self.gridi)*self.get_volume_of_a_box()

    def minx(self):
        """ gridin minimi x arvo reaaliavaruudessa atomiyksiköissä """
        return 0.0

    def miny(self):
        """ gridin minimi y arvo reaaliavaruudessa atomiyksiköissä """
        return 0.0

    def minz(self):
        """ gridin minimi z arvo reaaliavaruudessa atomiyksiköissä """
        return 0.0
    
    def maxx(self):
        """ gridin maksimi x arvo reaaliavaruudessa atomiyksiköissä """
        return (self.nx-1) * self.h

    def maxy(self):
        """ gridin maksimi y arvo reaaliavaruudessa atomiyksiköissä """
        return (self.ny-1) * self.h

    def maxz(self):
        """ gridin maksimi z arvo reaaliavaruudessa atomiyksiköissä """
        return (self.nz-1) * self.h

