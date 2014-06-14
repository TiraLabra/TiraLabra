#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" Tässä moduulissa laskentaan liittyviä funktioita. Esim. 
ratkaistaan hartree potentiaali  """

import numpy as np
import energiat

def tee_gauss_seidel_yksi_askel(V_hartree, varaus):
    """ Iteroidaan Poissonin yhtälön (nabla²=-4*pi*rho)
    ratkaisua yksi askel:
    http://en.wikipedia.org/wiki/Discrete_Poisson_equation
    Ratkaisun pitäisi olla Hartree potentiaali.
    Käytetään Gauss-Seidel iteraatiota:
    http://en.wikipedia.org/wiki/Gauss%E2%80%93Seidel_method

    xxx: parannus ks.
    http://wiki.scipy.org/PerformancePython
    """
    from math import pi
    gridi = V_hartree.gridi
    h = V_hartree.get_grid_step()
    imax = gridi.shape[0]
    jmax = gridi.shape[1]
    kmax = gridi.shape[2]
    #2d
    if kmax < 2:
        k=0
        for i in range(1, imax-1):
            for j in range(1, jmax-1):
                gridi[i,j,k] = \
                    gridi[i-1,j,k]*0.25 + \
                    gridi[i+1,j,k]*0.25 + \
                    gridi[i,j-1,k]*0.25 + \
                    gridi[i,j+1,k]*0.25 + \
                    pi * varaus.gridi[i,j,k] * h * h
    #3d
    #3d
    else:
        #print "3d", varaus
        for i in range(1, imax-1):
            for j in range(1, jmax-1):
                for k in range(1, kmax-1):
                    gridi[i,j,k] = \
                        gridi[i,j,k-1]/6 + \
                        gridi[i,j,k+1]/6 + \
                        gridi[i-1,j,k]/6 + \
                        gridi[i+1,j,k]/6 + \
                        gridi[i,j-1,k]/6 + \
                        gridi[i,j+1,k]/6 + \
                        2. / 3. * pi * varaus.gridi[i,j,k] * h * h * h



def monte_carlo_yksi_askel_kaikki(elektroni_tiheys, V_hartree,
                                  ydin_tiheys,
                                  tiheydenmuutos=0.1, 
                                  T=1000*0.0000031667908):
    """ muutetaan varaustiheyttä ja hyväksytään tai hylätään siirto 
    hyväksytään siirto== muutetaan uuteen tiheyteen
    hylätään siirto== ei muuteta tiheyttä.
    Koko varaustiheys muutetaan kerralla (jotta elektronimäärä säilyy)
    lämpötilakin (T) on atomiyksiköissä
    """
    import math
    kb = 3.1668114e-6  #Bolztmannin vakio atomiyksiköissä

    def onko_pariton(num):
        return bool(num % 2)

    #lasketaan kokonaisenergia vanhalla elektronitiheydellä
    e_vanha = energiat.E_tot(elektroni_tiheys, V_hartree, ydin_tiheys)
    # alkuvarausmuutos, kerrotaan 1-x ja 1+x jossa x muutos (aluksi 10%)
    size = elektroni_tiheys.get_sisapisteiden_lkm()
    positiiviset = np.ones(size/2)*(1.+tiheydenmuutos)
    negatiiviset = np.ones(size/2)*(1.-tiheydenmuutos)
    yrite = np.concatenate([positiiviset, negatiiviset])
    if onko_pariton(size):
        yksi = np.ones(1)
        yrite = np.concatenate([yrite, yksi])
    np.random.shuffle(yrite)
    #print "elektronitiheyden muutos", yrite
    elektroni_tiheys_vanha = elektroni_tiheys.get_sisapisteet().copy()
    yrite = yrite.reshape(elektroni_tiheys_vanha.shape)
    #print "elektroni_tiheys_vanha", elektroni_tiheys_vanha
    #print "elektroni_tiheys muutos", yrite
    elektroni_tiheys_uusi = elektroni_tiheys_vanha * yrite
    elektroni_tiheys.set_sisapisteet(elektroni_tiheys_uusi)
    #skaalataan elektronien lukumäärä takaisin alkuperäiseen
    elektroni_tiheys_uusi = elektroni_tiheys_uusi * \
        elektroni_tiheys.get_summa_mennyt() / \
        elektroni_tiheys.get_summa_nykyinen()
    elektroni_tiheys.set_sisapisteet(elektroni_tiheys_uusi)
    #print "elektroni_tiheys uusi", elektroni_tiheys.to_1d_list()
    #print "elektronitiheys uusi", elektroni_tiheys.gridi
    #print "uusi",
    e_uusi = energiat.E_tot(elektroni_tiheys, V_hartree, ydin_tiheys)
    e_diff = e_uusi - e_vanha
    print "ediff", e_diff
    if e_uusi > e_vanha:
        print "eksponenetti", math.exp(-(e_diff)/(kb*T)), e_diff
        #print 
        if np.random.random_sample() > math.exp(-(e_diff)/(kb*T)):
            #hylätään uusi 
            elektroni_tiheys.set_sisapisteet(elektroni_tiheys_vanha)
            print "MC hylätty"
            return False
        else:
            print "MC hyväksytty YLÄMÄKEEN"
            return False
    else:
        print "MC hyväksytty"
        return True


def minimoi_monte_carlolla(n_iter=100000, tol=1e-6):
    

def yhden_pisteen_derivaatta(elektroni_tiheys, V_hartree,
                             ydin_tiheys, tiheydenmuutos,
                             i, j, k):
    """ lasketaan energian funktionaaliderivaatta gridipisteessä i,j,k """

    elektroni_tiheys[i,j,k]=\
        elektroni_tiheys[i,j,k]\
        + tiheydenmuutos
    e_plus = \
        energiat.E_tot(elektroni_tiheys, V_hartree, ydin_tiheys)
    elektroni_tiheys[i,j,k]=\
        elektroni_tiheys[i,j,k]\
        -2.0 * tiheydenmuutos
    e_miinus = \
        energiat.E_tot(elektroni_tiheys, V_hartree, ydin_tiheys)
    yhden_pisteen_derivaatta = \
        (e_plus - e_miinus) \
        / (2.0 * tiheydenmuutos)
    elektroni_tiheys[i,j,k]=\
        elektroni_tiheys[i,j,k]\
        + tiheydenmuutos
    return yhden_pisteen_derivaatta

def funktionaaliderivaatta(elektroni_tiheys, V_hartree,
                                  ydin_tiheys,
                                  tiheydenmuutos=0.01):
    """ Lasketaan kokonaisenergian funktionaaliderivaatta tiheyden suhteen.
    ELi lasketaan joka gridi pisteessä
    ( [E(n(r_i)+delta n] - [ E(n(r_i)-delta n] / (2*delta n)
    Tätä käytetään myöhemmin kun muutetaan tiheyttä Steepest descentillä
    siihen suuntaa kuin funktionaaliderivaatta osoittaa 
    """
    import math

    # alustataan funktionaaliderivaatta nollaksi
    derivaatta = V_hartree.gridi * 0.0
    imax = gridi.shape[0]
    jmax = gridi.shape[1]
    kmax = gridi.shape[2]
    #2d
    if kmax < 2:
        k=0
        for i in range(1, imax-1):
            for j in range(1, jmax-1):
                derivaatta[i, j, k] = \
                    yhden_pisteen_derivaatta(elektroni_tiheys, V_hartree,
                                             ydin_tiheys,
                                             tiheydenmuutos,
                                             i,j,k)
    #3d
    else:
        for i in range(1, imax-1):
            for j in range(1, jmax-1):
                for k in range(1, kmax-1):
                    derivaatta[i, j, k] = \
                        yhden_pisteen_derivaatta(elektroni_tiheys, V_hartree,
                                                 ydin_tiheys,
                                                 tiheydenmuutos,
                                                 i,j,k)
    return derivaatta
    

