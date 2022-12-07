package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.domain.BridgeGame;
import bridge.domain.GameCommand;
import bridge.view.InputView;
import bridge.view.OutputView;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeMaker bridgeMaker;

    private BridgeGame bridgeGame;

    public Controller(InputView inputView, OutputView outputView, BridgeNumberGenerator generator){
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeMaker = new BridgeMaker(generator);
    }

    public void run(){
        bridgeGame = initBridgeGame();
        playGame();
        showGameResult();
    }

    private BridgeGame initBridgeGame(){
        try{
            return new BridgeGame(bridgeMaker, inputView.readBridgeSize());
        } catch (IllegalArgumentException error) {
            outputView.printError(error.getMessage());
            return initBridgeGame();
        }
    }

    private void playGame(){
        GameCommand command = null;
        while(!bridgeGame.isCompleted() && !GameCommand.QUIT.equals(command)){
            bridgeGame.move(inputView.readMoving());
            outputView.printMap(bridgeGame.makeBridgeMap());
            if(bridgeGame.isOver()){
                command = inputView.readGameCommand();
                resetGame(command);
            }
        }
    }

    private void resetGame(GameCommand command){
        if(command.equals(GameCommand.RETRY)){
            bridgeGame.retry();
        }
    }

    private void showGameResult(){
        outputView.printResult(bridgeGame.makeBridgeMap(), bridgeGame.getPrintableResult(),
                bridgeGame.getTotalAttempt());
    }
}
