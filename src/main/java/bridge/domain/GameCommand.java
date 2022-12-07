package bridge.domain;

import java.util.Arrays;

public enum GameCommand {
    RETRY("R"),
    QUIT("Q");

    private static final String GAME_COMMAND_ERROR = "[ERROR] 입력값은 R / Q 중 하나만 입력이 가능합니다. 재입력 해주세요.";

    private final String command;

    GameCommand(String command){
        this.command = command;
    }

    public static GameCommand of(String command){
        return Arrays.stream(values())
                .filter(gameCommand -> gameCommand.command.equals(command))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException(GAME_COMMAND_ERROR));
    }
}
