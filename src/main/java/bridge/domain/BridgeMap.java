package bridge.domain;

import java.util.ArrayList;
import java.util.List;

public class BridgeMap {
    private static final String EMPTY_SIGN = "   ";
    private static final String MAP_FORMAT = "[%s]\n";
    private static final String MAP_DELIMITER = "|";

    private final List<String> upperMap = new ArrayList<>();
    private final List<String> lowerMap = new ArrayList<>();

    public void addLog(Move move, String sign){
        if(move.equals(Move.UP)){
            addUpperLog(sign);
            return;
        }
        addLowerLog(sign);
    }

    private void addUpperLog(String sign){
        upperMap.add(sign);
        lowerMap.add(EMPTY_SIGN);
    }

    private void addLowerLog(String sign){
        lowerMap.add(sign);
        upperMap.add(EMPTY_SIGN);
    }

    @Override
    public String toString(){
        return String.format(MAP_FORMAT, String.join(MAP_DELIMITER, upperMap)) +
                String.format(MAP_FORMAT, String.join(MAP_DELIMITER, lowerMap));
    }
}
