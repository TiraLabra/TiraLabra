package com.mycompany.tiralabra_maven;
/**
 * Rajapinta, jonka hajautustaulut toteuttavat
 * 
 * @author sampox
 */
public interface Hajautustaulu {
		public Boolean poistaMerkinta(String avain);
		public TaulunMerkinta lisaaMerkinta(String avain, String arvo);
		public TaulunMerkinta etsiMerkinta(String avain);

}
