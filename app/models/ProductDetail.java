package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ProductDetail
{
    @Id
    private int productId;
    private String productName;
    private BigDecimal unitPrice;
    private String categoryName;

    public ProductDetail(int productId, String productName, BigDecimal unitPrice, String categoryName)
    {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.categoryName = categoryName;
    }

    public int getProductId()
    {
        return productId;
    }

    public String getProductName()
    {
        return productName;
    }

    public BigDecimal getUnitPrice()
    {
        return unitPrice;
    }

    public String getCategoryName()
    {
        return categoryName;
    }
}
