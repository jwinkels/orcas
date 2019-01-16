package com.opitzconsulting.orcas.gradle

import java.io.File;

import de.opitzconsulting.orcas.diff.OrcasScriptRunner;
import de.opitzconsulting.orcas.diff.ParametersCall;

public abstract class BaseOrcasOneTimeScriptsTask extends BaseOrcasTask
{
  @Override
  protected void executeOrcasTaskWithParameters( ParametersCall pParameters )
  {
    if( !isCheckFolderExists() || getScriptfolder().exists() )
    {
      pParameters.setModelFile( getScriptfolder().toString() );
      pParameters.setIsOneTimeScriptMode( true );
      pParameters.setAdditionalParameters( null );
      pParameters.setIsOneTimeScriptLogonlyMode( false );

      new OrcasScriptRunner().mainRun( modifyParameters( pParameters ) );
    }
    else
    {
      logInfo( "no " + getLogname() + " found" );
    }
  }

  protected boolean isCheckFolderExists()
  {
    return true;
  }

  protected abstract File getScriptfolder();
}
