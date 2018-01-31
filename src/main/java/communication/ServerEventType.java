package communication;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public enum ServerEventType {

	START_GAME,
	END_GAME,
	GAME_STATE;

	ServerEventType() {

	}

}
