package demo.meetakka.useactor;

import akka.actor.typed.Behavior;
import akka.actor.typed.PostStop;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import demo.meetakka.useactor.message.Add;
import demo.meetakka.useactor.message.CountCommand;
import demo.meetakka.useactor.message.ShowCount;
import demo.meetakka.useactor.message.Sub;
import lombok.extern.slf4j.Slf4j;

import java.beans.beancontext.BeanContext;

@Slf4j
public class CountActor extends AbstractBehavior<CountCommand> {

    private int count;

    public static Behavior<CountCommand> create() {
        return Behaviors.setup(CountActor::new);
    }

    public CountActor(ActorContext<CountCommand> context) {
        super(context);
    }

    @Override
    public Receive<CountCommand> createReceive() {
        return newReceiveBuilder()
                .onMessage(Add.class, add -> {
                    count++;
                    return this;
                })
                .onMessage(Sub.class, sub -> {
                    count--;
                    return this;
                })
                .onMessage(ShowCount.class, showCount -> {
                    log.info("count is {}", count);
                    return Behaviors.stopped();
                })
                .onSignal(PostStop.class, stop -> {
                    log.info("counter actor stopped");
                    // this behavior will be ignored
                    return Behaviors.empty();
                })
                .build();
    }
}
