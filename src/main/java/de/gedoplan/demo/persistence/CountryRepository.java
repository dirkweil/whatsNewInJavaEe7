package de.gedoplan.demo.persistence;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
public class CountryRepository
{
  @PersistenceContext
  EntityManager entityManager;

  @Transactional
  public Country save(Country country)
  {
    return this.entityManager.merge(country);
  }

  public List<Country> findAll()
  {
    TypedQuery<Country> query = this.entityManager.createQuery("select x from Country x", Country.class);
    return query.getResultList();
  }

  public Object[] readDbValueForContinentAndExpired(String isoCode)
  {
    Query query = this.entityManager.createNativeQuery("select CONTINENT, EXPIRED from " + Country.TABLE_NAME + " where ISO_CODE=?");
    query.setParameter(1, isoCode);
    return (Object[]) query.getSingleResult();
  }

  public Country findById(String id)
  {
    return this.entityManager.find(Country.class, id);
  }

}
