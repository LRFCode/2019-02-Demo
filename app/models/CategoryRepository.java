package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryRepository
{
    private JPAApi jpaApi;

    @Inject
    public CategoryRepository(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }

    public List<Category> getList()
    {
        String sql = "SELECT c FROM Category c ORDER BY categoryName";
        TypedQuery<Category> query = jpaApi.em().createQuery(sql, Category.class);
        return query.getResultList();
    }
}
