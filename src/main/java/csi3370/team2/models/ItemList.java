package csi3370.team2.models;

import com.nimbusds.jose.shaded.json.JSONUtil;

import java.util.List;

public class ItemList {

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
}
