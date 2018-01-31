package communication;

import com.google.gson.*;

public class ServerEventParser {

	private static final String EVENT_TYPE_MEMBER_NAME         = "event";
	public static final String AFFECTED_PLAYERS_MEMBER_NAME    = "player_ids";

	private static final JsonParser parser = new JsonParser();
	private static JsonElement jElement;

	/**
	 * Turns the given input into a {@link JsonObject} if possible.
	 * @param input the String representation of the JsonObject wanted.
	 * @return the JsonObject representing the input if the input is a json object.
	 * @throws JsonSyntaxException if parsing the input fails.
	 */
	public static JsonObject parse(String input) throws JsonSyntaxException {
		jElement = parser.parse(input);
		if(jElement.isJsonObject()) {
			return jElement.getAsJsonObject();
		} else {
			return null;
		}
	}

	/**
	 * Determines the {@link ServerEventType} of the eventObject.
	 * @param eventObject
	 * @return the ServerEventType of the eventObject.
	 * @throws JsonParseException if the eventObject couldn't be parsed into a ServerEventType.
	 */
	public static ServerEventType getServerEventType(JsonObject eventObject) throws JsonParseException {
		if(eventObject == null) throw new IllegalArgumentException("null argument");
		if(eventObject.has(EVENT_TYPE_MEMBER_NAME)){
			JsonElement el = eventObject.get(EVENT_TYPE_MEMBER_NAME);
			try {
				return ServerEventType.valueOf(el.getAsString().toUpperCase());
			} catch (IllegalArgumentException e) {
				throw new JsonParseException("Couldn't parse '" + EVENT_TYPE_MEMBER_NAME + "' into a ServerEventType!", e);
			}
		} else {
			throw new JsonParseException("Not a ServerEvent!");
		}
	}
}
