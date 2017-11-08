package fr.cqrsaxon.api.event;

public class AccountDebitedEvent {
  private String accountId;
  private Double amountDebited;
  private Double balance;

  public AccountDebitedEvent(String accountId, Double amountDebited, Double balance) {
    this.accountId = accountId;
    this.amountDebited = amountDebited;
    this.balance = balance;
  }

  public String getAccountNo() {
    return accountId;
  }

  public Double getAmountDebited() {
    return amountDebited;
  }

  public Double getBalance() {
    return balance;
  }
}
