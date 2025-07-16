import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Broker {

    private Map<String, Topic> topicList = new ConcurrentHashMap<>();
    private Map<String, List<Topic>> subscriberToTopic = new ConcurrentHashMap<>();

    public Topic createTopic(String name) {
        Topic topic = new Topic(name);
        topicList.put(name, topic);
        System.out.println("Topic created: " + topic.getName());
        return topic;
    }

    public void subscribeTopic(String subscriberId, Topic topic) {
        if(!topicList.containsKey(topic.getName())) {
            System.out.println("Topic not found: " + topic.getName());
            return;
        }
        subscriberToTopic.computeIfAbsent(subscriberId, k -> new ArrayList<>()).add(topic);
    }

    public synchronized void publish(String topicName, Message msg) {
        topicList.get(topicName).addMessage(msg);
        notifyAll();
    }

    public List<Topic> getSubscriberTopic(String subscriptionId) {
        return subscriberToTopic.get(subscriptionId);
    }
}
