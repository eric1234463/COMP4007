package kiosk;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class QueueManager {
    //    private static final ScheduledExecutorService queueThreadsSchuduled = Executors.newScheduledThreadPool(5);
    private ArrayList<Queue> queues = new ArrayList<Queue>();

    public Queue getQueue(int index){
        return this.queues.get(index);
    }

    public void addQueues(Queue queue) {
        this.queues.add(queue);
    }
}
