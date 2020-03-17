package Models.Port;

import com.task.ResourceException;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Port<T> {
    private final static int PORT_SIZE = 4;
    private final Semaphore semaphore = new Semaphore(PORT_SIZE, true);
    private final Queue<T> resources = new LinkedList<T>();

    public Port(Queue<T> source) {
        resources.addAll(source);
    }

    public T getResources() throws ResourceException {
        try {
            semaphore.acquire();
            T res = resources.poll();
            return res;

        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }

        throw new ResourceException(":error");
    }

    public void returnResources(T res) {
        resources.add(res);
        semaphore.release();
    }
}
