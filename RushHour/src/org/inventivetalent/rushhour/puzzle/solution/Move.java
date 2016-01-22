package org.inventivetalent.rushhour.puzzle.solution;

import org.inventivetalent.rushhour.car.Variant;
import org.inventivetalent.rushhour.puzzle.Direction;
import org.inventivetalent.rushhour.puzzle.GameCar;
import org.inventivetalent.rushhour.puzzle.Puzzle;

public class Move {

	public final Variant   variant;
	public final Direction direction;
	public final int       moves;

	//Only for moves performed by a player
	public long timestamp = 0L;

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

	public String asString() {
		return new StringBuilder().append(this.variant.getIdentifier()).append(this.direction.getCode()).append(this.moves).toString();
	}

	public void executeMove(Puzzle puzzle) {
		GameCar targetCar = null;
		for (GameCar car : puzzle.cars) {
			if (car.variant == this.variant) { targetCar = car; }
		}
		if (targetCar == null) { throw new IllegalStateException("The puzzle does not contain the car of this move"); }
		for (int i = 0; i < this.moves; i++) {
			puzzle.moveCar(targetCar, this.direction);
		}
	}

	public void executeSingleMove(Puzzle puzzle) {
		GameCar targetCar = null;
		for (GameCar car : puzzle.cars) {
			if (car.variant == this.variant) { targetCar = car; }
		}
		if (targetCar == null) { throw new IllegalStateException("The puzzle does not contain the car of this move"); }
		puzzle.moveCar(targetCar, this.direction);
	}

	@Override
	public String toString() {
		return "Move[ " + asString() + " ]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) { return false; }

		Move move = (Move) o;

		if (moves != move.moves) { return false; }
		if (timestamp != move.timestamp) { return false; }
		if (variant != move.variant) { return false; }
		return direction == move.direction;

	}

	@Override
	public int hashCode() {
		int result = variant != null ? variant.hashCode() : 0;
		result = 31 * result + (direction != null ? direction.hashCode() : 0);
		result = 31 * result + moves;
		result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}
}
