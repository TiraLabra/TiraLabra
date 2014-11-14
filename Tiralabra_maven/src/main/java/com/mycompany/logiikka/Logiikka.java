package com.mycompany.logiikka;

import com.mycompany.domain.Kasi;

public class Logiikka {
    
    private Kasi pelaaja;
    private Kasi tekoAly;
    //private Kasi pelaajanViimeisinKasi;
    
    public Logiikka() {
        this.pelaaja = null;
        this.tekoAly = null;
    }
    
    public Kasi pelaajanViimeisinKasi() {
        return this.pelaaja;
    }
    
    public void setPelaajanKasi(Kasi k) {
        this.pelaaja = k;
    }
    
    public Kasi getPelaajanKasi() {
        return this.pelaaja;
    }
    
    public void setTekoalynKasi(Kasi k) {
        this.tekoAly = k;
    }
        
    public int pelaajaVoittaaKierroksen() {
        if(this.pelaaja.getNimi().equals("KIVI")) {
            if (this.tekoAly.getNimi().equals("KIVI")) {
                return 0;
            } else if (this.tekoAly.getNimi().equals("PAPERI")) {
                return -1;
            } else if (this.tekoAly.getNimi().equals("SAKSET")) {
                return 1;
            } else if (this.tekoAly.getNimi().equals("LISKO")) {
                return 1;
            } else {
                return -1;
            }
        } else if (this.pelaaja.getNimi().equals("PAPERI")) {
            if (this.tekoAly.getNimi().equals("KIVI")) {
                return 1;
            } else if (this.tekoAly.getNimi().equals("PAPERI")) {
                return 0;
            } else if (this.tekoAly.getNimi().equals("SAKSET")) {
                return -1;
            } else if (this.tekoAly.getNimi().equals("LISKO")) {
                return -1;
            } else {
                return 1;
            }
        } else if (this.pelaaja.getNimi().equals("SAKSET")) {
            if (this.tekoAly.getNimi().equals("KIVI")) {
                return -1;
            } else if (this.tekoAly.getNimi().equals("PAPERI")) {
                return 1;
            } else if (this.tekoAly.getNimi().equals("SAKSET")) {
                return 0;
            } else if (this.tekoAly.getNimi().equals("LISKO")) {
                return 1;
            } else {
                return -1;
            }
        } else if (this.pelaaja.getNimi().equals("LISKO")) {
            if (this.tekoAly.getNimi().equals("KIVI")) {
                return -1;
            } else if (this.tekoAly.getNimi().equals("PAPERI")) {
                return 1;
            } else if (this.tekoAly.getNimi().equals("SAKSET")) {
                return -1;
            } else if (this.tekoAly.getNimi().equals("LISKO")) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (this.tekoAly.getNimi().equals("KIVI")) {
                return 1;
            } else if (this.tekoAly.getNimi().equals("PAPERI")) {
                return -1;
            } else if (this.tekoAly.getNimi().equals("SAKSET")) {
                return 1;
            } else if (this.tekoAly.getNimi().equals("LISKO")) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
