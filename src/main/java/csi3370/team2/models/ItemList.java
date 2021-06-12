package csi3370.team2.models;

import com.nimbusds.jose.shaded.json.JSONUtil;

public class ItemList {

    private String name;
    private String listType;
    private String sortPreference;
    private int ownerId;

    public ItemList(String name, String listType, String sortPreference, int ownerId){
        this.name = name;
        this.listType = listType;
        this.sortPreference = sortPreference;
        this.ownerId = ownerId;
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
}
