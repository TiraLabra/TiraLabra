package com.mycompany.tiralabra_maven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Tässä luokassa testataan ja verrataan hajautustaulujen suorituskykyä
 * 
 */
public class App {
	public static void main(String[] args) {

		while (true) {
			/**
			 * Testattavat hajautustaulut
			 * 
			 */
			Hajautustaulu[] hajikset = new Hajautustaulu[3];
			hajikset[0] = new HajautustauluLinkitetyllaListalla();
			hajikset[1] = new HajautustauluAvoimellaHajautuksella(true);
			hajikset[2] = new HajautustauluAvoimellaHajautuksella(false);

			/**
			 * Testitulokset kirjataan tähän taulukkoon
			 */
			String[] tulokset = new String[4];
			tulokset[0] = "Hajautustaulu ylivuotolistoilla";
			tulokset[1] = "Avoin hajautus (lineearinen)";
			tulokset[2] = "Avoin hajautus (neliöinen)";
			tulokset[3] = "\t\t\t\tLisäys\tEtsintä(tuloksellinen)\tEtsinta(Tulokseton)\tPoisto";
			Scanner lukija = new Scanner(System.in);
			// final String sallitutMerkit ="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			// Random randomi = new Random();

			System.out
					.println("Ohjelma testaa erilaisten hajautustaulujen suorituskykyä erikokoisilla alphanumeerisilla syötteillä.");
			System.out
					.println("Alkiot lisätään/etsitään/poistetaan hajautustauluista kokonaisuudessaan.");
			System.out.println("Valitse testisyötteen koko:");
			System.out.println("1	100 alkiota");
			System.out.println("2	1000 alkiota");
			System.out.println("3	5000 alkiota");
			System.out.println("4	10000 alkiota");
			System.out.println("5	exit");

			int testisyotteenKoko = palautaValinta(5, lukija);

			if (testisyotteenKoko == 1) {
				testisyotteenKoko = 100;
			} else if (testisyotteenKoko == 2) {
				testisyotteenKoko = 1000;
			} else if (testisyotteenKoko == 3) {
				testisyotteenKoko = 5000;
			} else if (testisyotteenKoko == 4) {
				testisyotteenKoko = 10000;
			} else if (testisyotteenKoko == 5) {
				break;
			}

			// LISÄYS
			for (int k = 0; k < 3; k++) {
				long aikaAlussa = System.currentTimeMillis();
				for (int i = 0; i < testisyotteenKoko; i++) {
					hajikset[k].lisaaMerkinta("b" + i, "b" + i);
				}
				long aikaLopussa = System.currentTimeMillis();
				tulokset[k] = tulokset[k] + "\t" + (aikaLopussa - aikaAlussa)
						+ "ms.";
			}

			// ETSINTÄ
			for (int k = 0; k < 3; k++) {
				long aikaAlussa = System.currentTimeMillis();
				for (int i = 0; i < testisyotteenKoko; i++) {
					hajikset[k].etsiMerkinta("b" + i);
				}
				long aikaLopussa = System.currentTimeMillis();
				tulokset[k] = tulokset[k] + "\t\t" + (aikaLopussa - aikaAlussa)
						+ "ms.";

			}

			// ETSINTÄ (TULOKSETON)
			for (int k = 0; k < 3; k++) {
				long aikaAlussa = System.currentTimeMillis();
				for (int i = 0; i < testisyotteenKoko; i++) {
					hajikset[k].etsiMerkinta("" + i);
				}
				long aikaLopussa = System.currentTimeMillis();
				tulokset[k] = tulokset[k] + "\t\t\t"
						+ (aikaLopussa - aikaAlussa) + "ms.";

			}

			// POISTO
			for (int k = 0; k < 3; k++) {
				long aikaAlussa = System.currentTimeMillis();
				for (int i = 0; i < testisyotteenKoko; i++) {
					hajikset[k].poistaMerkinta("b" + i);
				}
				long aikaLopussa = System.currentTimeMillis();
				tulokset[k] = tulokset[k] + "\t\t" + (aikaLopussa - aikaAlussa)
						+ "ms.";

			}

			for (int i = 3; i >= 0; i--) {
				System.out.println(tulokset[i]);
			}
			System.out.println();
		}
	}

	public static int palautaValinta(int vaihtoehtojenMaara, Scanner lukija) {
		while (true)
			try {
				int valinta = Integer.parseInt(lukija.nextLine());
				if (valinta > 0 && valinta <= vaihtoehtojenMaara) {
					return valinta;
				} else {
					System.out
							.println("Virheellinen syöte. Syötä vain valintaa vastaava numero.");
				}
			} catch (NumberFormatException nfe) {
				System.out
						.println("Virheellinen syöte. Syötä vain valintaa vastaava numero.");
			}
	}

	/*
	 * public static String luoMerkkijono(int pituus, String sallitutMerkit,
	 * Random randomi) { StringBuilder builderi = new StringBuilder(pituus); for
	 * (int i = 0; i < pituus; i++)
	 * builderi.append(sallitutMerkit.charAt(randomi
	 * .nextInt(sallitutMerkit.length()))); return builderi.toString(); }
	 */
}
