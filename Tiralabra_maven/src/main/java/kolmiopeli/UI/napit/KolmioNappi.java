package kolmiopeli.UI.napit;

import kolmiopeli.domain.Kolmio;

/**
 *
 * @author Eemi
 */
public interface KolmioNappi {

    public boolean isOnkoValittuna();

    public Kolmio getNapinKolmio();

    public void setOnkoValittuna(boolean onkoValittuna);
}
