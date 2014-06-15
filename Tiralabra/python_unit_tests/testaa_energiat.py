#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" testataan modulin energiat.py funktioita """

import sys, os
sys.path.insert(0, os.path.abspath("../src") )
import energiat
import read_data

#
import unittest
class TestEnergyFunctions(unittest.TestCase):
    """ energiaan liittyvät unit testit """


    def setUp(self):
        """ alustetaan testeihin liittyvät muuttujat """
        [ny, nx, nz, elektroni_lkm, elektroni_tiheys,
         V_hartree, ydin_tiheys, ytimien_lkm, ydin_tiheys] = \
         read_data.read_data(filename = 'alkuarvot.txt_5x5x0')
        self.elektroni_tiheys = elektroni_tiheys
        self.V_hartree1 = V_hartree
        self.V_hartree2 = V_hartree
        self.V_hartree3 = V_hartree
        self.ydin_tiheys = ydin_tiheys
        #energiat.get_V_hartree(
        #    V_hartree, elektroni_tiheys, tolerance=0.001)
        #print energiat.E_elektroni_ydin(elektroni_tiheys, V_hartree)
    
    def test_E_T(self):
        """ testataan liike-energia """
        e_kin = energiat.E_T(self.elektroni_tiheys)
        self.assertAlmostEqual(e_kin, 1429.56811, places=4)

    def test_E_vaihtokorrelaatio(self):
        """ testataan vaihtokorrelaatioenergia """
        e_vaihtokorrelaatio = energiat.E_vaihtokorrelaatio(
            self.elektroni_tiheys)
        self.assertAlmostEqual(e_vaihtokorrelaatio, -152.0550, places=4)

    def test_E_elektroni_elektroni(self):
        """ testataan elektronien itseisvuorovaikutusenergia """
        energiat.get_V_hartree(
            self.V_hartree1, self.elektroni_tiheys, tolerance=0.001)
        e_elektroni_elektroni = energiat.E_elektroni_elektroni(
            self.elektroni_tiheys, self.V_hartree1)
        self.assertAlmostEqual(e_elektroni_elektroni, 57.2074, places=4)

    def test_E_elektroni_ydin(self):
        """ testataan elektronit-ytimet vuotovaikutusenergia """
        energiat.get_V_hartree(
            self.V_hartree2, self.elektroni_tiheys, tolerance=0.001)
        e_elektroni_ydin = energiat.E_elektroni_ydin(
            self.ydin_tiheys, self.V_hartree2)
        self.assertAlmostEqual(e_elektroni_ydin, -157.07796, places=4)

    def test_E_tot(self):
        """ testataan kokonaisenergia """
        energiat.get_V_hartree(
            self.V_hartree3, self.elektroni_tiheys, tolerance=0.001)
        e_tot = energiat.E_T(self.elektroni_tiheys)+\
        energiat.E_vaihtokorrelaatio(self.elektroni_tiheys) + \
        energiat.E_elektroni_elektroni(
            self.elektroni_tiheys, self.V_hartree3) + \
        energiat.E_elektroni_ydin(
            self.ydin_tiheys, self.V_hartree3)
        self.assertAlmostEqual(e_tot, 1177.64253, places=4)

if __name__ == '__main__':
    unittest.main()



