package fr.cqrsaxon.commandhandler;

import fr.cqrsaxon.api.command.CreditAccountCommand;
import fr.cqrsaxon.model.Account;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditAccountHandler {
  @Autowired
  private Repository repository;

  @CommandHandler
  public void handle(CreditAccountCommand creditAccountCommand) throws Throwable {
    Account accountToCredit = (Account) repository.load(creditAccountCommand.getAccount());
    accountToCredit.credit(creditAccountCommand.getAmount());
  }
}
