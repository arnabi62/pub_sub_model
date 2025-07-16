import java.util.List;

public class Subscriber implements Runnable{
    private String id;
    private Broker broker;

    public Subscriber(String id, Broker broker) {
        this.broker = broker;
        this.id = id;
    }


    @Override
    public void run() {
        List<Topic> subscribedTopic = broker.getSubscriberTopic(id);
        if(subscribedTopic.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for(Topic topic: subscribedTopic) {
            try {
                Message msg = topic.getMessage();
                System.out.println("received msg : " + msg.getName() + " by " + id);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
