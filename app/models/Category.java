package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category
{
    @Id
    private int categoryId;
    private String categoryName;
    private byte[] picture;

    public int getCategoryId()
    {
        return categoryId;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public byte[] getPicture()
    {
        return picture;
    }
}
