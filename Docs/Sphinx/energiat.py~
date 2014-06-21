#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" Tässä moduulissa lasketaan gridiin liittyviä energiatermejä:
Elektronien kineettinen energia T(n(r))
Vaihtokorrelaatioenergia E_xc
Elektronien Coulombin energia.
Atomiytimien aiheuttama potentiaali V_ext*n(r)
"""
from sys import exit
from laskentaa import tee_gauss_seidel_yksi_askel

#from math import abs
def E_T(elektroni_tiheys):
    """ Lasketaan elektronikaasun kineettinen energia gridissä.
    """
    import numpy as np
    c = 2.871
    return abs(c*np.sum(elektroni_tiheys))

def E_vaihtokorrelaatio(elektroni_tiheys):
    """ Lasketaan elektronikaasun vaihtokorrelaatio energia gridissä.
    """
    import numpy as np
    c = 0.681420222312
    return c*np.sum(elektroni_tiheys**(4/3))

def E_elektroni_elektroni(elektroni_tiheys, V_hartree):
    """ Lasketaan elektronikaasun Coulombin energia gridissä.
    """
    import numpy as np
    c = 0.5
    return c*np.sum(elektroni_tiheys * V_hartree)

def E_elektroni_ydin(ydin_tiheys, V_hartree):
    """ Lasketaan ytien ja elektronien vuorovaikutusenergia
    """
    import numpy as np
    
    c = 1.0
    return c*np.sum(ydin_tiheys * V_hartree)


def get_V_hartree(V_hartree, elektroni_tiheys_3d, n_iter=10):
    """ lasketaan Hartree potentiaali tämänhetkiselle elektronitiheydelle"""
    for step in range(n_iter):
        tee_gauss_seidel_yksi_askel(V_hartree, elektroni_tiheys_3d)
    

def E_tot(elektroni_tiheys_flat, V_hartree, ydin_tiheys,ntot):
    """lasketaan elektronien kokonaisenergia 
    otetaan vakioelektronimäärä huomioon lagrangen kertoimen avulla"""
    import numpy as np
    elektroni_tiheys_3d = np.array(elektroni_tiheys_flat[:-1])
    lagrange = elektroni_tiheys_flat[-1]
    elektroni_tiheys_3d.resize(V_hartree.gridi.shape)
    
    # 100 iteraatiota jotta saadaan hartree potentiaali
    get_V_hartree(V_hartree, elektroni_tiheys_3d, 10)

    ETOT = E_T(elektroni_tiheys_3d)+E_vaihtokorrelaatio(elektroni_tiheys_3d) + \
        E_elektroni_elektroni(elektroni_tiheys_3d,V_hartree.gridi) + \
        E_elektroni_ydin(elektroni_tiheys_3d,ydin_tiheys.gridi)+ \
        lagrange*abs(np.sum(elektroni_tiheys_3d)*V_hartree.get_volume_of_a_box()-ntot)
    

 
    print "ETOT", ETOT, "NEL", np.sum(elektroni_tiheys_3d)*V_hartree.get_volume_of_a_box(), "L", lagrange
    return ETOT

    
