package models;

import java.util.Date;

public class ItemDescription {

    protected String name;
    protected String type;
    protected String description;
    protected Date releaseDate;

    public ItemDescription(String name, String type, String description, Date releaseDate)
    {
        this.name = name;
        this.type = type;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public void setName(String name){
        this.name = name;
    }

    /* ex: Collections Edition, Standard */
    public void setType(String type){
        this.type = type;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setReleaseDate(Date releaseDate){
        this.releaseDate = releaseDate;
    }

}
