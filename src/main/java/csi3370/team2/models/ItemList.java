package csi3370.team2.models;


import java.util.Comparator;
import java.util.List;

public class ItemList {

    public static final String TYPE_COLLECTION = "COLLECTION";
    public static final String TYPE_WISHLIST = "WISHLIST";

    private int listId;
    private String name;
    private String listType;
    private String sortPreference;
    private int ownerId;
    private List<ItemListItem> items;

    public ItemList(int listId, String name, String listType, String sortPreference, int ownerId){
        this.listId = listId;
        this.name = name;
        this.listType = listType;
        this.sortPreference = sortPreference;
        this.ownerId = ownerId;
    }

    public ItemList() {
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getSortPreference() {
        return sortPreference;
    }

    public void setSortPreference(String sortPreference) {
        this.sortPreference = sortPreference;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public List<ItemListItem> getItems() {
        return items;
    }

    public void setItems(List<ItemListItem> items) {
        this.items = items;
    }

    public void applySortPreference(Comparator<ItemListItem> sortComparator) {
        if (items.isEmpty())
            return; // don't sort an empty list
        items.sort(sortComparator);
    }
}
