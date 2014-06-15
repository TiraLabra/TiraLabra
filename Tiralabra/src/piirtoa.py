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

def grid(x, y, z, resX=100, resY=100):
    "Convert 3 column data to matplotlib grid"
    xi = linspace(min(x), max(x), resX)
    yi = linspace(min(y), max(y), resY)
    Z = griddata(x, y, z, xi, yi)
    X, Y = meshgrid(xi, yi)
    return X, Y, Z

def plot3d(gridi):

    from mpl_toolkits.mplot3d import axes3d
    import matplotlib.pyplot as plt
    from matplotlib import cm

    fig = plt.figure()
    ax = fig.gca(projection='3d')
    X, Y, Z = axes3d.get_test_data(0.05)
    print "axes3d.get_test_data(0.05)",list(axes3d.get_test_data(0.05)).shape
    cset = ax.contour(X, Y, Z, extend3d=True, cmap=cm.coolwarm)
    ax.clabel(cset, fontsize=9, inline=1)
    
    plt.show()


def plot2d_simple(gridi):
    """ 2d contour plot
    """
    plt.figure()
    X = np.arange(0, gridi.gridi.shape[0], 1)
    Y = np.arange(0, gridi.gridi.shape[1], 1)
    Z = gridi.gridi[:,:,0]    
    CS = plt.contour(X,Y,Z)
    plt.clabel(CS, inline=1, fontsize=10)
    plt.title('Electron density' )
    plt.show()    

def plot3d_simple(gridi):
    """ 2d contour plots of 3d data using xy slices
    """
    plt.figure()
    X = np.arange(0, gridi.gridi.shape[0], 1)
    Y = np.arange(0, gridi.gridi.shape[1], 1)
    print "gridi.gridi.shape[2]", gridi.gridi.shape[2]
    for iz in range(gridi.gridi.shape[2]-2):
        print "iz", iz+1
        Z = gridi.gridi[:,:,iz+1]    
        CS = plt.contour(X,Y,Z)
        plt.clabel(CS, inline=1, fontsize=10)
        plt.title('Electron density' )
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

