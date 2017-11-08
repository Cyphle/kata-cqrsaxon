package fr.cqrsaxon.api.event;

public class AccountCreatedEvent {
  private final String accountNo;

  public AccountCreatedEvent(String accountNo) {
    this.accountNo = accountNo;
  }

  public String getAccountNo() {
    return accountNo;
  }
}
