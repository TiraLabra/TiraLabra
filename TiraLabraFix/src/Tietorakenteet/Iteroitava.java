/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tietorakenteet;

   /**
     *
     * Tämä metodi toimii Keon alkioiden rajapintana. Tässä on lueteltu ne asiat jotka alkiolta vaaditaan, jotta se pystyisi olemaan Keossa.
     */
public interface Iteroitava {
  
  public void asetaArvo(double d);  
  public int vertausoperaatio(Iteroitava toinen);
  public int sijaintiKeossa();
  public void asetaSijainti(int i);
}