package de.opitzconsulting.orcas.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * This wrapper wrapps a resultset, usually a {@link WrapperReturnValueFromResultSet} or a {@link WrapperIteratorResultSet} is easier to use.
 */
public abstract class WrapperResultSet extends WrapperCallableStatement
{
  private List _parameters;

  /**
   * Standard Constructor.
   */
  public WrapperResultSet( String pSqlString, CallableStatementProvider pCallableStatementProvider, List pParameters, String pClientContextName )
  {
    super( pSqlString, pCallableStatementProvider, pClientContextName );

    _parameters = pParameters;
  }

  /**
   * Standard Constructor.
   */
  public WrapperResultSet( String pSqlString, CallableStatementProvider pCallableStatementProvider, String pClientContextName )
  {
    this( pSqlString, pCallableStatementProvider, null, pClientContextName );
  }

  /**
   * Standard Constructor.
   */
  public WrapperResultSet( String pSqlString, CallableStatementProvider pCallableStatementProvider, List pParameters )
  {
    this( pSqlString, pCallableStatementProvider, pParameters, null );
  }

  /**
   * Standard Constructor.
   */
  public WrapperResultSet( String pSqlString, CallableStatementProvider pCallableStatementProvider )
  {
    this( pSqlString, pCallableStatementProvider, null, null );
  }

  /**
   * Calls setParameter first, than executeQuery on the CallableStatement is called and the resultest is passed to useResultSet.
   */
  protected final void useCallableStatement( CallableStatement pCallableStatement ) throws SQLException
  {
    ResultSet lResultSet = null;

    setParameter( pCallableStatement );

    try
    {
      lResultSet = pCallableStatement.executeQuery();

      useResultSet( lResultSet );
    }
    finally
    {
      if( lResultSet != null )
      {
        lResultSet.close();
      }
    }
  }

  /**
   * Is called when the ResultSet is ready-to-use. The first operation should be a pResultSet.next() during this method. The resultset may not be closed.
   */
  protected abstract void useResultSet( ResultSet pResultSet ) throws SQLException;

  /**
   * Is called before the CallabelStatement is executed. It is usually used to set parameters. The CallableStatement may not be executed. The default implementaion does nothing.
   */
  protected void setParameter( CallableStatement pCallableStatement ) throws SQLException
  {
    if( _parameters != null )
    {
      for( int i = 0; i < _parameters.size(); i++ )
      {
        pCallableStatement.setObject( i + 1, _parameters.get( i ) );
      }
    }
  }
}
