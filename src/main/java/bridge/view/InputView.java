package bridge.view;

import bridge.domain.GameCommand;
import bridge.domain.Move;

import static camp.nextstep.edu.missionutils.Console.readLine;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        System.out.println("다리의 길이를 입력해주세요.");
        try {
            return Integer.parseInt(readLine().trim());
        } catch (NumberFormatException error) {
            System.out.println("[ERROR] 다리 길이는 숫자값으로만 입력되어야 합니다. 재입력 해주세요.");
            return readBridgeSize();
        }
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public Move readMoving() {
        System.out.println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
        try{
            return Move.of(readLine().trim());
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return readMoving();
        }
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public GameCommand readGameCommand() {
        System.out.println("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
        try{
            return GameCommand.of(readLine().trim());
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return readGameCommand();
        }
    }
}
