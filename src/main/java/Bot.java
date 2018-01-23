import pong.GameState;
import pong.Paddle;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Bot implements Player {

    @Override
    public Paddle.State play(GameState state) {
        // Enter your bot logic here...

        // Example...
        return Paddle.State.MOVE_CLOCKWISE;
    }

    public Bot() {

    }

}