package homework.meetakka;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;

public class Main {

    public static void main(String[] args) {
        Behavior<Object> root = Behaviors.setup(context -> {
            context.spawn(DeviceCenter.create(), "device-center");
            return Behaviors.empty();
        });

        ActorSystem.create(root, "device-system");

    }

}
