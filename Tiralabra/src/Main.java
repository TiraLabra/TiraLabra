
public class Main {

    public static void main(String[] args) {
        String testi = "ababcdddda";
        Pakkaaja p = new Pakkaaja();
        String pakattu = p.pakkaa(testi);
       
        System.out.println(pakattu);
        
        //Purkaja pur = new Purkaja();
        
        //pur.pura(pakattu);
    }
}
