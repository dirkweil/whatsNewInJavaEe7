package de.gedoplan.demo.presentation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class FlowPresenterBase
{
  @Inject
  private ActiveFlowsPresenter        activeBeansModel;

  protected Log                   log               = LogFactory.getLog(this.getClass());

  protected String                created;

  private static final DateFormat CREATED_FORMATTER = new SimpleDateFormat("HH:mm:ss.SSS");

  public String getCreated()
  {
    return this.created;
  }

  @PostConstruct
  void postConstruct()
  {
    this.created = CREATED_FORMATTER.format(new Date());
    this.log.info(this.created + ": Construct");

    this.activeBeansModel.addActiveBean(this);
  }

  @PreDestroy
  void preDestroy()
  {
    this.log.info(this.created + ": Destroy");

    this.activeBeansModel.removeActiveBean(this);
  }

  @Override
  public String toString()
  {
    return getClass().getSimpleName() + "[" + this.created + "]";
  }

}
