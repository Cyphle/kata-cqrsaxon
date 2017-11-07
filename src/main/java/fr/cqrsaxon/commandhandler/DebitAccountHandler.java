package fr.cqrsaxon.commandhandler;

import fr.cqrsaxon.api.command.DebitAccountCommand;
import fr.cqrsaxon.model.Account;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DebitAccountHandler {
  @Autowired
  private Repository repository;

  @CommandHandler
  public void handle(DebitAccountCommand debitAccountCommand) throws Throwable {
    Account accountToDebit = (Account) repository.load(debitAccountCommand.getAccount());
    accountToDebit.debit(debitAccountCommand.getAmount());
  }
}
