package de.gedoplan.demo.presentation;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;

@Model
@ApplicationScoped
public class ActiveFlowsPresenter
{
  private List<FlowPresenterBase> flowModels = new CopyOnWriteArrayList<>();

  public String getActiveBeans()
  {
    StringBuilder buf = new StringBuilder();
    for (FlowPresenterBase flowModel : this.flowModels)
    {
      if (buf.length() != 0)
      {
        buf.append(", ");
      }
      buf.append(flowModel.toString());
    }
    return buf.toString();
  }

  public void addActiveBean(FlowPresenterBase flowModel)
  {
    this.flowModels.add(flowModel);
  }

  public void removeActiveBean(FlowPresenterBase flowModel)
  {
    this.flowModels.remove(flowModel);
  }
}
