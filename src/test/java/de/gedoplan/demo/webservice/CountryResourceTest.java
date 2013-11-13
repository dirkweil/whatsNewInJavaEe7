package de.gedoplan.demo.webservice;

import de.gedoplan.demo.persistence.Country;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Test;

public class CountryResourceTest
{
  private static final String serverUrl            = "http://localhost:8080";
  private static final String serverUrlWebContext  = serverUrl + "/whats-new-in-javaee";
  private static final String serverUrlRestContext = serverUrlWebContext + "/rest";

  @Test
  public void testGetById()
  {
    String countryIsoCode = "DE";

    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(serverUrlRestContext + "/country/" + countryIsoCode);
    Country country = target.request(MediaType.APPLICATION_XML).get(Country.class);

    Assert.assertNotNull("Country should not be null", country);
    Assert.assertEquals("Country ID", countryIsoCode, country.getIsoCode());
  }

  @Test(expected = NotFoundException.class)
  public void testGetByIdNotFound()
  {
    String countryIsoCode = "xx";

    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(serverUrlRestContext + "/country/" + countryIsoCode);
    target.request(MediaType.APPLICATION_XML).get(Country.class);
  }
}
