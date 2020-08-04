package homework.meetakka;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import homework.meetakka.message.DeviceCenterCommand;

public class DeviceCenter extends AbstractBehavior<DeviceCenterCommand> {

    public static Behavior<DeviceCenterCommand> create() {
        return Behaviors.setup(DeviceCenter::new);
    }

    public DeviceCenter(ActorContext<DeviceCenterCommand> context) {
        super(context);
    }

    @Override
    public Receive<DeviceCenterCommand> createReceive() {
        // write your code here
        return newReceiveBuilder().build();
    }
}
