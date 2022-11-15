package com.daekyo.boot_config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
  /*
    현재 사용할 DataSource 를 결정하는 로직
   */
  @Override
  protected Object determineCurrentLookupKey() {
    return TransactionSynchronizationManager.isCurrentTransactionReadOnly()
        ? "DB1" : "DB2";
  }
}
