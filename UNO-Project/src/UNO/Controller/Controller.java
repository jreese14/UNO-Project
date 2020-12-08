package UNO.Controller;

import UNO.ClickMessage;
import UNO.Message;
import UNO.Model.Model;
import UNO.NewGameMessage;
import UNO.View.View;
import UNO.Model.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Class for implementing the game using a queue and valves to take messages from CardView and execute using the model
 */
public class Controller {

    BlockingQueue<Message> queue;
    Model model;
    View view;
    // GameInfo gameInfo;
    private List<Valve> valves = new ArrayList<Valve>();

    public Controller(View view, Model model, BlockingQueue<Message> queue) {
        this.model = model;
        // this.gameInfo = new GameInfo(model);
        this.view = view;
        this.queue = queue;
        this.valves.add(new NewGameValve());
        this.valves.add(new ClickValve());

    }

    /**
     * Method keeps taking messages from queue and executes them with valves
     * @throws Exception if you can't take a message from the queue
     */
    public void mainLoop() throws Exception {
        ValveResponse response = ValveResponse.EXECUTED;
        Message message = null;
        while (response != ValveResponse.FINISH) {
            try {
                message = (Message) queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (Valve valve : valves) {
                response = valve.execute(message);
                if (response != ValveResponse.MISS)
                    break;
            }
        }
    }

    /**
     * Class for the valve when you click a card
     */
    private class ClickValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != ClickMessage.class) {
                return ValveResponse.MISS;
            }
            // System.out.println("2");
            ClickMessage c = (ClickMessage) message;
            // System.out.println("3");

            UNO.Model.Card card = c.getCard();
            if (model.playCard(card)) {
                view.update();
            }

            return ValveResponse.EXECUTED;
        }
    }

    /**
     * Class for the valve when you there is new game
     */
    private class NewGameValve implements Valve {

        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != NewGameMessage.class) {
                return ValveResponse.MISS;
            }
            queue.clear();
//            model = model.restartGame();
//            view.change(gameInfo);
            return ValveResponse.EXECUTED;

        }
    }

}
