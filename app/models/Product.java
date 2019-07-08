package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String productName;
    private int categoryId;
    private int supplierId;
    private BigDecimal unitPrice;
    private int unitsInStock;
    private int unitsOnOrder;
    private int reorderLevel;
    private boolean discontinued;

    public int getProductId()
    {
        return productId;
    }

    public String getProductName()
    {
        return productName;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public int getSupplierId()
    {
        return supplierId;
    }

    public BigDecimal getUnitPrice()
    {
        return unitPrice;
    }

    public int getUnitsInStock()
    {
        return unitsInStock;
    }

    public int getUnitsOnOrder()
    {
        return unitsOnOrder;
    }

    public int getReorderLevel()
    {
        return reorderLevel;
    }

    public boolean isDiscontinued()
    {
        return discontinued;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public void setSupplierId(int supplierId)
    {
        this.supplierId = supplierId;
    }

    public void setUnitPrice(BigDecimal unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public void setUnitsInStock(int unitsInStock)
    {
        this.unitsInStock = unitsInStock;
    }

    public void setUnitsOnOrder(int unitsOnOrder)
    {
        this.unitsOnOrder = unitsOnOrder;
    }

    public void setReorderLevel(int reorderLevel)
    {
        this.reorderLevel = reorderLevel;
    }

    public void setDiscontinued(boolean discontinued)
    {
        this.discontinued = discontinued;
    }
}
