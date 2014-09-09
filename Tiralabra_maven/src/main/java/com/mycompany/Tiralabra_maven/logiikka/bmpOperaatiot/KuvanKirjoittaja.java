package com.mycompany.Tiralabra_maven.logiikka.bmpOperaatiot;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;
import com.mycompany.Tiralabra_maven.logiikka.paikkaPino.OmaPinoAlkionaPaikka;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.util.Stack;
import javax.imageio.ImageIO;

/**
 * Luokka, jonka avulla tulokset (reitti ja läpikäydyt paikat) kirjoitetaan
 * bmp-tiedostoon.
 */
public class KuvanKirjoittaja {

    private BufferedImage image;

    /**
     * Metodi, joka kirjoittaa tulokset (reitti ja läpikäydyt paikat)
     * bmp-tiedostoon.
     *
     * @param BMPFileName Lähtötietokuvan nimi. Esim. "kartta1.bmp"
     *
     * @param outputFileEnding Lähtötietokuvan nimen perään liitettävä teksti
     * tulostietokuvan nimen muodostamiseksi. Esim. kun
     * outputFileEnding="TULOS", saadaan tulostietokuvan nimeksi
     * "kartta1TULOS.bmp"
     *
     * @param kaydytPaikat Pino Paikka-olioita, joissa on käyty eli joiden
     * etäisyys lähtopisteestä on laskettu algoritmin suorittamisen aikana.
     *
     * @param reittiPino Pino Paikka-olioita, jotka muodostavat nopeimman reitin
     * lähtöpisteestä maalipisteeseen.
     */
    public void writeImage(String BMPFileName, String outputFileEnding, OmaPinoAlkionaPaikka kaydytPaikat, OmaPinoAlkionaPaikka reittiPino) {

        File outputfile = new File(BMPFileName.substring(0, BMPFileName.length() - 4) + outputFileEnding + ".bmp");

        try {
            this.image = ImageIO.read(new File(BMPFileName));
        } catch (IOException e) {
            System.out.println("virhe");
        }

        int color;
        Paikka paikkaK;
        while (!kaydytPaikat.stackIsEmpty()) {
            paikkaK = kaydytPaikat.stackPop();
            color = image.getRGB(paikkaK.i, paikkaK.j);
            if (color == Color.WHITE.getRGB()) { //helppokulkuinen maasto (valkoinen) jossa on käyty
                image.setRGB(paikkaK.i, paikkaK.j, Color.YELLOW.getRGB());
            } else if (color == Color.GRAY.getRGB()) { //vaikeakulkuinen maasto (harmaa) jossa kuitenkin on käyty
                image.setRGB(paikkaK.i, paikkaK.j, Color.ORANGE.getRGB());
            }
        }

        Paikka paikkaU;
        while (!reittiPino.stackIsEmpty()) {
            paikkaU = reittiPino.stackPop();
            image.setRGB(paikkaU.i, paikkaU.j, Color.BLUE.getRGB());

        }

        try {
            ImageIO.write(image, "bmp", outputfile);
        } catch (IOException e) {
        }
    }
}
