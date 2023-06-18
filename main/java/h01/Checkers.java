package h01;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.RobotFamily;
import fopbot.World;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.tudalgo.algoutils.student.Student;
import static org.tudalgo.algoutils.student.Student.crash;
import static org.tudalgo.algoutils.student.io.PropertyUtils.getIntProperty;

/**
 * {@link Checkers} is a simplified version of Checkers, implemented in FOPBot.
 */
public class Checkers {

    /**
     * The number of rows in the game board.
     */
    public static final int NUMBER_OF_ROWS = getIntProperty("checkers.properties", "NUMBER_OF_ROWS");

    /**
     * The number of columns in the game board.
     */
    public static final int NUMBER_OF_COLUMNS = getIntProperty("checkers.properties", "NUMBER_OF_COLUMNS");

    /**
     * The minimum initial number of coins for a black stone.
     */
    public static final int MIN_NUMBER_OF_COINS = getIntProperty("checkers.properties", "MIN_NUMBER_OF_COINS");

    /**
     * The maximum initial number of coins for a black stone.
     */
    public static final int MAX_NUMBER_OF_COINS = getIntProperty("checkers.properties", "MAX_NUMBER_OF_COINS");

    /**
     * The current state of the game.
     * At the start of the game, the state of the game is set to {@link GameState#RUNNING}.
     * After the game has finished, the state of the game is set to {@link GameState#BLACK_WIN} or {@link GameState#WHITE_WIN}.
     */
    private GameState gameState = GameState.RUNNING;


    /**
     * The robot of the white team.
     */
    private Robot whiteStone;

    /**
     * The robots of the black team.
     */
    private Robot blackStone0, blackStone1, blackStone2, blackStone3, blackStone4;

    /**
     * Runs the initialization of the game.
     * The initialization of the game consists of the initialization of the world and all stones.
     */
    public void initGame() {
        Student.setCrashEnabled(false);
        // initialize the world
        World.setSize(NUMBER_OF_COLUMNS, NUMBER_OF_ROWS);
        // initialize all stones
        initWhiteStone();
        initBlackStones();
    }

    /**
     * Runs the game. After the game has finished, the winner of the game will be printed to the console.
     */
    public void runGame() {
        World.setVisible(true);
        while (isRunning()) {
            doBlackTeamActions();
            doWhiteTeamActions();
            updateGameState();
        }
        System.out.printf("Final State: %s%n", gameState);
    }

    /**
     * Returns {@code true} if the game is running, {@code false} otherwise.
     *
     * @return if the game is running
     */
    public boolean isRunning() {
        return gameState == GameState.RUNNING;
    }

    /**
     * Runs the initialization of the white stone.
     */
    public void initWhiteStone() {
        int randomDirectionValue = getRandom().nextInt(0,4);
        Direction direct = switch (randomDirectionValue) {
            case 0 -> Direction.UP;
            case 1 -> Direction.DOWN;
            case 2 -> Direction.LEFT;
            case 3 -> Direction.RIGHT;
            default -> null;
        };

        int x ;
        int y ;
        do
        {
            x = getRandom().nextInt(0,NUMBER_OF_COLUMNS);
            y = getRandom().nextInt(0,NUMBER_OF_ROWS);

        }
        while((x + y) % 2 ==0 );

        whiteStone = new Robot(x,y,direct,0, RobotFamily.SQUARE_WHITE);


    }

    /**
     * Runs the initialization of all black stones.
     */
    public void initBlackStones() {
        int x = whiteStone.getX();
        int y = whiteStone.getY();
        /**
         * Robot 0
         * */
        int x0 ;
        int y0 ;
        do
        {
            x0 = getRandom().nextInt(0,NUMBER_OF_COLUMNS);
            y0 = getRandom().nextInt(0,NUMBER_OF_ROWS);
        }

        while((x0 + y0) % 2 ==0  || (x0 == x && y0 == y));

        int coinNumber0 = getRandom().nextInt(MIN_NUMBER_OF_COINS,MAX_NUMBER_OF_COINS + 1);
        int randomDirectionValue0 = getRandom().nextInt(0,4);
        Direction direct0 = null;

        if(randomDirectionValue0 == 0)
        {
            direct0 = Direction.UP;
        }
        if(randomDirectionValue0 == 1)
        {
            direct0 = Direction.DOWN;
        }
        if(randomDirectionValue0 == 2)
        {
            direct0 = Direction.LEFT;
        }
        if(randomDirectionValue0 == 3)
        {
            direct0 = Direction.RIGHT;
        }
        blackStone0 = new Robot(x0,y0,direct0,coinNumber0,RobotFamily.SQUARE_BLACK);
        System.out.println(coinNumber0);

        /**
         * Robot 1
         * */
        int x1 ;
        int y1 ;
        do
        {
            x1 = getRandom().nextInt(0,NUMBER_OF_COLUMNS);
            y1 = getRandom().nextInt(0,NUMBER_OF_ROWS);
        }

        while((x1 + y1) % 2 ==0  || (x1 == x && y1 == y));

        int coinNumber1 = getRandom().nextInt(MIN_NUMBER_OF_COINS,MAX_NUMBER_OF_COINS + 1);
        int randomDirectionValue1 = getRandom().nextInt(0,4);
        Direction direct1 = null;

        if(randomDirectionValue1 == 0)
        {
            direct1 = Direction.UP;
        }
        if(randomDirectionValue1 == 1)
        {
            direct1 = Direction.DOWN;
        }
        if(randomDirectionValue1 == 2)
        {
            direct1 = Direction.LEFT;
        }
        if(randomDirectionValue1 == 3)
        {
            direct1 = Direction.RIGHT;
        }
        blackStone1 = new Robot(x1,y1,direct1,coinNumber1,RobotFamily.SQUARE_BLACK);
        System.out.println(coinNumber1);

        /**
         * Robot 2
         */
        int x2 ;
        int y2 ;
        do
        {
            x2 = getRandom().nextInt(0,NUMBER_OF_COLUMNS);
            y2 = getRandom().nextInt(0,NUMBER_OF_ROWS);
        }

        while((x2 + y2) % 2 ==0  || (x2 == x && y2 == y));

        int coinNumber2 = getRandom().nextInt(MIN_NUMBER_OF_COINS,MAX_NUMBER_OF_COINS +1);
        int randomDirectionValue2 = getRandom().nextInt(0,4);
        Direction direct2 = null;

        if(randomDirectionValue2 == 0)
        {
            direct2 = Direction.UP;
        }
        if(randomDirectionValue2 == 1)
        {
            direct2 = Direction.DOWN;
        }
        if(randomDirectionValue2 == 2)
        {
            direct2 = Direction.LEFT;
        }
        if(randomDirectionValue2 == 3)
        {
            direct2 = Direction.RIGHT;
        }
        blackStone2 = new Robot(x2,y2,direct2,coinNumber2,RobotFamily.SQUARE_BLACK);

        /**
         * Robot 3
         */
        int x3 ;
        int y3 ;
        do
        {
            x3 = getRandom().nextInt(0,NUMBER_OF_COLUMNS);
            y3 = getRandom().nextInt(0,NUMBER_OF_ROWS);
        }

        while((x3 + y3) % 2 ==0  || (x3 == x && y3 == y));

        int coinNumber3 = getRandom().nextInt(MIN_NUMBER_OF_COINS,MAX_NUMBER_OF_COINS +1 );
        int randomDirectionValue3 = getRandom().nextInt(0,4);
        Direction direct3 = null;

        if(randomDirectionValue3 == 0)
        {
            direct3 = Direction.UP;
        }
        if(randomDirectionValue3 == 1)
        {
            direct3 = Direction.DOWN;
        }
        if(randomDirectionValue3 == 2)
        {
            direct3 = Direction.LEFT;
        }
        if(randomDirectionValue3 == 3)
        {
            direct3 = Direction.RIGHT;
        }
        blackStone3 = new Robot(x3,y3,direct3,coinNumber3,RobotFamily.SQUARE_BLACK);

        /**
         * Robot 4
         */
        int x4 ;
        int y4 ;
        do
        {
            x4 = getRandom().nextInt(0,NUMBER_OF_COLUMNS);
            y4 = getRandom().nextInt(0,NUMBER_OF_ROWS);
        }

        while((x4 + y4) % 2 ==0  || (x4 == x && y4 == y));

        int coinNumber4 = getRandom().nextInt(MIN_NUMBER_OF_COINS,MAX_NUMBER_OF_COINS + 1);
        int randomDirectionValue4 = getRandom().nextInt(0,4);
        Direction direct4 = null;

        if(randomDirectionValue4 == 0)
        {
            direct4 = Direction.UP;
        }
        if(randomDirectionValue4 == 1)
        {
            direct4 = Direction.DOWN;
        }
        if(randomDirectionValue4 == 2)
        {
            direct4 = Direction.LEFT;
        }
        if(randomDirectionValue4 == 3)
        {
            direct4 = Direction.RIGHT;
        }
        blackStone4 = new Robot(x4,y4,direct4,coinNumber4,RobotFamily.SQUARE_BLACK);

    }




    /**
     * Runs the action of the black team.
     */
    public void doBlackTeamActions() {
        Robot chosenBot = null;
        do
        {
            int chosenBotNum = getRandom().nextInt(0,5);

            if(chosenBotNum == 0){
                chosenBot = blackStone0;

            }
            if (chosenBotNum == 1){
                chosenBot = blackStone1;

            }
            if(chosenBotNum == 2){
                chosenBot = blackStone2;

            }
            if (chosenBotNum == 3){
                chosenBot = blackStone3;

            }
            if(chosenBotNum == 4){
                chosenBot = blackStone4;

            }
        }while(!chosenBot.hasAnyCoins() || chosenBot.isTurnedOff());


        /**
         * Robot
         * */
        int x0 ;
        int y0 ;
        int a0 = 0;
        int b0 = 0;

        int Moving = 1;
        while( Moving == 1 )
        {
            if(!chosenBot.hasAnyCoins()){
                break;
            }

            if(chosenBot.isFacingUp()){
                a0 = 1;
                b0 = 1;
            }
            else if(chosenBot.isFacingDown()){
                a0 = -1;
                b0 = -1;
            }
            else if(chosenBot.isFacingLeft()){
                a0 = -1;
                b0 = 1;
            }
            else if(chosenBot.isFacingRight()){
                a0 = 1;
                b0 = -1;
            }
            x0 = chosenBot.getX() + a0;
            y0 = chosenBot.getY() + b0;
            if(x0 == whiteStone.getX() && y0 == whiteStone.getY()){
                chosenBot.turnLeft();
            } else if (x0 == -1 || y0 == -1 || x0 == NUMBER_OF_COLUMNS || y0 == NUMBER_OF_ROWS) {
                chosenBot.turnLeft();
            }
            else {
                chosenBot.putCoin();
                chosenBot.move();
                //turn right
                for (int i = 0; i < 3; i++){
                    chosenBot.turnLeft();
                }
                chosenBot.move();
                System.out.println(blackStone0.getNumberOfCoins());
                Moving = 0;
            }

        }
    }

    /**
     * Runs the action of the white team.
     */
    public void doWhiteTeamActions() {
        Robot[] blackstoneX = {blackStone0, blackStone1, blackStone2, blackStone3, blackStone4};

        for (int i = 0; i < 5 ; i++) {
            int xB0 = blackstoneX[i].getX();
            int yB0 = blackstoneX[i].getY();
            int x_test = whiteStone.getX();
            int y_test = whiteStone.getY();
            int xDiffer = xB0 - x_test;
            int yDiffer = yB0 - y_test;

            if(xB0 < NUMBER_OF_COLUMNS - 1 && yB0 < NUMBER_OF_ROWS - 1 && xB0 > 0 && yB0 > 0){
                //up right
                if ((xDiffer > 0) && (xDiffer == yDiffer) && blackstoneX[i].isTurnedOn()) {
                    blackstoneX[i].turnOff();
                    x_test = xB0 + 1;
                    y_test = yB0 + 1;
                    whiteStone.setX(x_test);
                    whiteStone.setY(y_test);
                    break;
                }
                //down left
                else if ((xDiffer < 0) && (xDiffer == yDiffer) && blackstoneX[i].isTurnedOn()) {
                    blackstoneX[i].turnOff();
                    x_test = xB0 - 1;
                    y_test = yB0 - 1;
                    whiteStone.setX(x_test);
                    whiteStone.setY(y_test);
                    break;
                }
                //up left
                else if ((xDiffer < 0) && (xDiffer == -yDiffer) && blackstoneX[i].isTurnedOn()) {
                    blackstoneX[i].turnOff();
                    x_test = xB0 - 1;
                    y_test = yB0 + 1;
                    whiteStone.setX(x_test);
                    whiteStone.setY(y_test);
                    break;
                }
                //down right
                else if ((xDiffer > 0) && (xDiffer == -yDiffer) && blackstoneX[i].isTurnedOn()) {
                    blackstoneX[i].turnOff();
                    x_test = xB0 + 1;
                    y_test = yB0 - 1;
                    whiteStone.setX(x_test);
                    whiteStone.setY(y_test);
                    break;
                }
            }
        }
    }

    /**
     * Checks if a team has won the game and, if so, updates the game state to {@link GameState#BLACK_WIN} or {@link GameState#WHITE_WIN}.
     */
    public void updateGameState() {
        if(blackStone0.isTurnedOff() && blackStone1.isTurnedOff() && blackStone2.isTurnedOff() &&
            blackStone3.isTurnedOff() && blackStone4.isTurnedOff())
        {
            gameState = GameState.WHITE_WIN;

        }
        else if((blackStone0.isTurnedOn() && !blackStone0.hasAnyCoins()) &&
            (blackStone1.isTurnedOn() && !blackStone1.hasAnyCoins()) &&
            (blackStone2.isTurnedOn() && !blackStone2.hasAnyCoins()) &&
            (blackStone3.isTurnedOn() && !blackStone3.hasAnyCoins()) &&
            (blackStone4.isTurnedOn() && !blackStone4.hasAnyCoins()) )
        {
            gameState = GameState.BLACK_WIN;

        }
        else {
            gameState = GameState.RUNNING;
        }
    }

    /**
     * Returns an instance of {@link Random}.
     *
     * @return an instance of {@link Random}
     */
    private Random getRandom() {
        return ThreadLocalRandom.current();
    }
}
