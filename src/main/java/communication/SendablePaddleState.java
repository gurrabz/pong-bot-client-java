package communication;

import communication.ClientId;
import pong.Paddle;

public class SendablePaddleState {
    private final Paddle.State state;
    private final ClientId id;

    public SendablePaddleState(Paddle.State state, ClientId id) {
        this.state = state;
        this.id = id;
    }
}