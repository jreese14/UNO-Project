package UNO;

import UNO.Controller.Controller;
import UNO.Model.Model;
import UNO.View.View;

import javax.swing.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
/**
* Class that runs the main loop
 */
public class UNO {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();

        Model model = new Model();
        View view = new View(queue, model);

        Controller controller = new Controller(view, model, queue);
        controller.mainLoop();
    }
}