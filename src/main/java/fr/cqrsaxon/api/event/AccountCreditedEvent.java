package fr.cqrsaxon.api.event;

public class AccountCreditedEvent {
  private String accountId;
  private Double creditedAmount;
  private Double balance;

  public AccountCreditedEvent(String accountId, Double creditedAmount, Double balance) {
    this.accountId = accountId;
    this.creditedAmount = creditedAmount;
    this.balance = balance;
  }

  public String getAccountNo() {
    return accountId;
  }

  public Double getCreditedAmount() {
    return creditedAmount;
  }

  public Double getBalance() {
    return balance;
  }
}
