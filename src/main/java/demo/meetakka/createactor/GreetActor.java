package demo.meetakka.createactor;

import akka.actor.AbstractActor;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GreetActor extends AbstractBehavior<GreetingMessage> {

    public static Behavior<GreetingMessage> create() {
        return Behaviors.setup(context -> {

            return new GreetActor(context);
        });
    }

    public GreetActor(ActorContext<GreetingMessage> context) {
        super(context);
    }

    @Override
    public Receive<GreetingMessage> createReceive() {
        return newReceiveBuilder()
                .onMessage(Greet.class, greet -> {
                    log.info("{} greeted and say: {}", greet.getName(), greet.getMessage());
                    log.info("current path: {}", getContext().getSelf());
                    return this;
                })
                .build();
    }
}
