package org.inventivetalent.rushhour.puzzle.solution;

import com.google.gson.*;
import org.bukkit.entity.Player;
import org.inventivetalent.rushhour.car.Variant;
import org.inventivetalent.rushhour.puzzle.Direction;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Solution {

	public final List<Move> moves = new ArrayList<>();

	public void trackPlayerMove(Player player, Move move) {
		move.timestamp = System.currentTimeMillis();
		moves.add(move);
	}

	public void trackPlayerMove(Player player, Variant variant, Direction direction, int moves) {
		trackPlayerMove(player, new Move(variant, direction, moves));
	}

	public static class Serializer implements JsonDeserializer<Solution> {

		@Override
		public Solution deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
			Solution solution = new Solution();
			if (jsonElement.isJsonArray()) {
				JsonArray jsonArray = jsonElement.getAsJsonArray();
				for (JsonElement element : jsonArray) {
					Move move;
					if (element.isJsonObject()) {
						move = new Gson().fromJson(element, Move.class);
					} else {
						move = Move.parse(element.getAsString());
					}

					solution.moves.add(move);
				}
			}
			return solution;
		}
	}

}
