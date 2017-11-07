package fr.cqrsaxon.config;

import fr.cqrsaxon.api.command.DebitAccountCommand;
import fr.cqrsaxon.api.command.DebitAccountHandler;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
  @Bean
  public SimpleCommandBus commandBus() {
    SimpleCommandBus simpleCommandBus = new SimpleCommandBus();
    simpleCommandBus.subscribe(DebitAccountCommand.class.getName(), new DebitAccountHandler());
    return simpleCommandBus;
  }

  @Bean
  AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
    AnnotationCommandHandlerBeanPostProcessor handler = new AnnotationCommandHandlerBeanPostProcessor();
    handler.setCommandBus(commandBus());
    return handler;
  }

  @Bean
  public DefaultCommandGateway commandGateway() {
    return new DefaultCommandGateway(commandBus());
  }
}
