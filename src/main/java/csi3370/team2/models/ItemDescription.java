package csi3370.team2.models;

import java.util.Date;

public class ItemDescription {

    protected String name;
    protected String type;
    protected String description;
    protected Date releaseDate;

    public ItemDescription() {
    }

    public ItemDescription(String name, String type, String description, Date releaseDate)
    {
        this.name = name;
        this.type = type;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
