package kolmiopeli.logiikka.tiralabraAlgoritmit;

/**
 *
 * @author Eemi
 */
class RomahduttajaDebugViestit {

    private boolean onkoPaalla;

    public RomahduttajaDebugViestit(boolean debugViestitPaalla) {
        this.onkoPaalla = debugViestitPaalla;
    }

    public void setOnkoPaalla(boolean onkoPaalla) {
        this.onkoPaalla = onkoPaalla;
    }

    void kolmioRomahtaa(int tyhjaRivi, int tyhjaSarake, int romahtavanRivi, int romahtavanSarake) {
        if (onkoPaalla) {

            System.out.println("{" + romahtavanRivi + ", " + romahtavanSarake + "} --> {" + tyhjaRivi + ", " + tyhjaSarake + "}");
        }
    }

    void viivoja() {
        if (onkoPaalla) {
            System.out.println("--------------------");
        }
    }
}
