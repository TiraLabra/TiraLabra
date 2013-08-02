package Tietorakenteet;

import java.util.Arrays;

// javan genericsien suunnittelija voisi hypätä sillalta alas
public class OmaArrayList<T> implements OmaList<T> {

    private Object[] data;
    private int datanKoko;

    /**
     * Konstruktori. Alustaa sisäiset rakenteet, ei mitään kummempaa käyttäjän
     * kannalta.
     */
    public OmaArrayList() {
        data = new Object[1];
        datanKoko = 0;
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
        if (data.length == datanKoko) {
            kasvataKokoa();
        }

        data[datanKoko] = e;
        ++datanKoko;
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
        data = Arrays.copyOf(data, data.length * 2);
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
     * Laskee hajautusarvon taulukon sisällön perusteella.
     *
     * @return hajautusarvo
     */
    // todo: etsi kunnon algoritmi
    @Override
    public int hashCode() {
        long paluuArvo = 0;
        for (int i = 0; i < datanKoko; ++i) {
            paluuArvo += (paluuArvo + data[i].hashCode() % Integer.MAX_VALUE) % Integer.MAX_VALUE;
        }
        
        return (int)paluuArvo;
        
    }

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

        for (int i = 0; i < datanKoko; ++i) {
            if (!data[i].equals(verrattava.data[i])) {
                return false;
            }
        }

        return true;
    }
}
