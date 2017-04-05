package ie.cit.architect.protracker.comparators;

import java.util.Comparator;

/**
 * Created by brian on 05/04/17.
 */
public class AlphabeticalComparator implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
