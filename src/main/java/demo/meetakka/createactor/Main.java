package demo.meetakka.createactor;

import akka.NotUsed;
import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;

public class Main {

    public static Behavior<NotUsed> root() {
        return Behaviors.setup(context -> {
            ActorRef<GreetingMessage> greet = context.spawn(GreetActor.create(), "greet");
            greet.tell(new Greet("xiaoming", "hello"));
            return Behaviors.empty();
        });
    }


    public static void main(String[] args) {
        ActorSystem.create(root(), "greet-system");
    }
}
