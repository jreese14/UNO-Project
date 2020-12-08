package UNO.Controller;

import UNO.Message;

/**
 * Interface for a valve used to execute messages in the queue
 */
public interface Valve {
    public ValveResponse execute(Message message);
}
