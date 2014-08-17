/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tietorakenteet;

import Tietorakenteet.Kordinaatti;
import java.util.ArrayList;

/**
 *
 * Monikulmio luokka
 */
public class Monikulmio {
    
    private ArrayList<Kordinaatti> kulmat;
    
    /**
 *
 * Luo uuden monikulmion parametreinä kordinaattien lista.
 */
    
    public Monikulmio(ArrayList<Kordinaatti> kordinaatit)
    {
    this.kulmat = kordinaatit;
    }
    
     /**
 *
 * Palauttaakulmat
 * @return ArrayList<Kordinaatti> kordinaatit
 */
    
    public ArrayList<Kordinaatti> palautaKulmat()
    {
    return this.kulmat;
    }
    
    
}
