#!/usr/bin/env python
# -*- coding: utf-8 -*-

import matplotlib
matplotlib.rc('text', usetex=True)
import matplotlib.pyplot as plt
from ase import Atoms 
from gpaw import GPAW 
import numpy as np
from piirtoa import plot2d
 
a = 4.0 
h = 0.2
nbands = 1
H = Atoms('H', [(a / 2, a / 2, a / 2)], 
          pbc=0, 
          cell=(a, a, a)) 
calc = GPAW(nbands=nbands, h=h, charge=0) 
H.set_calculator(calc) 
print H.get_potential_energy() + calc.get_reference_energy() 

density = calc.get_pseudo_density()
print density.shape
nx = density.shape[0]
ny = density.shape[1]

density_xy = np.sum(density,axis=2)*h*h*h
plot2d(density_xy, h, h, nx, ny)



