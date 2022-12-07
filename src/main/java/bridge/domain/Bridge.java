package bridge.domain;

import static bridge.domain.bridgeGameConstants.FAIL_SIGN;
import static bridge.domain.bridgeGameConstants.MAX_SIZE;
import static bridge.domain.bridgeGameConstants.MIN_SIZE;
import static bridge.domain.bridgeGameConstants.PASS_SIGN;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Bridge {
    private static final int INDEX_ZERO = 0;
    private static final String BRIDGE_SIZE_ERROR = "[ERROR] 다리 길이는 %d부터 %d 사이의 값이어야 합니다. 재입력 해주세요.";

    private final List<Move> bridge;

    public Bridge(List<String> bridge){
        validateBridgeSize(bridge);
        this.bridge = bridge.stream().map(Move::of).collect(Collectors.toList());
    }

    public Bridge(){
        bridge = new ArrayList<>();
    }

    public void addMove(Move move){
        bridge.add(move);
    }

    public Move getMove(int index){
        return bridge.get(index);
    }

    public int size(){
        return bridge.size();
    }

    public List<String> makeCompareResult(Bridge other){
        int shorterSize = Math.min(this.bridge.size(), other.bridge.size());
        return IntStream.range(INDEX_ZERO, shorterSize)
                .mapToObj(index->isEqualAtIndex(index, other.bridge.get(index)))
                .map(this::convertToSign)
                .collect(Collectors.toList());
    }

    private boolean isEqualAtIndex(int index, Move value){
        return bridge.get(index).equals(value);
    }

    private String convertToSign(boolean isPassSign){
        if(isPassSign){
            return PASS_SIGN;
        }
        return FAIL_SIGN;
    }

    private void validateBridgeSize(List<String> bridge){
        if(bridge.size()>MAX_SIZE || bridge.size()<MIN_SIZE){
            throw new IllegalArgumentException(String.format(BRIDGE_SIZE_ERROR,MIN_SIZE,MAX_SIZE));
        }
    }
}
