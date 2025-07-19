//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Broker broker = new Broker();

       Topic t1 =  broker.createTopic("T1");
       Topic t2 = broker.createTopic("T2");

        Publisher p1 = new Publisher(broker);

        Thread th1 = new Thread(new Subscriber("S1", broker));
        Thread th2 = new Thread(new Subscriber("S2", broker));

        broker.subscribeTopic("S1", t1);
        broker.subscribeTopic("S1", t2);
        broker.subscribeTopic("S2", t2);

        th1.start();
        th2.start();

        p1.publish(t1.getName(), new Message("message1 for topic T1"));
        p1.publish(t2.getName(), new Message("message2 for topic T2"));
        p1.publish(t2.getName(), new Message("message1 for topic T2"));

        try {
            Thread.sleep(100);
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }





    }
}