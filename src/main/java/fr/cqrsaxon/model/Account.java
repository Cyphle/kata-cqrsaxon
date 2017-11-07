package fr.cqrsaxon.model;

import org.axonframework.domain.AbstractAggregateRoot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account extends AbstractAggregateRoot {
  @Id
  private String id;
  @Column
  private Double balance;

  public Account() {
  }

  public Account(String id) {
    this.id = id;
    this.balance = 0.0d;
  }

  @Override
  public Object getIdentifier() {
    return id;
  }

  public void setIdentifier(String id) {
    this.id = id;
  }

  public Double getBalance() {
    return balance;
  }

  public void debit(Double debitAmount) {
    if (Double.compare(debitAmount, 0.0d) > 0 && this.balance - debitAmount > -1) {
      this.balance -= debitAmount;
    } else {
      throw new IllegalArgumentException("Cannot debit with the amount");
    }
  }

  public void credit(Double creditAmount) {
    if (Double.compare(creditAmount, 0.0d) > 0 && Double.compare(creditAmount, 1000000) < 0) {
      this.balance += creditAmount;
    } else {
      throw new IllegalArgumentException("Cannot credit with the amount");
    }
  }
}
