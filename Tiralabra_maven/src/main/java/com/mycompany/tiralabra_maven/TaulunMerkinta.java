package com.mycompany.tiralabra_maven;

/**
 * Luokka kuvaa yksittäistä merkintää avoimella hajautuksella toteutetussa
 * hajautustaulussa. Merkinnällä on avain ja arvo.
 * 
 * @author sampox
 */
public class TaulunMerkinta {
	/**
	 * Merkinnän avain
	 * 
	 */
	private String avain;
	/**
	 * Merkinnän arvo
	 * 
	 */
	private String arvo;

	/**
	 * Konstruktori luo uuden merkinnän ja asettaa merkinnälle parametrinä
	 * annetun avaimen ja arvon
	 * 
	 * @param avain
	 *            Merkinnän avain
	 * @param arvo
	 *            Merkinnän arvo
	 */
	public TaulunMerkinta(String avain, String arvo) {
		this.avain = avain;
		this.arvo = arvo;
	}

	// GETTERIT JA SETTERIT

	public String getAvain() {
		return avain;
	}

	public void setAvain(String avain) {
		this.avain = avain;
	}

	public String getArvo() {
		return arvo;
	}

	public void setArvo(String arvo) {
		this.arvo = arvo;
	}

}
