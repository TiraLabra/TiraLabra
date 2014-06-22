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
    #print c*np.sum(np.power(elektroni_tiheys.gridi,1.66666666))
    #print elektroni_tiheys.gridi
    return c*np.sum(np.power(elektroni_tiheys.gridi,(5./3.)))

def E_vaihtokorrelaatio(elektroni_tiheys):
    """ Lasketaan elektronikaasun vaihtokorrelaatio energia gridissä.
    """
    import numpy as np
    c = -0.681420222312
    return c*np.sum(np.power(elektroni_tiheys.gridi,(4./3.)))

def E_elektroni_elektroni(elektroni_tiheys, V_hartree):
    """ Lasketaan elektronikaasun Coulombin energia gridissä.
    """
    import numpy as np
    c = 0.5
    return c*np.sum(elektroni_tiheys.gridi * V_hartree.gridi)

def E_elektroni_ydin(ydin_tiheys, V_hartree):
    """ Lasketaan ytien ja elektronien vuorovaikutusenergia
    """
    import numpy as np
    
    c = -1.0
    #print "YTT",ydin_tiheys.to_1d_list()
    #print "HT",V_hartree.to_1d_list()
    #print ydin_tiheys.gridi * V_hartree.gridi
    #sys.exit()
    return c*np.sum(ydin_tiheys.gridi * V_hartree.gridi)


def get_V_hartree(V_hartree, elektroni_tiheys, n_iter=10):
    """ lasketaan Hartree potentiaali tämänhetkiselle elektronitiheydelle"""
    for step in range(n_iter):
        tee_gauss_seidel_yksi_askel(V_hartree, elektroni_tiheys)
    

def E_tot(elektroni_tiheys, V_hartree, ydin_tiheys):
    """lasketaan elektronien kokonaisenergia 
    otetaan vakioelektronimäärä huomioon lagrangen kertoimen avulla"""
    import numpy as np
    
    # 100 iteraatiota jotta saadaan hartree potentiaali
    get_V_hartree(V_hartree, elektroni_tiheys, 10)

    ETOT = E_T(elektroni_tiheys)+\
        E_vaihtokorrelaatio(elektroni_tiheys) + \
        E_elektroni_elektroni(elektroni_tiheys, V_hartree) + \
        E_elektroni_ydin(ydin_tiheys, V_hartree)
 
    print "E_t",E_T(elektroni_tiheys), \
        "E_xc",E_vaihtokorrelaatio(elektroni_tiheys), \
        "E_ee",E_elektroni_elektroni(elektroni_tiheys, V_hartree), \
        "E_ne",E_elektroni_ydin(ydin_tiheys, V_hartree), \
        "ETOT", ETOT, \
        "NEL", np.sum(elektroni_tiheys.gridi)*V_hartree.get_volume_of_a_box()
    return ETOT

    
