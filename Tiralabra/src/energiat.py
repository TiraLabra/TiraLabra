#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" Tässä moduulissa lasketaan gridiin liittyviä energiatermejä:
Elektronien kineettinen energia T(n(r))
Vaihtokorrelaatioenergia E_xc
Elektronien Coulombin energia.
Atomiytimien aiheuttama potentiaali V_ext*n(r)
"""
from sys import exit
#from math import abs
def E_T(tiheys):
    """ Lasketaan elektronikaasun kineettinen energia gridissä.
    """
    import numpy as np
    c = 2.871
    return abs(c*np.sum(tiheys))

def E_vaihtokorrelaatio(tiheys):
    """ Lasketaan elektronikaasun vaihtokorrelaatio energia gridissä.
    """
    import numpy as np
    c = 0.681420222312
    return c*np.sum(tiheys**(4/3))

def E_elektroni_elektroni(tiheys, V_hartree):
    """ Lasketaan elektronikaasun Coulombin energia gridissä.
    """
    import numpy as np
    
    c = 0.5
    return c*np.sum(tiheys * V_hartree)

def E_elektroni_ydin(tiheys, ytimet, dx, dy, dz):
    """ Lasketaan elektronikaasun Coulombin energia gridissä.
    xxx Tää ei oo kovin tehokasta koodia...
    """
    import numpy as np
    from math import sqrt
    imax = tiheys.shape[0]
    jmax = tiheys.shape[1]
    kmax = tiheys.shape[2]
    e_elektroni_ydin = 0.0
    c = 1
    #print "AAA",imax, jmax, kmax
    #exit()
    for ydin in ytimet:
        #print "ydin", ydin.get_x()
        for i in range(0, imax):
            for j in range(0, jmax):
                for k in range(0, kmax):
                    #print "ijk", i, j, k
                    dx = i*dx - ydin.get_x()
                    dy = j*dy - ydin.get_y()
                    dz = k*dz - ydin.get_z()
                    dist = sqrt(dx*dx+dy*dy+dz*dz)
                    e_elektroni_ydin = e_elektroni_ydin + \
                        tiheys[i, j, k] * ydin.get_varaus() / dist
    return c * e_elektroni_ydin


