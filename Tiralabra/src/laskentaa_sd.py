#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" Tässä moduulissa steepest descent minimointiin  liittyviä funktioita."""


import numpy as np
import energiat


def yhden_pisteen_derivaatta(elektroni_tiheys, V_hartree,
                             ydin_tiheys, 
                             i, j, k):
    """ lasketaan energian funktionaaliderivaatta gridipisteessä i,j,k """
    tiheydenmuutos = elektroni_tiheys.get_tiheydenmuutos()
    elektroni_tiheys.gridi[i,j,k]=\
        elektroni_tiheys.gridi[i,j,k]\
        + tiheydenmuutos
    e_plus = \
        energiat.E_tot_with_penalty(\
        elektroni_tiheys, V_hartree, ydin_tiheys)
    elektroni_tiheys.gridi[i,j,k]=\
        elektroni_tiheys.gridi[i,j,k]\
        -2.0 * tiheydenmuutos
    e_miinus = \
        energiat.E_tot_with_penalty(\
        elektroni_tiheys, V_hartree, ydin_tiheys)
    yhden_pisteen_derivaatta = \
        (e_plus - e_miinus) \
        / (2.0 * tiheydenmuutos)
    elektroni_tiheys.gridi[i,j,k]=\
        elektroni_tiheys.gridi[i,j,k]\
        + tiheydenmuutos
    return -yhden_pisteen_derivaatta

def funktionaaliderivaatta(elektroni_tiheys, V_hartree,
                           ydin_tiheys):
    """ Lasketaan kokonaisenergian funktionaaliderivaatta tiheyden suhteen.
    ELi lasketaan joka gridi pisteessä
    ( [E(n(r_i)+delta n] - [ E(n(r_i)-delta n] / (2*delta n)
    Tätä käytetään myöhemmin kun muutetaan tiheyttä Steepest descentillä
    siihen suuntaa kuin funktionaaliderivaatta osoittaa 
    """
    import math, copy

    # alustataan funktionaaliderivaatta nollaksi
    derivaatta = copy.copy(V_hartree.gridi) * 0.0
    imax = V_hartree.gridi.shape[0]
    jmax = V_hartree.gridi.shape[1]
    kmax = V_hartree.gridi.shape[2]
    #2d
    if kmax < 2:
        k=0
        for i in range(1, imax-1):
            for j in range(1, jmax-1):
                derivaatta[i, j, k] = \
                    yhden_pisteen_derivaatta(elektroni_tiheys, V_hartree,
                                             ydin_tiheys,
                                             i,j,k)
    #3d
    else:
        for i in range(1, imax-1):
            for j in range(1, jmax-1):
                for k in range(1, kmax-1):
                    derivaatta[i, j, k] = \
                        yhden_pisteen_derivaatta(elektroni_tiheys, V_hartree,
                                                 ydin_tiheys,
                                                 i,j,k)
    return derivaatta
    

def tee_yksi_sd_askel(elektroni_tiheys, V_hartree,
                      ydin_tiheys):
    """Haarukoidaan energian minimi tiheyden gradientin suunnassa
    ks. NUMERICAL METHODS IN ENGINEERING WITH Python Jaan Kiusalaas
    The Pennsylvania State University (2005)
    """
    import sys
    from math import log
    import copy

    def etsi_minimi_karkeasti(elektroni_tiheys, V_hartree,
                      ydin_tiheys):
        """ensin karkea minimi haarukoidaan 
        (i-1)*tiheyden_muutos ja (i)*tiheyden_muutos väliin
        h=askel derivaatan suunnassa
        
        Palauttaa välin jolta minimi löytyy (a,b,)
        ja booleanin löytyikö mimimi (True=löytyi, False=ei löytynyt)
        """
        import copy
        c = 1.618033989
        x1 = 0
        h = elektroni_tiheys.get_askel()
        #tehdään aidosti uudet objektit eikä vain viittauksia
        tiheys_x1 = copy.copy(elektroni_tiheys)
        tiheys_x2 = copy.copy(elektroni_tiheys)
        tiheys_x3 = copy.copy(elektroni_tiheys)

        f1 = energiat.E_tot_with_penalty(
            tiheys_x1, V_hartree, ydin_tiheys)
        x2 = x1 + h
        tiheys_x2.gridi = elektroni_tiheys.gridi + \
            x2 * funktionaaliderivaatta(elektroni_tiheys, V_hartree,
                                           ydin_tiheys)
        f2 = energiat.E_tot_with_penalty(
            tiheys_x2, V_hartree, ydin_tiheys)
        
        if (f2 > f1):
            if h < 1e-6:
                print "muutos liian pieni"
                return 0,h,True
            else:
                return 0, 0.9*h, False

        for i in range (100):
            h = h * c
            x3 = x2 + h
            tiheys_x3.gridi = elektroni_tiheys.gridi + \
                x3 * funktionaaliderivaatta(elektroni_tiheys, V_hartree,
                                            ydin_tiheys)
            f3 = energiat.E_tot_with_penalty(
                tiheys_x3, V_hartree, ydin_tiheys)
            if f3 > f2: 
                return x1,x3, True
            x1 = x2; x2 = x3
            f1 = f2; f2 = f3


        print "ei löytynyt minimiä, kasvata tiheys askelta (tiheyden_muutos)"
        return 0, 1.1*h, False
        #sys.exit()

    # etsitään karkea minimi: minimi on välillä [a, b]
    found = False
    while not(found):
        [a, b, found] =    etsi_minimi_karkeasti(elektroni_tiheys, V_hartree,
                                                 ydin_tiheys)
        if not(found):
            elektroni_tiheys.set_askel(b)

    #nyt tiedetään että minimi on välillä [a,b]
    #haarukoidaan kultaisella haarukoinnilla
    tol2 = elektroni_tiheys.get_tol()*0.01
    nIter = int(-2.078087*log(tol2/abs(b-a)))+1 
    #golden search konvergoituu nIter ask.
    R = 0.618033989
    C = 1.0 - R
    x1 = R*a + C*b; x2 = C*a + R*b

    #tehdään aidot kopiot, ettei kopioida vain viittausta
    tiheys_x1 = copy.copy(elektroni_tiheys)
    tiheys_x2 = copy.copy(elektroni_tiheys)

    tiheys_x1.gridi =  elektroni_tiheys.gridi + \
        x1 * funktionaaliderivaatta(elektroni_tiheys, V_hartree,
                                    ydin_tiheys)
    f1 = energiat.E_tot_with_penalty(\
        tiheys_x1, V_hartree, ydin_tiheys)
    tiheys_x2.gridi =  elektroni_tiheys.gridi + \
        x2 * funktionaaliderivaatta(elektroni_tiheys, V_hartree,
                                    ydin_tiheys)
    f2 = energiat.E_tot_with_penalty(\
        tiheys_x2, V_hartree, ydin_tiheys)
    # Etsintä luuppi
    for i in range(nIter):
        if f1 > f2:
            a = x1
            x1 = x2; f1 = f2
            x2 = C*a + R*b
            tiheys_x2.gridi = elektroni_tiheys.gridi + \
                x2 * funktionaaliderivaatta(elektroni_tiheys, V_hartree,
                                            ydin_tiheys)
            f2 = energiat.E_tot_with_penalty(
                tiheys_x2, V_hartree, ydin_tiheys)
            
        else:
            b = x2
            x2 = x1; f2 = f1
            x1 = R*a + C*b
            tiheys_x1.gridi =  elektroni_tiheys.gridi + \
                x1 * funktionaaliderivaatta(elektroni_tiheys, V_hartree,
                                            ydin_tiheys)
            f1 = energiat.E_tot_with_penalty(
                tiheys_x1, V_hartree, ydin_tiheys)
    if f1 < f2: 
        return f1, tiheys_x1
    else: 
        return f2, tiheys_x2

def minimoi_steepest_descentilla(myfile, 
                           elektroni_tiheys, V_hartree,
                           ydin_tiheys):
    """ minimoidaan steepest descent menetelmällä kunnes n_iter tulee täyteen
    tai convergenssikriteeri (tol) täyttyy """
    import copy

    e_vanha = energiat.E_tot_with_penalty(
        elektroni_tiheys, V_hartree, ydin_tiheys)
    e_uusi = e_vanha + 100.0
    converged = False
    n_hyvaksytty = 0
    n_hylatty = 0
    askel = elektroni_tiheys.get_askel()

    for it in range(elektroni_tiheys.get_n_iter()):
        if abs(e_uusi - e_vanha) < elektroni_tiheys.get_tol():
            converged = True
            print "converged"
            myfile.write(str(e_tot)+'\n')            
            break
        [energia, uusi_tiheys] = tee_yksi_sd_askel(
            elektroni_tiheys, V_hartree,
            ydin_tiheys)
        elektroni_tiheys_vanha = copy.copy(elektroni_tiheys)
        elektroni_tiheys = copy.copy(uusi_tiheys)
        e_vanha = e_uusi
        e_uusi = energiat.E_tot_with_penalty(
            elektroni_tiheys, V_hartree, ydin_tiheys)
        print "EP, ETOT, uusi_askel",e_uusi, elektroni_tiheys.get_askel()
        #elektronimäärää pitää kontrolloitda tarkemmin jos ei meinaa 
        # konvergoida
        if e_uusi > e_vanha:
            print "probleemi, euusi> evanha"
            converged = True
            print "converged"
            elektroni_tiheys = elektroni_tiheys_vanha
            break
        #skaalataan elektronien jotta saadaan oikea E_tot
        elektroni_tiheys_oikea = copy.copy(elektroni_tiheys)
        elektroni_tiheys_oikea.gridi = elektroni_tiheys.gridi * \
            elektroni_tiheys.get_summa_mennyt() / \
            elektroni_tiheys.get_summa_nykyinen()
        e_tot = energiat.E_tot(
            elektroni_tiheys_oikea, V_hartree, ydin_tiheys)

        myfile.write(str(e_tot)+'\n')
    return converged, elektroni_tiheys_oikea
