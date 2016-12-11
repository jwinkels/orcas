package com.opitzconsulting.orcas.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.logging.LogLevel

import de.opitzconsulting.orcas.diff.Parameters.FailOnErrorMode;
import de.opitzconsulting.orcas.diff.ParametersCall;
import de.opitzconsulting.orcas.diff.Parameters.InfoLogHandler;
import de.opitzconsulting.orcas.diff.Parameters.JdbcConnectParameters;

public abstract class BaseOrcasTask extends DefaultTask 
{
  private def parameterModifier;
  def boolean nologging;

  @TaskAction
  def executeOrcasTask()
  {
    ParametersCall lParametersCall = new ParametersCall();

    lParametersCall.getJdbcConnectParameters().setJdbcDriver( project.orcasconfiguration.jdbcdriver );
    lParametersCall.getJdbcConnectParameters().setJdbcUrl( project.orcasconfiguration.jdbcurl );
    lParametersCall.getJdbcConnectParameters().setJdbcUser( project.orcasconfiguration.username );
    lParametersCall.getJdbcConnectParameters().setJdbcPassword( project.orcasconfiguration.password );

    lParametersCall.setTargetplsql( project.orcasconfiguration.targetplsql );

    lParametersCall.setScriptprefix( project.orcasconfiguration.scriptfolderPrefix );
    lParametersCall.setScriptpostfix( project.orcasconfiguration.scriptfolderPostfix );
    lParametersCall.setScriptfolderrecursive( project.orcasconfiguration.scriptfolderrecursive );

    lParametersCall.setSpoolfile( project.file(project.orcasconfiguration.spoolfile).toString() );
    lParametersCall.setSpoolfolder( project.file(project.orcasconfiguration.spoolfolder).toString() );
    lParametersCall.setLogname( getLogname() );
    lParametersCall.setLoglevel( project.orcasconfiguration.loglevel );

    lParametersCall.setFailOnErrorMode( project.orcasconfiguration.failOnErrorMode );

    lParametersCall.setLogonly( project.orcasconfiguration.logonly );
    lParametersCall.setDropmode( project.orcasconfiguration.dropmode );
    lParametersCall.setIndexparallelcreate( project.orcasconfiguration.indexparallelcreate );
    lParametersCall.setIndexmovetablespace( project.orcasconfiguration.indexmovetablespace );
    lParametersCall.setTablemovetablespace( project.orcasconfiguration.tablemovetablespace );
    lParametersCall.setCreatemissingfkindexes( project.orcasconfiguration.createmissingfkindexes );
    lParametersCall.setExcludewheretable( project.orcasconfiguration.excludewheretable );
    lParametersCall.setExcludewheresequence( project.orcasconfiguration.excludewheresequence );
    lParametersCall.setDateformat( project.orcasconfiguration.dateformat );
    lParametersCall.setExtensionParameter( project.orcasconfiguration.extensionparameter );

    if( !project.orcasconfiguration.usernameorcas.equals( "" ) )
    {
      lParametersCall.setOrcasDbUser( project.orcasconfiguration.usernameorcas );
    }
    else
    {
      if( project.orcasconfiguration.orcasusername != null )
      {
        lParametersCall.setOrcasDbUser( project.orcasconfiguration.orcasusername );
      }
      else
      {
        lParametersCall.setOrcasDbUser( project.orcasconfiguration.username );
      }
    }

    if( project.orcasconfiguration.orcasusername != null )
    {
      JdbcConnectParameters lOrcasJdbcConnectParameters = new JdbcConnectParameters();
      lOrcasJdbcConnectParameters.setJdbcDriver( project.orcasconfiguration.orcasjdbcdriver );
      lOrcasJdbcConnectParameters.setJdbcUrl( project.orcasconfiguration.orcasjdbcurl == null ? pParameters.getJdbcConnectParameters().getJdbcUrl() : project.orcasconfiguration.orcasjdbcurl );
      lOrcasJdbcConnectParameters.setJdbcUser( project.orcasconfiguration.orcasusername );
      lOrcasJdbcConnectParameters.setJdbcPassword( project.orcasconfiguration.orcaspassword );
      lParametersCall.setOrcasJdbcConnectParameters( lOrcasJdbcConnectParameters );
    }
    else
    {
      lParametersCall.setOrcasJdbcConnectParameters( lParametersCall.getJdbcConnectParameters() );
    }

    nologging = "nologging".equals( lParametersCall.getloglevel() ); 

    lParametersCall.setInfoLogHandler(
      new InfoLogHandler() 
      {
        void logInfo( String pLogMessage )
        {
          BaseOrcasTask.this.logInfo( pLogMessage );
        }
      }
    );
  
    logger.log( nologging ? LogLevel.QUIET : LogLevel.ERROR, "" );

    executeOrcasTaskWithParameters( lParametersCall );
  }

  protected void logInfo( String pLogMessage )
  {
    LogLevel lLogLevel = nologging ? LogLevel.QUIET : LogLevel.ERROR;
    logger.log( lLogLevel, getLogname() + ": " + pLogMessage );
  }

  protected abstract String getLogname();

  protected abstract void executeOrcasTaskWithParameters( ParametersCall pParameters );

  protected void parameters( def pParameterModifier )
  {
    parameterModifier = pParameterModifier;
  }

  protected ParametersCall modifyParameters( ParametersCall pParameters )
  {
    if( parameterModifier != null )
    {
      parameterModifier.setDelegate( pParameters );
      parameterModifier();
    }

    return pParameters;
  }
}
