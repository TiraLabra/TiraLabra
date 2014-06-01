/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package polunetsinta;

import java.util.Comparator;

/**
 *
 * @author Arvoitusmies
 */
class PriorityComparator implements Comparator<AstarKekoEntry> {

    @Override
    public int compare(AstarKekoEntry o1, AstarKekoEntry o2) {
        if (o1.getPriority() < o2.getPriority()) {
            return -1;
        } else if (o1.getPriority() > o2.getPriority()) {
            return 1;
        }
        return 0;
    }

}
