package com.mycompany.Tiralabra_maven.logiikka.testausta;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;
import com.mycompany.Tiralabra_maven.logiikka.keko.OmaKeko;
import java.util.ArrayList;

/**
 *
 * @author Hannu
 */
public class OmaKekoEtyyppiTestausta {
    
    private OmaKeko<Paikka> minunKeko;
//    private Object objekti =new Paikka(1, 1, 1);
    private Paikka paikkaTyyppi=new Paikka();
    private ArrayList<Paikka> arraylist=new ArrayList<>();
    
    public OmaKekoEtyyppiTestausta(){
//        this.minunKeko=new OmaKeko(objekti.getClass());
        this.minunKeko=new OmaKeko(paikkaTyyppi.getClass());
    }
    
    public void testing(){
        minunKeko.setTest(new Paikka(1, 11, 111), 1);
        minunKeko.setTest(new Paikka(2, 22, 222), 2);
        Paikka paikka=this.minunKeko.getTest(1);
        System.out.println("i: "+paikka.i+", j:"+paikka.j+", aikakustannus:"+paikka.aikaKustannus);
        paikka=this.minunKeko.getTest(2);
        System.out.println("i: "+paikka.i+", j:"+paikka.j+", aikakustannus:"+paikka.aikaKustannus);

        for (int i=1;i<=10;i++){
        minunKeko.setTest(new Paikka(i, 10*i, 100*i), i);
        paikka=this.minunKeko.getTest(i);
        System.out.println("i: "+paikka.i+", j:"+paikka.j+", aikakustannus:"+paikka.aikaKustannus);
        }
        
//        Paikka u=new Paikka();
//        u.
    }
    
}
