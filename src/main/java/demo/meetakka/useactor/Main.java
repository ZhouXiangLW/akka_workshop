package demo.meetakka.useactor;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.Terminated;
import akka.actor.typed.javadsl.Behaviors;
import demo.meetakka.useactor.message.Add;
import demo.meetakka.useactor.message.CountCommand;
import demo.meetakka.useactor.message.ShowCount;
import demo.meetakka.useactor.message.Sub;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        // Behavior通过工厂方法创建，这种写法是函数式风格
        Behavior<Object> root = Behaviors.setup(context -> {
            ActorRef<CountCommand> counter = context.spawn(CountActor.create(), "counter");
            // 被监控的actor停止时当前actor会收到Terminated信号
            context.watch(counter);
            sendMessage(counter);
            return Behaviors.receiveSignal((actorContext, signal) -> {
                // 被监控Actor停止自身也停止
                if (signal.getClass().equals(Terminated.class)) {
                    log.info("receive terminated signal");
                    return Behaviors.stopped();
                }
                return Behaviors.same();
            });
        });
        ActorSystem.create(root, "root");
    }

    private static void sendMessage(ActorRef<CountCommand> counter) throws InterruptedException {
        // 这里为了对比开启了多线程，在Actor里面请不要手动开启线程！！！
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.tell(new Add());
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 800; i++) {
                counter.tell(new Sub());
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        counter.tell(new ShowCount());
    }


}
