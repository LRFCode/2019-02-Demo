package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductRepository
{
    private JPAApi jpaApi;

    @Inject
    public ProductRepository(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }

    public List<Product> getList()
    {
        String sql = "SELECT p FROM Product p ORDER BY p.productName";
        TypedQuery<Product> query = jpaApi.em().createQuery(sql, Product.class);
        List<Product> products = query.getResultList();
        return products;
    }

    public List<Product> getList(String searchName)
    {
        String searchValue = searchName;

        if (searchValue == null)
        {
            searchValue = "";
        }

        if (searchValue.length() <= 1)
        {
            searchValue = searchValue + "%";
        }
        else
        {
            searchValue = "%" + searchValue + "%";
        }

        String sql = "SELECT p " +
                "FROM Product p " +
                "WHERE p.productName || p.categoryId LIKE :searchName " +
                "ORDER BY p.productName";
        TypedQuery<Product> query = jpaApi.em().createQuery(sql, Product.class);
        query.setParameter("searchName", searchValue);
        List<Product> products = query.getResultList();
        return products;
    }

    public List<ProductDetail> getDetailList(String searchName)
    {
        String searchValue = searchName;

        if (searchValue == null)
        {
            searchValue = "";
        }

        if (searchValue.length() <= 1)
        {
            searchValue = searchValue + "%";
        }
        else
        {
            searchValue = "%" + searchValue + "%";
        }

        String sql = "SELECT NEW ProductDetail(p.productId, p.productName, p.unitPrice, c.categoryName) " +
                "FROM Product p " +
                "JOIN Category c ON p.categoryId = c.categoryId " +
                "WHERE p.productName || p.categoryId LIKE :searchName " +
                "ORDER BY p.productName";
        TypedQuery<ProductDetail> query = jpaApi.em().createQuery(sql, ProductDetail.class);
        query.setParameter("searchName", searchValue);
        List<ProductDetail> products = query.getResultList();
        return products;
    }

    public Product get(int productId)
    {
        String sql = "SELECT p FROM Product p WHERE productId = :productId";
        TypedQuery<Product> query = jpaApi.em().createQuery(sql, Product.class);
        query.setParameter("productId", productId);
        return query.getSingleResult();
    }
}






