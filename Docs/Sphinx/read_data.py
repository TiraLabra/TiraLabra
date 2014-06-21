#!/usr/bin/env python
# -*- coding: utf-8 -*-

""" Luetaan laskentaa liittyvää dataa ja palautetaan data """
from gridi import *
import string

def read_data(filename = 'alkuarvot.txt'):
    """
    Yritetään lukea alkuarvot filestä alkuarvot.txt.
    Luetaan laskentaparametreja ja gridin tietoja.
    muutetaan siten että x->y ja y->x 
    eli input tiedossa y tulee ensin koska se on matriisin 1. dimensio
    gridin koon määrittelyssä ja ytimien paikkojen määrittelyssä
    """

    infile=open(filename,'r')
    mylambda = float(infile.readline().strip().split()[0])
    n_iter = int(float(infile.readline().strip().split()[0]))
    tol = float(infile.readline().strip().split()[0])
    temperature = float(infile.readline().strip().split()[0])
    tiheydenmuutos = float(infile.readline().strip().split()[0])
    d_rho = float(infile.readline().strip().split()[0])
    askel = float(infile.readline().strip().split()[0])
    ny=int(infile.readline().strip().split()[0])
    nx=int(infile.readline().strip().split()[0])
    nz=int(infile.readline().strip().split()[0])
    elektroni_lkm=float(infile.readline().strip().split()[0])
    elektroni_tiheys = Gridi(nx=nx,ny=ny,nz=nz,init_value=elektroni_lkm)
    V_hartree = Gridi(nx=nx,ny=ny,nz=nz,init_value=0)
    ydin_tiheys = Gridi(nx=nx,ny=ny,nz=nz,init_value=0)
    ytimien_lkm=int(infile.readline().strip().split()[0])
    for i_ydin in range(ytimien_lkm):
        line=infile.readline()
        col=string.split(line)
        iy=int(col[0])
        ix=int(col[1])
        iz=int(col[2])
        z =float(col[3])
        ydin_tiheys.gridi[ix,iy,iz]=1.0/ydin_tiheys.get_volume_of_a_box()
    return [mylambda, n_iter, tol, temperature,tiheydenmuutos,d_rho, askel,
            ny, nx, nz, elektroni_lkm, elektroni_tiheys,
            V_hartree, ydin_tiheys, ytimien_lkm, ydin_tiheys]
