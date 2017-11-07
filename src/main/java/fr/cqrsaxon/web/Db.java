package fr.cqrsaxon.web;

import fr.cqrsaxon.model.Account;
import org.axonframework.repository.Repository;
import org.axonframework.unitofwork.DefaultUnitOfWork;
import org.axonframework.unitofwork.UnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;

@Component
public class Db {
  @Autowired
  @Qualifier("transactionManager")
  protected PlatformTransactionManager txManager;

  @Autowired
  private Repository repository;

  @PostConstruct
  private void init() {
    TransactionTemplate transactionTmp = new TransactionTemplate(txManager);
    transactionTmp.execute(new TransactionCallbackWithoutResult() {
      @Override
      protected void doInTransactionWithoutResult(TransactionStatus status) {
        UnitOfWork uow = DefaultUnitOfWork.startAndGet();
        repository.add(new Account("acc-one"));
        repository.add(new Account("acc-two"));
        uow.commit();
      }
    });
  }
}
