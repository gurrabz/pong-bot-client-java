package communication;

import java.util.List;

/**
 * Represents a start_game event sent from the server and is used to convert the json received into a Java class which
 * can be easily used by the program.
 */
public class StartGameEvent {

    private String eventName;

    private List<Integer> affectedPlayers;

    public StartGameEvent() {

    }

    public boolean containsId(int id) {
    	return affectedPlayers.contains(id);
    }

}
