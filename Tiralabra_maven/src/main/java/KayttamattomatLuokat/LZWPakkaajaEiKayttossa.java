//package LZW;
//
//import Apuvalineet.BinaariMuuntaja;
//import Tietorakenteet.HajautusTaulu;
//
//public class LZWPakkaaja {
//    private BinaariMuuntaja muuntaja;
//    private HajautusTaulu esitykset;
//    private String teksti;
//    
//    public LZWPakkaaja(LZWLukija lukija) throws IllegalStateException {
//        this.esitykset = lukija.getAsciiKoodisto();
//        
//        String text = lukija.getTeksti();
//        if (text.isEmpty()) {
//            throw new IllegalStateException("Luettu tiedosto oli tyhjä.\nPakkausta ei muodosteta.");
//        }
//        
//        this.teksti = text;
//    }
//    
//    /**
//     * Palauttaa pakattavan tekstin.
//     * Muotoa: "lisätyt etunollat" (1 tavu) + "pakattu teksti" (heksakoodina).
//     * @return 
//     */
//    
//    public String pakattavaTeksti() {
//        StringBuilder pakattavaTeksti = new StringBuilder();
//        String pakattuna = pakattuna(ilmanEtuNollia());
//        
//        pakattavaTeksti.append((char) muuntaja.getLisatytEtuNollat());
//        pakattavaTeksti.append(pakattuna);
//        
//        return pakattavaTeksti.toString();
//    }
//    
//    protected String ilmanEtuNollia() {
//        StringBuilder ykkosinaJaNollina = new StringBuilder();
//        StringBuilder seuraava = new StringBuilder();
//        
//        for (int i = 0; i < teksti.length(); i++) {
//            char merkki = teksti.charAt(i);
//            
//            if (seuraavaaMerkkijonoaEiOle(seuraava, merkki)) {
//                ykkosinaJaNollina.append(bittiJono(seuraava));
//                seuraava = new StringBuilder();
//            }
//            seuraava.append(merkki);
//        }
//        ykkosinaJaNollina.append(bittiJono(seuraava));
//        return ykkosinaJaNollina.toString();
//    }
//    
//    protected String bittiJono(StringBuilder seuraava) {
//        return esitykset.getArvo(seuraava.toString());
//    }
//    
//    protected boolean seuraavaaMerkkijonoaEiOle(StringBuilder seuraava, char merkki) {
//        return ! esitykset.sisaltaaAvaimen(seuraava.toString() + merkki);
//    }
//    
//    protected String pakattuna(String ilmanEtuNollia) {
//        this.muuntaja = new BinaariMuuntaja();
//        String ykkosinaJaNollina = muuntaja.lisaaEtuNollat(ilmanEtuNollia);
//        
//        return muuntaja.pakatuksiTekstiksi(ykkosinaJaNollina);
//    }
//}
