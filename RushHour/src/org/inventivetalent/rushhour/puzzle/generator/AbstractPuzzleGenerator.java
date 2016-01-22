package org.inventivetalent.rushhour.puzzle.generator;

import org.inventivetalent.rushhour.car.Variant;
import org.inventivetalent.rushhour.puzzle.Direction;
import org.inventivetalent.rushhour.puzzle.Puzzle;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class AbstractPuzzleGenerator {

	public void generateBase() {
	}

	public void loadPuzzle(Puzzle puzzle) {
	}

	public void loadPuzzle(File file) throws IOException {
		loadPuzzle(Puzzle.fromJson(new FileReader(file)));
	}

	public abstract void setCar(int x, int y, Variant variant,Direction moveDirection, CarInteractListener listener);


	public void clearCars() {
	}

	public void resetListeners() {
	}

	public void updateCars() {
	}

}