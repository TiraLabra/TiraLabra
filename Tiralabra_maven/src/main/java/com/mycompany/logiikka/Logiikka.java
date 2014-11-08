package com.mycompany.logiikka;

/**
 * Luokkaa huolehtii pelimekaniikasta
 */

public class Logiikka {
    
    public Logiikka() {
        
    }
    
    
    /**
     * Metodi palauttaa kokonaislukuarvon, joka kuvaa
     * voittaako pelaajan käsi tekoälyn
     * 
     * @param pelaaja Pelaajan käsi
     * @param tekoaly Tietokoneen käsi
     * 
     * @return 
     * 1=voitto, -1=häviö, 0=tasapeli
     */
    public int voittaako(Kasi pelaaja, Kasi tekoaly) {
        if (pelaaja.getNimi().equals(tekoaly.getNimi())) {
            return 0;
        }
        if (pelaaja.voittaako(tekoaly)) {
            return 1;
        } else {
            return -1;
        }
    }
    
//    public int voittaako(Kasi kasi1, Kasi kasi2) {
//        if(kasi1.getNimi().equals("KIVI")) {
//            if (kasi2.getNimi().equals("KIVI")) {
//                return 0;
//            } else if (kasi2.getNimi().equals("PAPERI")) {
//                return -1;
//            } else if (kasi2.getNimi().equals("SAKSET")) {
//                return 1;
//            } else if (kasi2.getNimi().equals("LISKO")) {
//                return 1;
//            } else {
//                return -1;
//            }
//        } else if (kasi1.getNimi().equals("PAPERI")) {
//            if (kasi2.getNimi().equals("KIVI")) {
//                return 1;
//            } else if (kasi2.getNimi().equals("PAPERI")) {
//                return 0;
//            } else if (kasi2.getNimi().equals("SAKSET")) {
//                return -1;
//            } else if (kasi2.getNimi().equals("LISKO")) {
//                return -1;
//            } else {
//                return 1;
//            }
//        } else if (kasi1.getNimi().equals("SAKSET")) {
//            if (kasi2.getNimi().equals("KIVI")) {
//                return -1;
//            } else if (kasi2.getNimi().equals("PAPERI")) {
//                return 1;
//            } else if (kasi2.getNimi().equals("SAKSET")) {
//                return 0;
//            } else if (kasi2.getNimi().equals("LISKO")) {
//                return 1;
//            } else {
//                return -1;
//            }
//        } else if (kasi1.getNimi().equals("LISKO")) {
//            if (kasi2.getNimi().equals("KIVI")) {
//                return -1;
//            } else if (kasi2.getNimi().equals("PAPERI")) {
//                return 1;
//            } else if (kasi2.getNimi().equals("SAKSET")) {
//                return -1;
//            } else if (kasi2.getNimi().equals("LISKO")) {
//                return 0;
//            } else {
//                return 1;
//            }
//        } else {
//            if (kasi2.getNimi().equals("KIVI")) {
//                return 1;
//            } else if (kasi2.getNimi().equals("PAPERI")) {
//                return -1;
//            } else if (kasi2.getNimi().equals("SAKSET")) {
//                return 1;
//            } else if (kasi2.getNimi().equals("LISKO")) {
//                return -1;
//            } else {
//                return 0;
//            }
//        }
//    }
}
