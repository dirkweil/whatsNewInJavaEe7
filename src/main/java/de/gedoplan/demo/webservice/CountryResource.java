package de.gedoplan.demo.webservice;

import de.gedoplan.demo.persistence.Country;
import de.gedoplan.demo.persistence.CountryRepository;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

@Path("country")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class CountryResource
{
  @Inject
  CountryRepository countryRepository;

  @GET
  public List<Country> getAll()
  {
    return this.countryRepository.findAll();
  }

  @GET
  @Path("{id}")
  public Country getById(@PathParam("id") String id)
  {
    Country country = this.countryRepository.findById(id);
    if (country != null)
    {
      return country;
    }

    throw new WebApplicationException(Status.NOT_FOUND);
  }

}
