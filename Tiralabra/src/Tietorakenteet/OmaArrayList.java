package Tietorakenteet;

import java.util.Arrays;

// javan genericsien suunnittelija voisi hypätä sillalta alas
public class OmaArrayList<T> implements OmaList<T> {

    private Object[] data;
    private int datanKoko;
  

    /**
     * Konstruktori. Alustaa taulukolle 4 tavua tilaa
     * kannalta.
     */
    public OmaArrayList() {
        this(4);
    }
    
   
    /**
     * Konstruktori. Varaa tilaa koko-parametrin verran
     * @param koko Kuinka monelle alkiolle varataan tilaa suoraan
     */
    public OmaArrayList(int koko) {
        data = new Object[koko];
        datanKoko = 0;
    }
    
    @Override
    public int capacity() {
        return data.length;
    }
    
    @Override
    public int size() {
        return datanKoko;
    }

    @Override
    public boolean isEmpty() {
        return datanKoko == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < datanKoko; ++i) {
            if (data[i] == o) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(T e) {
        if (kapasiteettiTaynna()) {
            kasvataKokoa();
        }
        
        lisaaElementti(e);

        return true;
    }

    @Override
    public boolean addAll(OmaList<T> e) {
        if (e == null || e.isEmpty()) {
            return false;
        }

        for (int i = 0; i < e.size(); ++i) {
            add(e.get(i));
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < datanKoko; ++i) {
            if (data[i] == o) {
                remove(i);
                return true;
            }
        }

    
        return false;
    }

    @Override
    public void clear() {
        data = new Object[1];
        datanKoko = 0;

    
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= datanKoko) {
            throw new IndexOutOfBoundsException("Indeksi ulkona taulukosta");
        }
        return (T) data[index];
    }

    @Override
    public T set(int index, Object element) {
        if (index < 0 || index >= datanKoko) {
            throw new IndexOutOfBoundsException("Indeksi ulkona taulukosta");
        }

  

        T retval = (T) data[index];
        data[index] = element;
        return retval;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= datanKoko) {
            throw new IndexOutOfBoundsException("Indeksi ulkona taulukosta");
        }


        T poistettava = (T) data[index];
        for (int i = index + 1; i < datanKoko; ++i) {
            data[i - 1] = data[i];
        }
        --datanKoko;
        data[datanKoko] = null;
        return poistettava;
    }

    /**
     * Metodi joka tuplaa taulukon koon kutsuttaessa
     */
    private void kasvataKokoa() {
        Object [] uusiArray = new Object[data.length * 2];
        
        for (int i = 0; i < datanKoko; ++i) {
            uusiArray[i] = data[i];
        }
        
        data = uusiArray;
        
    }

    @Override
    public Object[] toArray() {
        Object[] paluu = new Object[datanKoko];

        for (int i = 0; i < datanKoko; ++i) {
            paluu[i] = data[i];
        }

        return paluu;
    }

    /**
     * Laskee hajautusarvon taulukon sisällön perusteella. Toteuttaa
     * Shift-add-xor-algoritmin. Lähde:
     * http://eternallyconfuzzled.com/tuts/algorithms/jsw_tut_hashing.aspx
     *
     * @return hajautusarvo
     */
    @Override
    public int hashCode() {

        int hashCode = 0;
        for (int i = 0; i < datanKoko; ++i) {
            hashCode ^= (hashCode << 5) + (hashCode >> 2) + data[i].hashCode();
        }

        return hashCode;
    }

    /**
     * Vertailee sisältävätkö listat samat objektit.<br> Kutsuu omistamiensa
     * objektien equals()-metodia
     *
     * @param obj Verrattava lista
     * @return Onko listojen sisältö sama
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        OmaArrayList<T> verrattava = (OmaArrayList<T>) obj;

        if (datanKoko != verrattava.datanKoko) {
            return false;
        }

        if (hashCode() != verrattava.hashCode()) {
            return false;
        }


        for (int i = 0; i < datanKoko; ++i) {
            if (!data[i].equals(verrattava.data[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean kapasiteettiTaynna() {
        return data.length == datanKoko;
    }

    private void lisaaElementti(T e) {
        data[datanKoko] = e;
        ++datanKoko;
    }
}
