package bridge.domain;

import java.util.Arrays;

public enum Move {

    DOWN("D", 0),
    UP("U", 1);

    private static final String INVALID_DIRECTION_ERROR = "[ERROR] 이동 방향은 U / D 값으로만 입력 가능합니다.";
    private static final String INVALID_ZERO_ONE_ERROR = "[ERROR] 이동 방향은 0 / 1 값으로만 입력 가능합니다.";

    private final String direction;
    private final int zeroOne;

    Move(String direction, int zeroOne){
        this.direction = direction;
        this.zeroOne = zeroOne;
    }

    public static Move of(String direction){
        return Arrays.stream(values())
                .filter(move -> move.direction.equals(direction))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException(INVALID_DIRECTION_ERROR));
    }

    public static Move of(int zeroOne){
        return Arrays.stream(values())
                .filter(move -> move.zeroOne==zeroOne)
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException(INVALID_ZERO_ONE_ERROR));
    }

    public String getDirection(){
        return direction;
    }
}
