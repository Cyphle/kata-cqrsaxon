package fr.cqrsaxon.model;

import fr.cqrsaxon.api.event.AccountCreatedEvent;
import fr.cqrsaxon.api.event.AccountCreditedEvent;
import fr.cqrsaxon.api.event.AccountDebitedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import javax.persistence.Entity;

@Entity
public class Account extends AbstractAnnotatedAggregateRoot
{
  @AggregateIdentifier
  private String acountNo;
  private Double balance;

  public Account() {
  }

  public Account(String acountNo) {
    apply(new AccountCreatedEvent(acountNo));
  }

  @EventSourcingHandler
  public void applyAccountCreation(AccountCreatedEvent event) {
    this.acountNo = event.getAccountNo();
    this.balance = 0.0d;
  }

  @Override
  public Object getIdentifier() {
    return acountNo;
  }

  public void setIdentifier(String id) {
    this.acountNo = id;
  }

  public Double getBalance() {
    return balance;
  }

  public void debit(Double debitAmount) {
    if (Double.compare(debitAmount, 0.0d) > 0 && this.balance - debitAmount > -1) {
      AccountDebitedEvent accountDebitedEvent = new AccountDebitedEvent(this.acountNo, debitAmount, this.balance);
      apply(accountDebitedEvent);
    } else {
      throw new IllegalArgumentException("Cannot debit with the amount");
    }
  }

  @EventSourcingHandler
  public void applyDebit(AccountDebitedEvent event) {
    this.balance -= event.getAmountDebited();
  }

  public void credit(Double creditAmount) {
    if (Double.compare(creditAmount, 0.0d) > 0 && Double.compare(creditAmount, 1000000) < 0) {
      AccountCreditedEvent accountCreditedEvent = new AccountCreditedEvent(this.acountNo, creditAmount, this.balance);
      apply(accountCreditedEvent);
    } else {
      throw new IllegalArgumentException("Cannot credit with the amount");
    }
  }

  @EventSourcingHandler
  public void applyCredit(AccountCreditedEvent event) {
    this.balance += event.getCreditedAmount();
  }
}
