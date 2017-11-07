package fr.cqrsaxon.web;

import fr.cqrsaxon.api.command.CreditAccountCommand;
import fr.cqrsaxon.api.command.DebitAccountCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
  @Autowired
  private CommandGateway commandGateway;

  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("name", "dadepo");
    return "index";
  }

  @RequestMapping("/debit")
  @Transactional
  @ResponseBody
  public void doDebit(@RequestParam("acc") String accountNumber, @RequestParam("amount") double amount) {
    DebitAccountCommand debitAccountCommand = new DebitAccountCommand(accountNumber, amount);
    commandGateway.send(debitAccountCommand);
  }

  @RequestMapping("/credit")
  @Transactional
  @ResponseBody
  public void doCredit(@RequestParam("acc") String accountNumber, @RequestParam("amount") double amount) {
    CreditAccountCommand creditAccountCommand = new CreditAccountCommand(accountNumber, amount);
    commandGateway.send(creditAccountCommand);
  }
}
