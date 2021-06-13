package csi3370.team2.util;

import csi3370.team2.models.ItemDescription;
import csi3370.team2.models.ItemListItem;

import java.util.Comparator;

/*
Sources:
https://www.javatpoint.com/Comparator-interface-in-collection-framework
https://www.javatpoint.com/how-to-compare-dates-in-java
*/

public class ItemListItemComparators {

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
