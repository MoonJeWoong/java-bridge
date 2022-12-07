package bridge.domain;

import bridge.BridgeMaker;

import static bridge.domain.bridgeGameConstants.FAIL_SIGN;
import static bridge.domain.bridgeGameConstants.PRINT_FAIL;
import static bridge.domain.bridgeGameConstants.PRINT_SUCCESS;

import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private static final int INITIAL_ATTEMPT = 0;
    private static final int INDEX_ZERO = 0;



    private final Bridge systemBridge;
    private Bridge userBridge = new Bridge();
    private int totalAttempt = INITIAL_ATTEMPT;

    public BridgeGame(BridgeMaker bridgeMaker, int size){
        systemBridge = new Bridge(bridgeMaker.makeBridge(size));
        totalAttempt++;
    }
    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(Move move) {
        userBridge.addMove(move);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        userBridge = new Bridge();
        totalAttempt++;
    }

    public BridgeMap makeBridgeMap(){
        BridgeMap bridgeMap = new BridgeMap();
        List<String> compareResult = systemBridge.makeCompareResult(userBridge);
        int index = INDEX_ZERO;
        while(index < compareResult.size()){
            bridgeMap.addLog(userBridge.getMove(index), compareResult.get(index));
            index++;
        }
        return bridgeMap;
    }

    public boolean isCompleted(){
        List<String> compareResult = systemBridge.makeCompareResult(userBridge);
        return compareResult.size() == systemBridge.size()
                && !compareResult.contains(FAIL_SIGN);
    }

    public boolean isOver(){
        return systemBridge.makeCompareResult(userBridge).contains(FAIL_SIGN);
    }

    public String getPrintableResult(){
        if(isCompleted()){
            return PRINT_SUCCESS;
        }
        return PRINT_FAIL;
    }

    public int getTotalAttempt(){
        return totalAttempt;
    }
}
