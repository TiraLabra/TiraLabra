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
                                  ydin_tiheys):
    """ muutetaan varaustiheyttä ja hyväksytään tai hylätään siirto 
    hyväksytään siirto== muutetaan uuteen tiheyteen
    hylätään siirto== ei muuteta tiheyttä.
    Koko varaustiheys muutetaan kerralla (jotta elektronimäärä säilyy)
    lämpötilakin (T) on atomiyksiköissä
    """
    import math
    kb = 3.1668114e-6  #Bolztmannin vakio atomiyksiköissä
    T = elektroni_tiheys.get_temperature()*0.0000031667908
    d_rho = elektroni_tiheys.get_d_rho()
    def onko_pariton(num):
        return bool(num % 2)

    #lasketaan kokonaisenergia vanhalla elektronitiheydellä
    e_vanha = energiat.E_tot(elektroni_tiheys, V_hartree, ydin_tiheys)
    # alkuvarausmuutos, kerrotaan 1-x ja 1+x jossa x muutos (aluksi 10%)
    size = elektroni_tiheys.get_sisapisteiden_lkm()
    positiiviset = np.ones(size/2)*(1.+d_rho)
    negatiiviset = np.ones(size/2)*(1.-d_rho)
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
    #print "ediff", e_diff
    if e_uusi > e_vanha:
        #print "eksponenetti", math.exp(-(e_diff)/(kb*T)), e_diff
        #print 
        if np.random.random_sample() > math.exp(-(e_diff)/(kb*T)):
            #hylätään uusi 
            elektroni_tiheys.set_sisapisteet(elektroni_tiheys_vanha)
            #print "MC hylätty"
            return False
        else:
            #print "MC hyväksytty YLÄMÄKEEN"
            return True
    else:
        #print "MC hyväksytty"
        return True


def minimoi_monte_carlolla(myfile, 
                           elektroni_tiheys, V_hartree,
                           ydin_tiheys):
    """ minimoidaan monte carlo menetelmällä kunnes n_iter tulee täyteen
    tai convergenssikriteeri (tol) täyttyy """

    e_vanha = energiat.E_tot(elektroni_tiheys, V_hartree, ydin_tiheys)
    e_uusi = e_vanha + 100.0
    converged = False
    n_hyvaksytty = 0
    n_hylatty = 0
    d_rho = elektroni_tiheys.get_d_rho()

    for it in range(elektroni_tiheys.get_n_iter()):
        if abs(e_uusi - e_vanha) < elektroni_tiheys.get_tol():
            converged = True
            print "converged"
            break
        hyvaksytty = monte_carlo_yksi_askel_kaikki(
            elektroni_tiheys, V_hartree,
            ydin_tiheys)

        if hyvaksytty:
            e_vanha = e_uusi
            e_uusi = energiat.E_tot(elektroni_tiheys, V_hartree, ydin_tiheys)
            print "E_mc",e_uusi,\
                "elektronimäärä", elektroni_tiheys.get_number_of_electrons()
            #myfile.write(str(e_uusi)+
            #             '  '+str( d_rho)+'\n')
            myfile.write(str(e_uusi)+'\n')

        if hyvaksytty:
            n_hyvaksytty =n_hyvaksytty+1
        else:
            n_hylatty = n_hylatty+1
        #print "hyv/hyl", n_hyvaksytty, n_hylatty
        if n_hyvaksytty > 5 and n_hylatty==0:
            #hyvaksytaan liian usein
            d_rho = d_rho * 1.1
            n_hyvaksytty = 0
            n_hylatty = 0
        elif n_hylatty > 5 and n_hyvaksytty==0:
            #hylataan liian usein
            d_rho = d_rho * 0.9
            n_hyvaksytty = 0
            n_hylatty = 0
        elif (n_hyvaksytty > 0): 
            #hylataan liian usein
            if (n_hylatty/n_hyvaksytty > 10.0):
                d_rho = d_rho * 0.9
                n_hyvaksytty = 0
                n_hylatty = 0
        elif (n_hylatty > 0): 
            #hylataan liian usein
            if (n_hyvaksytty/n_hylatty > 10.0):
                d_rho = d_rho * 1.1
                n_hyvaksytty = 0
                n_hylatty = 0
        else:
            #ollaan ok alueella
            pass
        elektroni_tiheys.set_d_rho(d_rho)
    return converged
    

