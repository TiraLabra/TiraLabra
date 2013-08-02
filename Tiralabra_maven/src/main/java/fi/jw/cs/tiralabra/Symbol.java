package fi.jw.cs.tiralabra;

import java.util.Comparator;
import java.util.List;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-02
 */
public class Symbol {
    private char character;
    private int weight;
    private List<Boolean> code;

    public Symbol(char character, int weight) {
        this.character = character;
        this.weight = weight;
    }


    public void increaseWeight() {
        this.weight++;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Boolean> getCode() {
        return code;
    }

    public void setCode(List<Boolean> code) {
        this.code = code;
    }

    public static Comparator<Symbol> getComparator() {
        return new SymbolWeightComparator();
    }

    private static class SymbolWeightComparator implements Comparator<Symbol> {

        @Override
        public int compare(Symbol o1, Symbol o2) {
            return o1.getWeight() - o2.getWeight();
        }
    }

}

