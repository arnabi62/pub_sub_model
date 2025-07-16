public class Publisher {

    private Broker broker;

    public Publisher(Broker broker) {
        this.broker = broker;
    }

    public void publish(String topicName, Message msg) {
        broker.publish(topicName, msg);
    }
}
