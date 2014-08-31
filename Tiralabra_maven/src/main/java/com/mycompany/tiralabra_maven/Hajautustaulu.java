package com.mycompany.tiralabra_maven;

public interface Hajautustaulu {
		public Boolean poistaMerkinta(String avain);
		public TaulunMerkinta lisaaMerkinta(String avain, String arvo);
		public TaulunMerkinta etsiMerkinta(String avain);
}
