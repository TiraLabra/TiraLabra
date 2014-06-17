#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" Luetaan laskentaa liittyvää dataa ja palautetaan data """
from gridi import *
import string

def read_data(filename = 'alkuarvot.txt'):
    """
    Yritetään lukea alkuarvot filestä alkuarvot.txt
    muutetaan siten että x->y ja y->x 
    eli input tiedossa y tulee ensin koska se on matriisin 1. dimensio
    gridin koon määrittelyssä ja ytimien paikkoken määrittelyssä
    """

    infile=open(filename,'r')
    ny=int(infile.readline().strip())
    nx=int(infile.readline().strip())
    nz=int(infile.readline().strip())
    elektroni_lkm=float(infile.readline().strip())
    elektroni_tiheys = Gridi(nx=nx,ny=ny,nz=nz,init_value=elektroni_lkm)
    V_hartree = Gridi(nx=nx,ny=ny,nz=nz,init_value=0)
    ydin_tiheys = Gridi(nx=nx,ny=ny,nz=nz,init_value=0)
    ytimien_lkm=int(infile.readline().strip())
    for i_ydin in range(ytimien_lkm):
        line=infile.readline()
        col=string.split(line)
        iy=int(col[0])
        ix=int(col[1])
        iz=int(col[2])
        z =float(col[3])
        ydin_tiheys.gridi[ix,iy,iz]=1.0/ydin_tiheys.get_volume_of_a_box()
    return [ny, nx, nz, elektroni_lkm, elektroni_tiheys,
            V_hartree, ydin_tiheys, ytimien_lkm, ydin_tiheys]
