#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" Tässä moduulissa steepest descent minimointiin  liittyviä funktioita."""


import numpy as np
import energiat


def yhden_pisteen_derivaatta(elektroni_tiheys, V_hartree,
                             ydin_tiheys, tiheydenmuutos,
                             i, j, k, mylambda):
    """ lasketaan energian funktionaaliderivaatta gridipisteessä i,j,k """
    elektroni_tiheys.gridi[i,j,k]=\
        elektroni_tiheys.gridi[i,j,k]\
        + tiheydenmuutos
    e_plus = \
        energiat.E_tot_with_penalty(\
        elektroni_tiheys, V_hartree, ydin_tiheys, mylambda)
    elektroni_tiheys.gridi[i,j,k]=\
        elektroni_tiheys.gridi[i,j,k]\
        -2.0 * tiheydenmuutos
    e_miinus = \
        energiat.E_tot_with_penalty(\
        elektroni_tiheys, V_hartree, ydin_tiheys, mylambda)
    yhden_pisteen_derivaatta = \
        (e_plus - e_miinus) \
        / (2.0 * tiheydenmuutos)
    elektroni_tiheys.gridi[i,j,k]=\
        elektroni_tiheys.gridi[i,j,k]\
        + tiheydenmuutos
    #print "tm", tiheydenmuutos
    return -yhden_pisteen_derivaatta

def funktionaaliderivaatta(mylambda, elektroni_tiheys, V_hartree,
                                  ydin_tiheys,
                                  tiheydenmuutos=1e-3 ):
    """ Lasketaan kokonaisenergian funktionaaliderivaatta tiheyden suhteen.
    ELi lasketaan joka gridi pisteessä
    ( [E(n(r_i)+delta n] - [ E(n(r_i)-delta n] / (2*delta n)
    Tätä käytetään myöhemmin kun muutetaan tiheyttä Steepest descentillä
    siihen suuntaa kuin funktionaaliderivaatta osoittaa 
    """
    import math

    # alustataan funktionaaliderivaatta nollaksi
    derivaatta = V_hartree.gridi * 0.0
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
                                             tiheydenmuutos,
                                             i,j,k,mylambda)
    #3d
    else:
        for i in range(1, imax-1):
            for j in range(1, jmax-1):
                for k in range(1, kmax-1):
                    derivaatta[i, j, k] = \
                        yhden_pisteen_derivaatta(elektroni_tiheys, V_hartree,
                                                 ydin_tiheys,
                                                 tiheydenmuutos,
                                                 i,j,k, mylambda)
    return derivaatta
    

def tee_yksi_sd_askel(mylambda, h, elektroni_tiheys, V_hartree,
                      ydin_tiheys,
                      tiheyden_muutos = 1e-3,
                      tol=1e-6):
    """Haarukoidaan energian minimi tiheyden gradientin suunnassa
    ks. NUMERICAL METHODS IN ENGINEERING WITH Python Jaan Kiusalaas
    The Pennsylvania State University (2005)
    """
    import sys
    from math import log
    import copy

    def etsi_minimi_karkeasti(mylambda, h, V_hartree,
                      ydin_tiheys,
                      tiheyden_muutos = 1e-3,
                      tol=1e-6):
        import copy
        """ensin karkea minimi haarukoidaan 
        (i-1)*tiheyden_muutos ja (i)*tiheyden_muutos väliin
        h=askel derivaatan suunnassa
        
        Palauttaa välin jolta minimi löytyy (a,b,)
        ja booleanin löytyikö mimimi (True=löytyi, False=ei löytynyt)
        """

        c = 1.618033989
        x1 = 0
        #tehdään aidosti uudet objektit eikä vain viittauksia
        tiheys_x1 = copy.copy(elektroni_tiheys)
        tiheys_x2 = copy.copy(elektroni_tiheys)
        tiheys_x3 = copy.copy(elektroni_tiheys)

        f1 = energiat.E_tot_with_penalty(
            tiheys_x1, V_hartree, ydin_tiheys, mylambda)
        x2 = x1 + h
        tiheys_x2.gridi = copy.copy(elektroni_tiheys.gridi) + \
            x2 * funktionaaliderivaatta(mylambda, elektroni_tiheys, V_hartree,
                                           ydin_tiheys,
                                           tiheyden_muutos)
        f2 = energiat.E_tot_with_penalty(
            tiheys_x2, V_hartree, ydin_tiheys, mylambda)
        
        #print "f1, f2", f1, f2
        if (f2 > f1):
            #print "askel liian pitkä tai bugi, pienennä askelta"
            #print "askel oli", h
            return 0, 0.9*h, False

        for i in range (100):
            h = h * c
            x3 = x2 + h
            tiheys_x3.gridi = copy.copy(elektroni_tiheys.gridi) + \
                x3 * funktionaaliderivaatta(mylambda,
                                            elektroni_tiheys, V_hartree,
                                            ydin_tiheys,
                                            tiheyden_muutos)
            f3 = energiat.E_tot_with_penalty(
                tiheys_x3, V_hartree, ydin_tiheys, mylambda)
            #print "f1, f2, f3,x1-3", f1, f2, f3,x1,x2,x3
            if f3 > f2: 
                return x1,x3, True
            x1 = x2; x2 = x3
            f1 = f2; f2 = f3


        #print "ei löytynyt minimiä, kasvata tiheys askelta (tiheyden_muutos)"
        return 0, 2*h, False
        #sys.exit()

    # etsitään karkea minimi: minimi on välillä [a, b]
    found = False
    while not(found):
        [a, b, found] =    etsi_minimi_karkeasti(mylambda, h, V_hartree,
                                                 ydin_tiheys,
                                                 tiheyden_muutos = 1e-3,
                                                 tol=1e-6)
        if not(found):
            h = b
        else:
            h=b/10.0

    #arvoidaan karkeasti että seuraavalla iteratiolla tutkitaan väliä 
    # 0..0.1*b
    uusi_h = h
    #nyt tiedetään että minimi on välillä [a,b]
    #haarukoidaan kultaisella haarukoinnilla
    nIter = int(-2.078087*log(tol/abs(b-a)))+1 
    #golden search konvergoituu nIter ask.
    #print "a,b", a,b
    R = 0.618033989
    C = 1.0 - R
    x1 = R*a + C*b; x2 = C*a + R*b
    #x1=a; x2=b
    #print "x1, x2", x1, x2

    #tehdään aidot kopiot, ettei kopioida vain viittausta
    tiheys_x1 = copy.copy(elektroni_tiheys)
    tiheys_x2 = copy.copy(elektroni_tiheys)

    tiheys_x1.gridi =  copy.copy(elektroni_tiheys.gridi) + \
        x1 * funktionaaliderivaatta(mylambda, elektroni_tiheys, V_hartree,
                                           ydin_tiheys,
                                           tiheyden_muutos)
    f1 = energiat.E_tot_with_penalty(\
        tiheys_x1, V_hartree, ydin_tiheys, mylambda)
    tiheys_x2.gridi =  copy.copy(elektroni_tiheys.gridi) + \
        x2 * funktionaaliderivaatta(mylambda, elektroni_tiheys, V_hartree,
                                           ydin_tiheys,
                                           tiheyden_muutos)
    f2 = energiat.E_tot_with_penalty(\
        tiheys_x2, V_hartree, ydin_tiheys, mylambda)
    #print "f1, f2", f1, f2

    # Etsintä luuppi
    for i in range(nIter):
        if f1 > f2:
            a = x1
            x1 = x2; f1 = f2
            x2 = C*a + R*b
            tiheys_x2.gridi = copy.copy(elektroni_tiheys.gridi) + \
                x2 * funktionaaliderivaatta(mylambda,
                                            elektroni_tiheys, V_hartree,
                                            ydin_tiheys,
                                            tiheyden_muutos)
            f2 = energiat.E_tot_with_penalty(
                tiheys_x2, V_hartree, ydin_tiheys, mylambda)
        else:
            b = x2
            x2 = x1; f2 = f1
            x1 = R*a + C*b
            tiheys_x1.gridi =  copy.copy(elektroni_tiheys.gridi) + \
                x1 * funktionaaliderivaatta(mylambda,
                                            elektroni_tiheys, V_hartree,
                                            ydin_tiheys,
                                            tiheyden_muutos)
            f1 = energiat.E_tot_with_penalty(
                tiheys_x1, V_hartree, ydin_tiheys, mylambda)
        #print "x1, f1(x1), x2, f2(x2)",x1, f1, x2, f2 
        #print "Nel(x1), Nel(x2)",\
        #    tiheys_x1.get_summa_nykyinen(), tiheys_x2.get_summa_nykyinen()
    if f1 < f2: 
        #print "f1< f2"
        return f1, tiheys_x1, uusi_h
    else: 
        #print "f2< f1"
        return f2, tiheys_x2, uusi_h

def minimoi_steepes_descentilla(mylambda, askel, myfile, 
                           elektroni_tiheys, V_hartree,
                           ydin_tiheys,
                           tiheydenmuutos,
                           n_iter=100000, tol=1e-4):
    """ minimoidaan steepest descent menetelmällä kunnes n_iter tulee täyteen
    tai convergenssikriteeri (tol) täyttyy """
    import copy

    e_vanha = energiat.E_tot_with_penalty(
        elektroni_tiheys, V_hartree, ydin_tiheys, mylambda)
    e_uusi = e_vanha + 100.0
    converged = False
    n_hyvaksytty = 0
    n_hylatty = 0

    for it in range(n_iter):
        #print "error", abs(e_uusi - e_vanha)
        if abs(e_uusi - e_vanha) < tol:
            converged = True
            print "converged"
            myfile.write(str(e_tot)+'\n')            
            break
        [energia, uusi_tiheys, uusi_askel] = tee_yksi_sd_askel(
            mylambda, askel, elektroni_tiheys, V_hartree,
            ydin_tiheys,
            tiheyden_muutos = 1e-3,
            tol=1e-6)
        elektroni_tiheys_vanha = elektroni_tiheys
        elektroni_tiheys = uusi_tiheys
        #skaalataan elektronien lukumäärä takaisin alkuperäiseen
        elektroni_tiheys_oikea = copy.copy(uusi_tiheys)
        elektroni_tiheys_uusi = uusi_tiheys.get_sisapisteet().copy()
        elektroni_tiheys_uusi = elektroni_tiheys_uusi * \
            elektroni_tiheys.get_summa_mennyt() / \
            uusi_tiheys.get_summa_nykyinen()
        #de = abs(elektroni_tiheys.get_summa_mennyt()-\
        #             uusi_tiheys.get_summa_nykyinen())
        elektroni_tiheys_oikea.set_sisapisteet(elektroni_tiheys_uusi)
        askel = uusi_askel
        e_vanha = e_uusi
        e_uusi = energiat.E_tot_with_penalty(
            elektroni_tiheys, V_hartree, ydin_tiheys, mylambda)
        e_tot = energiat.E_tot(
            elektroni_tiheys_oikea, V_hartree, ydin_tiheys)
        #print "Kemiallinen potentiaali",abs(e_tot-
        #                                    energiat.E_tot(
        #        elektroni_tiheys_oikea, V_hartree, ydin_tiheys))/\
        #        de

        print "EP, ETOT, uusi_askel",e_uusi, e_tot, uusi_askel
        #elektronimäärää pitää kontrolloitda tarkemmin jos ei meinaa 
        # konvergoida
        if e_uusi > e_vanha:
            print "probleemi, euusi> evanha"
            converged = True
            print "converged"
            elektroni_tiheys = elektroni_tiheys_vanha
            break
        myfile.write(str(e_tot)+'\n')
    return converged
