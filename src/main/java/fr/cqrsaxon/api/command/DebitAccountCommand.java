package fr.cqrsaxon.api.command;

public class DebitAccountCommand {
  private final String account;
  private final Double amount;

  public DebitAccountCommand(String account, Double amount) {
    this.account = account;
    this.amount = amount;
  }

  public String getAccount() {
    return account;
  }

  public Double getAmount() {
    return amount;
  }
}
