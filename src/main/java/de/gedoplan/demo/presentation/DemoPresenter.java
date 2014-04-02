package de.gedoplan.demo.presentation;

import javax.enterprise.inject.Model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Model
public class DemoPresenter
{
  private static final Log log    = LogFactory.getLog(DemoPresenter.class);

  private String           email  = "dirk.weil@gedoplan.de";
  private int              nights = 1;

  public String getEmail()
  {
    return this.email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public int getNights()
  {
    return this.nights;
  }

  public void setNights(int nights)
  {
    this.nights = nights;
  }

  public void doOk()
  {
    log.debug("email: " + this.email);
    log.debug("nights: " + this.nights);
  }
}
