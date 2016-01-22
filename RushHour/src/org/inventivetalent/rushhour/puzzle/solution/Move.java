package org.inventivetalent.rushhour.puzzle.solution;

import org.inventivetalent.rushhour.car.Variant;
import org.inventivetalent.rushhour.puzzle.Direction;

public class Move {

	public Variant   variant;
	public Direction direction;
	public int       moves;

	public Move(Variant variant, Direction direction, int moves) {
		this.variant = variant;
		this.direction = direction;
		this.moves = moves;
	}

	public Move(char identifier, char code, int moves) {
		this.variant = Variant.getByIdentifier(identifier);
		this.direction = Direction.getByCode(code);
		this.moves = moves;
	}

	public static Move parse(String move) {
		String[] split = move.split("");
		if (split.length != 3) { throw new IllegalArgumentException(); }
		char identifier = split[0].charAt(0);
		char direction = split[1].charAt(0);
		int moves = Integer.parseInt(split[2]);

		return new Move(identifier, direction, moves);
	}

	//TODO: Execute move

}