package de.opitzconsulting.orcas.diff;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

import de.opitzconsulting.orcas.orig.diff.*;
import de.opitzconsulting.origOrcasDsl.*;
import org.eclipse.emf.common.util.EList;

import de.opitzconsulting.orcas.sql.CallableStatementProvider;

public class InitDiffRepository
{
  private static String defaultTablespace;

  private static String handleDefaultTablespace( String pDefaultTablespace, String pValue )
  {
    return pValue == null || pValue.equals( pDefaultTablespace ) ? null : pValue;
  }

  public static void init( CallableStatementProvider pCallableStatementProvider, DatabaseHandler pDatabaseHandler )
  {
    CharType lDefaultCharType = pDatabaseHandler.getDefaultCharType( pCallableStatementProvider );

    defaultTablespace = pDatabaseHandler.getDefaultTablespace( pCallableStatementProvider );

    DiffRepository.setIndexOrUniqueKeyMerge( new IndexOrUniqueKeyMerge()
    {
      @Override
      public String tablespaceCleanValueIfNeeded( String pValue )
      {
        return handleDefaultTablespace( defaultTablespace, super.tablespaceCleanValueIfNeeded( pValue ) );
      }
    } );
    DiffRepository.getIndexOrUniqueKeyMerge().consNameIsConvertToUpperCase = true;
    DiffRepository.getIndexOrUniqueKeyMerge().tablespaceIsConvertToUpperCase = true;

    // not neede for partitioning since there ist no diff there
    // pa_orcas_om_repository_orig.set_om_orig_subsubpart( new
    // om_orig_subsubpart( 1, 1 ) );
    // pa_orcas_om_repository_orig.set_om_orig_tablepartitioning( new
    // om_orig_tablepartitioning() );
    // pa_orcas_om_repository_orig.set_om_orig_rangepartitionval( new
    // om_orig_rangepartitionval( 0, 0 ) );
    // pa_orcas_om_repository_orig.set_om_orig_refpartition( new
    // om_orig_refpartition( 1, 1 ) );
    // pa_orcas_om_repository_orig.set_om_orig_hashpartition( new
    // om_orig_hashpartition( 1, 1 ) );
    // pa_orcas_om_repository_orig.set_om_orig_listpartitionvalu( new
    // om_orig_listpartitionvalu( 0, 0 ) );
    // pa_orcas_om_repository_orig.set_om_orig_listpartition( new
    // om_orig_listpartition( 1, 1 ) );
    // pa_orcas_om_repository_orig.set_om_orig_rangepartition( new
    // om_orig_rangepartition( 1, 1 ) );
    // pa_orcas_om_repository_orig.set_om_orig_listsubpart( new
    // om_orig_listsubpart( 1 ) );
    // pa_orcas_om_repository_orig.set_om_orig_rangesubpart( new
    // om_orig_rangesubpart( 1 ) );
    // pa_orcas_om_repository_orig.set_om_orig_refpartitions( new
    // om_orig_refpartitions( 1 ) );
    // pa_orcas_om_repository_orig.set_om_orig_hashsubsubpart( new
    // om_orig_hashsubsubpart() );
    // pa_orcas_om_repository_orig.set_om_orig_hashsubparts( new
    // om_orig_hashsubparts() );
    // pa_orcas_om_repository_orig.set_om_orig_listsubsubpart( new
    // om_orig_listsubsubpart() );
    // pa_orcas_om_repository_orig.set_om_orig_listpartitions( new
    // om_orig_listpartitions() );
    // pa_orcas_om_repository_orig.set_om_orig_hashpartitions( new
    // om_orig_hashpartitions() );
    // pa_orcas_om_repository_orig.set_om_orig_rangepartitions( new
    // om_orig_rangepartitions( 0 ) );
    // pa_orcas_om_repository_orig.set_om_orig_rangesubsubpart( new
    // om_orig_rangesubsubpart() );
    // pa_orcas_om_repository_orig.set_om_orig_listsubparts( new
    // om_orig_listsubparts() );
    // pa_orcas_om_repository_orig.set_om_orig_rangesubparts( new
    // om_orig_rangesubparts() );

    DiffRepository.setLobStorageMerge( new LobStorageMerge()
    {
      @Override
      public boolean isChildOrderRelevant()
      {
        return false;
      }

      @Override
      public List<Integer> getMergeResult( List<LobStorageDiff> pNewDiffValues, List<LobStorage> pOldValues )
      {
        List<Integer> lReturn = new ArrayList<Integer>();

        for( LobStorage lOldValue : pOldValues )
        {
          boolean lFound = false;

          int i = 0;
          for( LobStorageDiff pNewDiffValue : pNewDiffValues )
          {
            if( pNewDiffValue.column_nameNew.equals( lOldValue.getColumn_name() ) )
            {
              lReturn.add( i );
              lFound = true;
              break;
            }

            i++;
          }

          if( !lFound )
          {
            lReturn.add( null );
          }
        }

        return lReturn;
      }
    } );
    DiffRepository.getLobStorageMerge().column_nameIsConvertToUpperCase = true;
    DiffRepository.getLobStorageMerge().lobStorageTypeDefaultValue = LobStorageType.BASICFILE;

    DiffRepository.setLobStorageParametersMerge( new LobStorageParametersMerge()
    {
      @Override
      public String tablespaceCleanValueIfNeeded( String pValue )
      {
        return handleDefaultTablespace( defaultTablespace, super.tablespaceCleanValueIfNeeded( pValue ) );
      }
    } );
    DiffRepository.getLobStorageParametersMerge().tablespaceIsConvertToUpperCase = true;
    DiffRepository.getLobStorageParametersMerge().compressTypeDefaultValue = CompressType.NOCOMPRESS;
    DiffRepository.getLobStorageParametersMerge().lobCompressForTypeDefaultValue = LobCompressForType.MEDIUM;
    DiffRepository.getLobStorageParametersMerge().lobDeduplicateTypeDefaultValue = LobDeduplicateType.KEEP_DUPLICATES;

    DiffRepository.setVarrayStorageMerge( new VarrayStorageMerge()
    {
      @Override
      public boolean isChildOrderRelevant()
      {
        return false;
      }

      @Override
      public List<Integer> getMergeResult( List<VarrayStorageDiff> pNewDiffValues, List<VarrayStorage> pOldValues )
      {
        List<Integer> lReturn = new ArrayList<Integer>();

        for( VarrayStorage lOldValue : pOldValues )
        {
          boolean lFound = false;

          int i = 0;
          for( VarrayStorageDiff pNewDiffValue : pNewDiffValues )
          {
            if( pNewDiffValue.column_nameNew.equals( lOldValue.getColumn_name() ) )
            {
              lReturn.add( i );
              lFound = true;
              break;
            }

            i++;
          }

          if( !lFound )
          {
            lReturn.add( null );
          }
        }

        return lReturn;
      }
    } );
    DiffRepository.getVarrayStorageMerge().column_nameIsConvertToUpperCase = true;

    DiffRepository.setInlineCommentMerge( new InlineCommentMerge()
    {
      @Override
      public boolean isChildOrderRelevant()
      {
        return false;
      }

      @Override
      public List<Integer> getMergeResult( List<InlineCommentDiff> pNewDiffValues, List<InlineComment> pOldValues )
      {
        List<Integer> lReturn = new ArrayList<Integer>();

        for( InlineComment lOldValue : pOldValues )
        {
          boolean lFound = false;

          int i = 0;
          for( InlineCommentDiff pNewDiffValue : pNewDiffValues )
          {
            boolean lisEqual;

            if( pNewDiffValue.column_nameNew == null )
            {
              lisEqual = lOldValue.getColumn_name() == null;
            }
            else
            {
              lisEqual = pNewDiffValue.column_nameNew.equals( lOldValue.getColumn_name() );
            }

            if( lisEqual )
            {
              lReturn.add( i );
              lFound = true;
              break;
            }

            i++;
          }

          if( !lFound )
          {
            lReturn.add( null );
          }
        }

        return lReturn;
      }

      @Override
      public void cleanupValues( InlineComment pValue )
      {
        super.cleanupValues( pValue );

        if ( pValue.getColumn_name_string() != null )
        {
          if ( pValue.getColumn_name_string().matches( "^[A-Z]([A-Z]|[0-9]|[_#$])*$" ) )
          {
            pValue.setColumn_name( pValue.getColumn_name_string() );
          }
          else
          {
            pValue.setColumn_name( '"' + pValue.getColumn_name_string() + '"' );
          }
          pValue.setColumn_name_string( null );
        }
      }
    } );
    DiffRepository.getInlineCommentMerge().column_nameIsConvertToUpperCase = true;

    DiffRepository.setColumnRefMerge( new ColumnRefMerge()
    {
      @Override
      public void cleanupValues( ColumnRef pValue )
      {
        super.cleanupValues( pValue );

        if ( pValue.getColumn_name_string() != null )
        {
          if ( pValue.getColumn_name_string().matches( "^[A-Z]([A-Z]|[0-9]|[_#$])*$" ) )
          {
            pValue.setColumn_name( pValue.getColumn_name_string() );
          }
          else
          {
            pValue.setColumn_name( '"' + pValue.getColumn_name_string() + '"' );
          }
          pValue.setColumn_name_string( null );
        }
      }

    } );
    DiffRepository.getColumnRefMerge().column_nameIsConvertToUpperCase = true;

    DiffRepository.setPrimaryKeyMerge( new PrimaryKeyMerge()
    {
      @Override
      public String tablespaceCleanValueIfNeeded( String pValue )
      {
        return handleDefaultTablespace( defaultTablespace, super.tablespaceCleanValueIfNeeded( pValue ) );
      }
    } );
    DiffRepository.getPrimaryKeyMerge().consNameIsConvertToUpperCase = true;
    DiffRepository.getPrimaryKeyMerge().indexnameIsConvertToUpperCase = true;
    DiffRepository.getPrimaryKeyMerge().statusDefaultValue = EnableType.ENABLE;

    DiffRepository.setMviewLogMerge( new MviewLogMerge()
    {
      @Override
      public String tablespaceCleanValueIfNeeded( String pValue )
      {
        return handleDefaultTablespace( defaultTablespace, super.tablespaceCleanValueIfNeeded( pValue ) );
      }
    } );
    DiffRepository.getMviewLogMerge().newValuesDefaultValue = NewValuesType.EXCLUDING;
    DiffRepository.getMviewLogMerge().parallelDefaultValue = ParallelType.NOPARALLEL;
    DiffRepository.getMviewLogMerge().synchronousDefaultValue = SynchronousType.SYNCHRONOUS;
    DiffRepository.getMviewLogMerge().tablespaceIsConvertToUpperCase = true;

    DiffRepository.setForeignKeyMerge( new ForeignKeyMerge()
    {
      @Override
      public boolean isChildOrderRelevant()
      {
        return false;
      }

      @Override
      public List<Integer> getMergeResult( List<ForeignKeyDiff> pNewDiffValues, List<ForeignKey> pOldValues )
      {
        return getMergeResultByFunction( pNewDiffValues, pOldValues, //
        ( pNewDiffValue, pOldValue ) -> pNewDiffValue.consNameNew.equals( pOldValue.getConsName() ), //
        ( pNewDiffValue, pOldValue ) -> isColumnsEqual( pNewDiffValue.srcColumnsDiff, pOldValue.getSrcColumns() )//
                                        && isColumnsEqual( pNewDiffValue.destColumnsDiff, pOldValue.getDestColumns() ) //
                                        && pNewDiffValue.destTableNew.equals( pOldValue.getDestTable() ) );
      }
    } );
    DiffRepository.getForeignKeyMerge().consNameIsConvertToUpperCase = true;
    DiffRepository.getForeignKeyMerge().deferrtypeDefaultValue = DeferrType.IMMEDIATE;
    DiffRepository.getForeignKeyMerge().delete_ruleDefaultValue = FkDeleteRuleType.NO_ACTION;
    DiffRepository.getForeignKeyMerge().destTableIsConvertToUpperCase = true;
    DiffRepository.getForeignKeyMerge().statusDefaultValue = EnableType.ENABLE;

    DiffRepository.setSequenceMerge( new SequenceMerge()
    {
      @Override
      public boolean isChildOrderRelevant()
      {
        return false;
      }

      @Override
      public List<Integer> getMergeResult( List<SequenceDiff> pNewDiffValues, List<Sequence> pOldValues )
      {
        List<Integer> lReturn = new ArrayList<Integer>();

        for( Sequence lOldValue : pOldValues )
        {
          boolean lFound = false;

          int i = 0;
          for( SequenceDiff pNewDiffValue : pNewDiffValues )
          {
            if( pNewDiffValue.sequence_nameNew.equals( lOldValue.getSequence_name() ) )
            {
              lReturn.add( i );
              lFound = true;
              break;
            }

            i++;
          }

          if( !lFound )
          {
            lReturn.add( null );
          }
        }

        return lReturn;
      }

      public BigInteger cacheCleanValueIfNeeded( BigInteger pValue )
      {
        if (Objects.equals(pValue, BigInteger.valueOf(20))) {

          return null;
        }

        return pValue;
      }

      @Override
      public BigInteger increment_byCleanValueIfNeeded( BigInteger pValue )
      {
        if(Objects.equals(pValue, BigInteger.ONE))
        {
          return null;
        }

        return pValue;
      }

      public BigInteger maxvalueCleanValueIfNeeded( BigInteger pValue )
      {
        if(Objects.equals(pValue, new BigInteger("9999999999999999999999999999")))
        {
          return null;
        }

        return pValue;
      }

      public BigInteger minvalueCleanValueIfNeeded( BigInteger pValue )
      {
        if(Objects.equals(pValue, BigInteger.ONE))
        {
          return null;
        }

        return pValue;
      }
    } );
    DiffRepository.getSequenceMerge().cycleDefaultValue = CycleType.NOCYCLE;
    DiffRepository.getSequenceMerge().orderDefaultValue = OrderType.NOORDER;
    DiffRepository.getSequenceMerge().sequence_nameIsConvertToUpperCase = true;

    DiffRepository.setConstraintMerge( new ConstraintMerge()
    {
      @Override
      public boolean isChildOrderRelevant()
      {
        return false;
      }

      @Override
      public List<Integer> getMergeResult( List<ConstraintDiff> pNewDiffValues, List<Constraint> pOldValues )
      {
        return getMergeResultByFunction( pNewDiffValues, pOldValues, //
        ( pNewDiffValue, pOldValue ) -> pNewDiffValue.consNameNew.equals( pOldValue.getConsName() ), //
        ( pNewDiffValue, pOldValue ) -> pNewDiffValue.ruleNew.equals( pOldValue.getRule() ) );
      }
    } );
    DiffRepository.getConstraintMerge().consNameIsConvertToUpperCase = true;
    DiffRepository.getConstraintMerge().deferrtypeDefaultValue = DeferrType.IMMEDIATE;
    DiffRepository.getConstraintMerge().statusDefaultValue = EnableType.ENABLE;

    // pa_orcas_om_repository_orig.set_om_orig_columnidentity( new
    // om_orig_columnidentity( 0, 0, ot_orig_cycletype.c_nocycle, 0,
    // ot_orig_ordertype.c_noorder ) );

    DiffRepository.setUniqueKeyMerge( new UniqueKeyMerge()
    {
      @Override
      public boolean isChildOrderRelevant()
      {
        return false;
      }

      @Override
      public List<Integer> getMergeResult( List<UniqueKeyDiff> pNewDiffValues, List<UniqueKey> pOldValues )
      {
        return getMergeResultByFunction( pNewDiffValues, pOldValues //
        , ( pNewDiffValue, pOldValue ) -> pNewDiffValue.consNameNew.equals( pOldValue.getConsName() ) //
        , ( pNewDiffValue, pOldValue ) -> pNewDiffValue.indexnameNew == null && pOldValue.getIndexname() == null && isColumnsEqual( pNewDiffValue.uk_columnsDiff, pOldValue.getUk_columns() ) );
      }
    } );
    DiffRepository.getUniqueKeyMerge().indexnameIsConvertToUpperCase = true;
    DiffRepository.getUniqueKeyMerge().statusDefaultValue = EnableType.ENABLE;

    DiffRepository.setMviewMerge( new MviewMerge()
    {
      @Override
      public boolean isChildOrderRelevant()
      {
        return false;
      }

      @Override
      public List<Integer> getMergeResult( List<MviewDiff> pNewDiffValues, List<Mview> pOldValues )
      {
        List<Integer> lReturn = new ArrayList<Integer>();

        for( Mview lOldValue : pOldValues )
        {
          boolean lFound = false;

          int i = 0;
          for( MviewDiff pNewDiffValue : pNewDiffValues )
          {
            if( pNewDiffValue.mview_nameNew.equals( lOldValue.getMview_name() ) )
            {
              lReturn.add( i );
              lFound = true;
              break;
            }

            i++;
          }

          if( !lFound )
          {
            lReturn.add( null );
          }
        }

        return lReturn;
      }

      @Override
      public String tablespaceCleanValueIfNeeded( String pValue )
      {
        return handleDefaultTablespace( defaultTablespace, super.tablespaceCleanValueIfNeeded( pValue ) );
      }

      @Override
      public void cleanupValues( Mview pValue )
      {
        super.cleanupValues( pValue );

        if( pValue.getCompression() == CompressType.COMPRESS )
        {
          if( pValue.getCompressionFor() == CompressForType.DIRECT_LOAD )
          {
            pValue.setCompressionFor( null );
          }
        }
      }
    } );
    DiffRepository.getMviewMerge().mview_nameIsConvertToUpperCase = true;
    DiffRepository.getMviewMerge().tablespaceIsConvertToUpperCase = true;
    DiffRepository.getMviewMerge().buildModeDefaultValue = BuildModeType.IMMEDIATE;
    DiffRepository.getMviewMerge().compressionDefaultValue = CompressType.NOCOMPRESS;
    DiffRepository.getMviewMerge().parallelDefaultValue = ParallelType.NOPARALLEL;
    DiffRepository.getMviewMerge().queryRewriteDefaultValue = EnableType.DISABLE;
    DiffRepository.getMviewMerge().refreshMethodDefaultValue = RefreshMethodType.FORCE;
    DiffRepository.getMviewMerge().refreshModeDefaultValue = RefreshModeType.DEMAND;

    DiffRepository.setIndexMerge( new IndexMerge()
    {
      @Override
      public boolean isChildOrderRelevant()
      {
        return false;
      }

      @Override
      public List<Integer> getMergeResult( List<IndexDiff> pNewDiffValues, List<Index> pOldValues )
      {
        return getMergeResultByFunction( pNewDiffValues, pOldValues //
        , ( pNewDiffValue, pOldValue ) -> pNewDiffValue.consNameNew.equals( pOldValue.getConsName() ) //
        , ( pNewDiffValue, pOldValue ) -> pNewDiffValue.function_based_expressionNew == null && pNewDiffValue.domain_index_expressionNew == null && isColumnsEqual( pNewDiffValue.index_columnsDiff, pOldValue.getIndex_columns() ) );
      }
    } );
    DiffRepository.getIndexMerge().compressionDefaultValue = CompressType.NOCOMPRESS;
    DiffRepository.getIndexMerge().globalDefaultValue = IndexGlobalType.GLOBAL;
    DiffRepository.getIndexMerge().loggingDefaultValue = LoggingType.LOGGING;
    DiffRepository.getIndexMerge().parallelDefaultValue = ParallelType.NOPARALLEL;

    DiffRepository.setColumnMerge( new ColumnMerge()
    {
      @Override
      public boolean isChildOrderRelevant()
      {
        return false;
      }

      @Override
      public List<Integer> getMergeResult( List<ColumnDiff> pNewDiffValues, List<Column> pOldValues )
      {
        List<Integer> lReturn = new ArrayList<Integer>();

        for( Column lOldValue : pOldValues )
        {
          boolean lFound = false;

          int i = 0;
          for( ColumnDiff pNewDiffValue : pNewDiffValues )
          {
            if( pNewDiffValue.nameNew.equals( lOldValue.getName() ) )
            {
              lReturn.add( i );
              lFound = true;
              break;
            }

            i++;
          }

          if( !lFound )
          {
            lReturn.add( null );
          }
        }

        return lReturn;
      }

      @Override
      public void cleanupValues( Column pValue )
      {
        super.cleanupValues( pValue );

        if ( pValue.getName_string() != null )
        {
          if ( pValue.getName_string().matches( "^[A-Z]([A-Z]|[0-9]|[_#$])*$" ) )
          {
            pValue.setName( pValue.getName_string() );
          }
          else
          {
            pValue.setName( '"' + pValue.getName_string() + '"' );
          }
          pValue.setName_string( null );
        }

        if( pValue.getPrecision() == DiffRepository.getNullIntValue() )
        {
          if( pValue.getData_type() == DataType.FLOAT )
          {
            pValue.setPrecision( 126 );
          }
          if( pValue.getData_type() == DataType.TIMESTAMP )
          {
            pValue.setPrecision( 6 );
          }
          if( pValue.getData_type() == DataType.CHAR )
          {
            pValue.setPrecision( 1 );
          }
        }
      }

    } );
    DiffRepository.getColumnMerge().byteorcharDefaultValue = lDefaultCharType;
    DiffRepository.getColumnMerge().nameIsConvertToUpperCase = true;
    DiffRepository.getColumnMerge().object_typeIsConvertToUpperCase = true;

    DiffRepository.setTableMerge( new TableMerge()
    {
      @Override
      public boolean isChildOrderRelevant()
      {
        return false;
      }

      @Override
      public List<Integer> getMergeResult( List<TableDiff> pNewDiffValues, List<Table> pOldValues )
      {
        List<Integer> lReturn = new ArrayList<Integer>();

        for( Table lOldValue : pOldValues )
        {
          boolean lFound = false;

          int i = 0;
          for( TableDiff pNewDiffValue : pNewDiffValues )
          {
            if( pNewDiffValue.nameNew.equals( lOldValue.getName() ) )
            {
              lReturn.add( i );
              lFound = true;
              break;
            }

            i++;
          }

          if( !lFound )
          {
            lReturn.add( null );
          }
        }

        return lReturn;
      }

      @Override
      public String tablespaceCleanValueIfNeeded( String pValue )
      {
        return handleDefaultTablespace( defaultTablespace, super.tablespaceCleanValueIfNeeded( pValue ) );
      }

      @Override
      public void cleanupValues( Table pValue )
      {
        super.cleanupValues( pValue );

        if( pValue.getPermanentness() == PermanentnessType.GLOBAL_TEMPORARY )
        {
          if( pValue.getLogging() == LoggingType.NOLOGGING )
          {
            pValue.setLogging( null );
          }
        }
        else
        {
          if( pValue.getLogging() == LoggingType.LOGGING )
          {
            pValue.setLogging( null );
          }
        }

        if( pValue.getCompression() == CompressType.COMPRESS )
        {
          if( pValue.getCompressionFor() == CompressForType.DIRECT_LOAD )
          {
            pValue.setCompressionFor( null );
          }

          if( pValue.getPctfree() != null && pValue.getPctfree().intValue() == 0 )
          {
            pValue.setPctfree( null );
          }
        }
        else
        {
          if( pValue.getPctfree() != null && pValue.getPctfree().intValue() == 10 )
          {
            pValue.setPctfree( null );
          }
        }
      }
    } );
    DiffRepository.getTableMerge().compressionDefaultValue = CompressType.NOCOMPRESS;
    DiffRepository.getTableMerge().nameIsConvertToUpperCase = true;
    DiffRepository.getTableMerge().parallelDefaultValue = ParallelType.NOPARALLEL;
    DiffRepository.getTableMerge().tablespaceIsConvertToUpperCase = true;
    DiffRepository.getTableMerge().permanentnessDefaultValue = PermanentnessType.PERMANENT;
    DiffRepository.getTableMerge().transactionControlDefaultValue = PermanentnessTransactionType.ON_COMMIT_DELETE;
  }

  public static String getDefaultTablespace()
  {
    return defaultTablespace;
  }

  private static boolean isColumnsEqual( List<ColumnRefDiff> pNewColumnsDiff, EList<ColumnRef> pOldColumns )
  {
    if( pNewColumnsDiff.size() != pOldColumns.size() )
    {
      return false;
    }

    for( int i = 0; i < pNewColumnsDiff.size(); i++ )
    {
      if( !pNewColumnsDiff.get( i ).column_nameNew.equals( pOldColumns.get( i ).getColumn_name() ) )
      {
        return false;
      }
    }

    return true;
  }

  private static <T_DIFF extends AbstractDiff, T> List<Integer> getMergeResultByFunction( List<T_DIFF> pNewDiffValues, List<T> pOldValues, BiFunction<T_DIFF, T, Boolean> pPrimaryCompare, BiFunction<T_DIFF, T, Boolean> pSecondaryCompare )
  {
    List<Integer> lReturn = new ArrayList<Integer>();

    for( T lOldValue : pOldValues )
    {
      boolean lFound = false;

      int i = 0;
      for( T_DIFF pNewDiffValue : pNewDiffValues )
      {
        if( pPrimaryCompare.apply( pNewDiffValue, lOldValue ) )
        {
          lReturn.add( i );
          lFound = true;
          break;
        }

        i++;
      }

      if( !lFound )
      {
        i = 0;
        for( T_DIFF pNewDiffValue : pNewDiffValues )
        {
          if( pSecondaryCompare.apply( pNewDiffValue, lOldValue ) )
          {
            lReturn.add( i );
            lFound = true;
            break;
          }

          i++;
        }

        if( !lFound )
        {
          lReturn.add( null );
        }
      }
    }

    return lReturn;
  }
}
