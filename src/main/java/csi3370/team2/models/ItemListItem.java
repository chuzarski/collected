package csi3370.team2.models;

import java.util.Date;

public class ItemListItem extends ItemDescription {

    private int itemId;
    private int rating;
    private int memberListId;

    public ItemListItem(int itemId, String name, String type, String description, Date releaseDate, int rating, int parentList) {
        super(name, type, description, releaseDate);
        this.itemId = itemId;
        this.rating = rating;
        this.memberListId = parentList;
    }



    public ItemListItem(String name, String type, String description, Date releaseDate, int rating, int parentList) {
        super(name, type, description, releaseDate);
        this.memberListId = parentList;
        this.rating = rating;
    }



    public ItemListItem() {
        super(null, null, null, null);
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getMemberListId() {
        return memberListId;
    }

    public void setMemberListId(int memberListId) {
        this.memberListId = memberListId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
