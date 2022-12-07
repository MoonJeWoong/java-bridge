package bridge;

import bridge.controller.Controller;
import bridge.view.InputView;
import bridge.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BridgeNumberGenerator generator = new BridgeRandomNumberGenerator();

        Controller controller = new Controller(inputView, outputView, generator);
        controller.run();
    }
}
