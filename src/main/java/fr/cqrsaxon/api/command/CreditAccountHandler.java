package fr.cqrsaxon.api.command;

import org.axonframework.commandhandling.annotation.CommandHandler;

public class CreditAccountHandler {
  @CommandHandler
  public Object handle(CreditAccountCommand creditAccountCommand) throws Throwable {
    System.out.println("I can handle credit account command: " + creditAccountCommand.getAccount() + " for " + creditAccountCommand.getAmount() + " euros");

    return null;
  }
}
