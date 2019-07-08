package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class RegionRepository
{
    private JPAApi jpaApi;

    @Inject
    public RegionRepository(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }

    public List<Region> getList()
    {
        String sql = "SELECT r FROM Region r";
        TypedQuery<Region> query = jpaApi.em().createQuery(sql, Region.class);
        List<Region> regions = query.getResultList();
        return regions;
    }

    public void add(Region region)
    {
        jpaApi.em().persist(region);
    }

    public Region get(int regionId)
    {
        String sql = "SELECT r FROM Region r WHERE regionId = :regionId";
        TypedQuery<Region> query = jpaApi.em().createQuery(sql, Region.class);
        query.setParameter("regionId", regionId);
        Region region = query.getSingleResult();
        return region;
    }
}










