package de.gedoplan.demo.presentation;

import de.gedoplan.demo.persistence.Continent;
import de.gedoplan.demo.persistence.Country;
import de.gedoplan.demo.persistence.CountryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class CountryModel
{
  @Inject
  CountryRepository              countryRepository;

  private List<Country>          countries;

  private Map<Country, Object[]> dbValues;

  @PostConstruct
  void postConstruct()
  {
    this.dbValues = new HashMap<>();
    this.countries = this.countryRepository.findAll();
    for (Country country : this.countries)
    {
      this.dbValues.put(country, this.countryRepository.readDbValueForContinentAndExpired(country.getIsoCode()));
    }
  }

  public List<Country> getCountries()
  {
    return this.countries;
  }

  public String getDbValueForContinent(Country country)
  {
    return (String) this.dbValues.get(country)[0];
  }

  public String getDbValueForExpired(Country country)
  {
    return (String) this.dbValues.get(country)[1];
  }

  public void createSomeCounties()
  {
    this.countryRepository.save(new Country("CA", "Canada", "1", Continent.NORTH_AMERICA, false));
    this.countryRepository.save(new Country("CN", "China", "86", Continent.ASIA, false));
    this.countryRepository.save(new Country("DE", "Germany", "49", Continent.EUROPE, false));
    this.countryRepository.save(new Country("US", "United States of America", "1", Continent.NORTH_AMERICA, false));
    this.countryRepository.save(new Country("YU", "Yugoslavia", null, Continent.EUROPE, true));

    postConstruct();
  }

}
