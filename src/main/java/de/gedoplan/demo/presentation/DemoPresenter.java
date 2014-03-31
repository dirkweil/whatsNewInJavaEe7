package de.gedoplan.demo.presentation;

import javax.enterprise.inject.Model;

@Model
public class DemoPresenter
{
  private String email  = "dirk.weil@gedoplan.de";
  private int    nights = 1;

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

}
