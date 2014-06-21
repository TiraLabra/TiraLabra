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


def E_penalty(elektroni_tiheys):
    #print "el alku, el_nyt",\
    #    elektroni_tiheys.get_summa_mennyt(),\
    #    elektroni_tiheys.get_summa_nykyinen()
    #print "elektroni_tiheys.get_mylambda()", elektroni_tiheys.get_mylambda()
    return elektroni_tiheys.get_mylambda()*(\
            elektroni_tiheys.get_summa_mennyt()-\
                elektroni_tiheys.get_summa_nykyinen())**2

def E_T(elektroni_tiheys):
    """ Lasketaan elektronikaasun kineettinen energia gridissä.
    """
    import numpy as np
    c = 2.871
    #print c*np.sum(np.power(elektroni_tiheys.gridi,1.66666666))
    #print elektroni_tiheys.gridi
    et = c*np.sum(np.power(elektroni_tiheys.gridi,(5./3.)))
    if np.isnan(et):
        return 1e+5
    else:
        return et 

def E_vaihtokorrelaatio(elektroni_tiheys):
    """ Lasketaan elektronikaasun vaihtokorrelaatio energia gridissä.
    """
    import numpy as np
    c = -0.681420222312
    exc = c*np.sum(np.power(elektroni_tiheys.gridi,(4./3.)))
    if np.isnan(exc):
        return 1e+5
    else:
        return exc 

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


def get_V_hartree(V_hartree, elektroni_tiheys, tolerance=1e-5):
    """ lasketaan Hartree potentiaali tämänhetkiselle elektronitiheydelle"""
    e_vanha = E_elektroni_elektroni(elektroni_tiheys, V_hartree)
    e_uusi = e_vanha + 100.0
    counter = 0
    while (abs (e_uusi - e_vanha) > tolerance ):
        tee_gauss_seidel_yksi_askel(V_hartree, elektroni_tiheys)
        e_vanha = e_uusi
        e_uusi = E_elektroni_elektroni(elektroni_tiheys, V_hartree)
        counter = counter + 1
        #print "E_vanha, uusi", e_vanha , e_uusi
    #print "COUNTER", counter
    

def E_tot(elektroni_tiheys, V_hartree, ydin_tiheys):
    """lasketaan elektronien kokonaisenergia 
    otetaan vakioelektronimäärä huomioon lagrangen kertoimen avulla"""
    import numpy as np
    
    # xxx iteraatiota jotta saadaan hartree potentiaali
    get_V_hartree(V_hartree, elektroni_tiheys)

    ETOT = E_T(elektroni_tiheys)+\
        E_vaihtokorrelaatio(elektroni_tiheys) + \
        E_elektroni_elektroni(elektroni_tiheys, V_hartree) + \
        E_elektroni_ydin(ydin_tiheys, V_hartree)
 
    #print "ETOT", ETOT,\
    #    "E_t",E_T(elektroni_tiheys), \
    #    "E_xc",E_vaihtokorrelaatio(elektroni_tiheys), \
    #    "E_ee",E_elektroni_elektroni(elektroni_tiheys, V_hartree), \
    #    "E_ne",E_elektroni_ydin(ydin_tiheys, V_hartree), \
    #    "NEL", np.sum(elektroni_tiheys.gridi)*V_hartree.get_volume_of_a_box()
    return ETOT

    
def E_tot_with_penalty(elektroni_tiheys, V_hartree, ydin_tiheys):
    """ Lasketaan kokonaisenergia jossa on mukana rangaistus poikkeavasta 
    elektronien lukumäärästä """
    e_penalty = E_tot(elektroni_tiheys, V_hartree, ydin_tiheys) + \
        E_penalty(elektroni_tiheys)
    #print "e_penalty", E_penalty(elektroni_tiheys)
    #print "etot, ep", E_tot(elektroni_tiheys, V_hartree, ydin_tiheys),E_penalty(elektroni_tiheys)
    return e_penalty
