package multiThread;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

import java.io.IOException;

public class AkkaMutableStateInActor {
    public static class Counter extends AbstractLoggingActor {
        public Counter() {
            receive(ReceiveBuilder
                    .match(Message.class, this::onMessage)
                    .build());
        }

        //protocol
        static class Message {
            public int val = 1;
        }

        private int counter = 0;

        public void onMessage(Message message) {
            counter = counter + message.val;
            log().info("Increased counter : " + counter);
        }

        public static Props props() {
            return Props.create(Counter.class);
        }
    }

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("MutableStateInActor");

        final ActorRef counter = system.actorOf(Counter.props(), "Counter");

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        counter.tell(new Counter.Message(), ActorRef.noSender());
                    }
                }).start();
            }
        }
        System.in.read(new byte[1]);
        system.shutdown();
    }
}
