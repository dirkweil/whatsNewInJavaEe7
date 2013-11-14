package de.gedoplan.demo.persistence;

import de.gedoplan.demo.interceptor.TraceCall;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
@TraceCall
public class PublisherRepository
{
  @PersistenceContext
  EntityManager entityManager;

  @Transactional
  public Publisher save(Publisher publisher)
  {
    return this.entityManager.merge(publisher);
  }

  public List<Publisher> findAll()
  {
    return findAll(null);
  }

  public List<Publisher> findAll(String fetchgraphName)
  {
    TypedQuery<Publisher> query = this.entityManager.createQuery("select x from Publisher x", Publisher.class);
    if (fetchgraphName != null)
    {
      EntityGraph<?> entityGraph = this.entityManager.getEntityGraph(fetchgraphName);
      if (entityGraph != null)
      {
        query.setHint("javax.persistence.fetchgraph", entityGraph);
      }
    }
    return query.getResultList();
  }
}
