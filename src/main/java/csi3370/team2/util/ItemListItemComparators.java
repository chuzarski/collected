package csi3370.team2.util;

import csi3370.team2.models.ItemListItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

/*
Sources:
https://www.javatpoint.com/Comparator-interface-in-collection-framework
https://www.javatpoint.com/how-to-compare-dates-in-java
*/
@Singleton
public class ItemListItemComparators {

    private HashMap<String, Comparator<ItemListItem>> comparatorLookupTable;
    private final Logger log = LoggerFactory.getLogger("ItemListComparators");

    public ItemListItemComparators() {
        comparatorLookupTable = new HashMap<>();

        // all of our sort methods
        comparatorLookupTable.put("RATING", new RatingComparator());
        comparatorLookupTable.put("RATING_REVERSE", new RatingComparator().reversed());
        comparatorLookupTable.put("DATE", new DateComparator());
        comparatorLookupTable.put("DATE_REVERSE", new DateComparator().reversed());
        comparatorLookupTable.put("NAME", new NameComparator());
        comparatorLookupTable.put("NAME_REVERSE", new NameComparator().reversed());
    }

    public Comparator<ItemListItem> getComparator(String sortDirection) {
        Comparator<ItemListItem> comparator;
        comparator = comparatorLookupTable.get(sortDirection);

        if (comparator == null) {
            comparator = comparatorLookupTable.get("NAME"); // houston we had a problem
            log.error("Somehow the database had a bad sort method? Replacing with default of NAME");
        }
        return comparator;
    }

    public boolean checkValidSortMethod(String sortMethod) {
        return comparatorLookupTable.containsKey(sortMethod);
    }

    public Set<String> getValidSortMethods() {
        return comparatorLookupTable.keySet();
    }


    static class NameComparator implements Comparator<ItemListItem> {

        @Override
        public int compare(ItemListItem o1, ItemListItem o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    static class DateComparator implements Comparator<ItemListItem> {

        @Override
        public int compare(ItemListItem o1, ItemListItem o2) {
            if (o1.getReleaseDate().compareTo(o2.getReleaseDate()) == 0)
            {
                return 0;
            } else if (o1.getReleaseDate().compareTo(o2.getReleaseDate()) > 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    static  class RatingComparator implements Comparator<ItemListItem>{

        @Override
        public int compare(ItemListItem o1, ItemListItem o2) {
            if(o1.getRating() == o2.getRating())
                return 0;
            else if(o1.getRating() > o2.getRating())
                return 1;
            else
                return -1;
        }
    }
}
