package fr.cqrsaxon.api.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.unitofwork.UnitOfWork;

public class DebitAccountHandler implements CommandHandler {
  @Override
  public Object handle(CommandMessage commandMessage, UnitOfWork unitOfWork) throws Throwable {
    DebitAccountCommand debitAccountCommand = (DebitAccountCommand) commandMessage.getPayload();

    String account = debitAccountCommand.getAccount();
    Double amount = debitAccountCommand.getAmount();

    System.out.println("I handle debit account command: " + account + " for " + amount + " euros");

    return null;
  }
}
