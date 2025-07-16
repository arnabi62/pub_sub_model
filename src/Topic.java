import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Topic {

    private String name;
    private BlockingQueue<Message> messagesList = new LinkedBlockingQueue<>();

    public Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addMessage(Message msg){
        this.messagesList.add(msg);
    }

    public Message getMessage() throws InterruptedException {
        return this.messagesList.take();
    }

}
