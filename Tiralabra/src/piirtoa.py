#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" Tässä moduulissa piirtorutiineja.  """

import numpy as np
#import matplotlib
#import matplotlib.cm as cm
#import matplotlib.mlab as mlab
import matplotlib.pyplot as plt

def plot2d_color(gridi):
    """ 2d contour plot
    """
    dx = gridi.get_grid_step()
    dy = dx
    nx = gridi.gridi.shape[0]
    ny = gridi.gridi.shape[1]
    x=np.arange(0, nx*dx, dx)
    y=np.arange(0, ny*dy, dy)
    X, Y = np.meshgrid(x, y)
    print np.meshgrid(x, y)
    print "shape", gridi.get_xy().shape
    print gridi.get_xy()

    plt.figure()
    CS = plt.contour(X, Y, gridi.get_xy())
    plt.clabel(CS, inline=1, fontsize=10)
    #plt.title('Potential')
    plt.show()

def plot2d_simple(gridi):
    """ 2d contour plot
    """
    plt.figure()
    CS = plt.contour(gridi.gridi[:,:,0])
    plt.clabel(CS, inline=1, fontsize=10)
    #plt.title('Potential')
    plt.show()


def plot2d_color(gridi):
    """ 2d color plot
    """
    import scipy.interpolate

    # Set up a regular grid of interpolation points
    x = gridi.gridi[:,0,0]
    y = gridi.gridi[0,:,0]
    xi = np.linspace(gridi.minx(), gridi.maxx(), 100)
    yi = np.linspace(gridi.miny(), gridi.maxy(), 100)


    # Interpolate; there's also method='cubic' for 2-D data such as here
    zi = scipy.interpolate.griddata((x, y), gridi.gridi, (xi, yi), method='linear')    
    plt.imshow(zi, vmin=gridi.minz(), vmax=gridi.maxz(), origin='lower',
               extent=[x.min(), x.max(), y.min(), y.max()])
    plt.scatter(x, y, c=z)
    plt.colorbar()
    plt.show()

