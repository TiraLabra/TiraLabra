/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tietorakenteet;

import java.util.ArrayList;

/**
 *
 * @author Serafim
 */
public class Monikulmio {
    
    private ArrayList<Kordinaatti> kulmat;
    
    public Monikulmio(ArrayList<Kordinaatti> kordinaatit)
    {
    this.kulmat = kordinaatit;
    }
    
    public ArrayList<Kordinaatti> palautaKulmat()
    {
    return this.kulmat;
    }
    
    
}
