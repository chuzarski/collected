package csi3370.team2.util;

import csi3370.team2.models.ItemListItem;

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

    public static final NameComparator COMPARE_NAME = new NameComparator();
    public static final DateComparator COMPARE_DATE = new DateComparator();
    public static final RatingComparator COMPARE_RATING = new RatingComparator();

    HashMap<String, Comparator<ItemListItem>> comparatorLookupTable;

    public ItemListItemComparators() {
        comparatorLookupTable = new HashMap<>();

        // all of our sort methods
        comparatorLookupTable.put("RATING", ItemListItemComparators.COMPARE_RATING);
        comparatorLookupTable.put("RATING_REVERSE", ItemListItemComparators.COMPARE_RATING.reversed());
        comparatorLookupTable.put("DATE", ItemListItemComparators.COMPARE_DATE);
        comparatorLookupTable.put("DATE_REVERSE", ItemListItemComparators.COMPARE_DATE.reversed());
        comparatorLookupTable.put("NAME", ItemListItemComparators.COMPARE_NAME);
        comparatorLookupTable.put("NAME_REVERSE", ItemListItemComparators.COMPARE_NAME.reversed());
    }

    public Comparator<ItemListItem> getComparator(String sortDirection) {
        return comparatorLookupTable.get(sortDirection);
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
