#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" 
Tässä modulissa määritellään ydin objekti. Eli atomin ydin.
"""
import numpy as np

class Ydin(object):
    """Yksittäistä ydintä esittävä luokka
    
    Parametrit:
    
    paikka: kolmen floatin jono
         ytimen paikka

    varaus: float
         ytimen varaus

    """

    def __init__(self, paikka=(0, 0, 0), varaus=None):
        self.paikka = np.array(paikka, float)
        self.varaus = varaus 

    def get_x(self):
        """ ytimen x koordinaatti """
        return self.paikka[0]

    def get_y(self):
        """ ytimen y koordinaatti """
        return self.paikka[1]

    def get_z(self):
        """ ytimen z koordinaatti """
        return self.paikka[2]

    def get_varaus(self):
        """ ytimen varaus """
        return self.varaus
