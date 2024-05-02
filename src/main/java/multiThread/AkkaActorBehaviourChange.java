package multiThread;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

import java.io.IOException;

public class AkkaActorBehaviourChange {
    public static class Alarm extends AbstractLoggingActor {
        private final String password;
        //Two activities are possible on this Alarm system {ENABLE/DISABLE}
        //They are defines as partial functions.
        private final PartialFunction<Object, BoxedUnit> enabled;
        private final PartialFunction<Object, BoxedUnit> disabled;

        public Alarm(String password) {
            this.password = password;

            enabled = ReceiveBuilder
                    .match(Activity.class, this::onActivity)
                    .match(Disable.class, this::onDisable)
                    .build();

            disabled = ReceiveBuilder
                    .match(Enable.class, this::onEnable)
                    .build();

            receive(disabled);
        }

        public void onActivity(Activity temp) {
            log().info("Activity***Some Activity at Home***");
        }

        public void onDisable(Disable dis) {
            if (this.password == dis.password) {
                log().info("Disable***Disabling the alarm***");
                getContext().become(disabled);
            } else
                log().info("Disable***Wrong password sounding alarm***");
        }

        public void onEnable(Enable enb) {
            if (this.password == enb.password) {
                log().info("Enable***Enabling the alarm***");
                getContext().become(enabled);
            } else
                log().info("Enable***Wrong password sounding alarm***");
        }

        public static class Activity {
        }

        public static class Disable {
            private final String password;

            public Disable(String password) {
                this.password = password;
            }
        }

        public static class Enable {
            private final String password;

            public Enable(String password) {
                this.password = password;
            }
        }

        public static Props props(String password) {
            return Props.create(Alarm.class, password);
        }
    }

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("ActorBehaviourChange");

        final ActorRef alarm = system.actorOf(AkkaActorBehaviourChange.Alarm
                .props("study"), "ActorBehaviourChange");

        alarm.tell(new Alarm.Activity(), ActorRef.noSender());
        alarm.tell(new Alarm.Enable("dog"), ActorRef.noSender());
        alarm.tell(new Alarm.Enable("study"), ActorRef.noSender());
        alarm.tell(new Alarm.Activity(), ActorRef.noSender());
        alarm.tell(new Alarm.Disable("dog"), ActorRef.noSender());
        alarm.tell(new Alarm.Disable("study"), ActorRef.noSender());
        alarm.tell(new Alarm.Activity(), ActorRef.noSender());


        System.in.read(new byte[1]);
        system.shutdown();
    }
}
