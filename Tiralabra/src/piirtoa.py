#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" Tässä moduulissa piirtorutiineja.  """

import numpy as np
#import matplotlib
#import matplotlib.cm as cm
#import matplotlib.mlab as mlab
import matplotlib.pyplot as plt

def plot2d(gridi, dx, dy, nx, ny):
    """ 2d contour plot
    """
    x=np.arange(0, nx*dx, dx)
    y=np.arange(0, ny*dy, dy)
    X, Y = np.meshgrid(x, y)

    plt.figure()
    b = gridi[:,:,0].copy()
    CS = plt.contour(X, Y, b)
    plt.clabel(CS, inline=1, fontsize=10)
    #plt.title('Potential')
    plt.show()


