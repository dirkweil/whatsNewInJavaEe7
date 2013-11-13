package de.gedoplan.demo.persistence;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = Country.TABLE_NAME)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Country
{
  public static final String TABLE_NAME = "JPA_COUNTRY";

  @Id
  @Column(name = "ISO_CODE", length = 2)
  private String             isoCode;

  @Column(name = "NAME")
  private String             name;

  @Column(name = "PHONE_PREFIX", length = 5)
  private String             phonePrefix;

  //  @Enumerated(EnumType.STRING)
  //  @Convert(disableConversion = true)
  private Continent          continent;

  @Convert(converter = YesNoConverter.class)
  private boolean            expired;

  public Country(String isoCode, String name, String phonePrefix, Continent continent, boolean expired)
  {
    this.isoCode = isoCode;
    this.name = name;
    this.phonePrefix = phonePrefix;
    this.continent = continent;
    this.expired = expired;
  }

  public Country()
  {
  }

  public String getIsoCode()
  {
    return this.isoCode;
  }

  public void setIsoCode(String isoCode)
  {
    this.isoCode = isoCode;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getPhonePrefix()
  {
    return this.phonePrefix;
  }

  public void setPhonePrefix(String phonePrefix)
  {
    this.phonePrefix = phonePrefix;
  }

  public Continent getContinent()
  {
    return this.continent;
  }

  public void setContinent(Continent continent)
  {
    this.continent = continent;
  }

  public boolean isExpired()
  {
    return this.expired;
  }

  public void setExpired(boolean expired)
  {
    this.expired = expired;
  }

}
