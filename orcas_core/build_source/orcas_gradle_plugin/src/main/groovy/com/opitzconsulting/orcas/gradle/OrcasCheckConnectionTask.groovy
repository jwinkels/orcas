package com.opitzconsulting.orcas.gradle;

import de.opitzconsulting.orcas.diff.OrcasCheckConnection;
import de.opitzconsulting.orcas.diff.ParametersCall
import org.gradle.api.tasks.Internal;

public class OrcasCheckConnectionTask extends BaseOrcasTask
{
  @Internal
  def logname = "check-connection";

  @Override
  protected String getLogname()
  {
    return logname;
  }

  @Override
  protected void executeOrcasTaskWithParameters( ParametersCall pParameters )
  {
    new OrcasCheckConnection().mainRun( modifyParameters( pParameters ) );
  }
}
