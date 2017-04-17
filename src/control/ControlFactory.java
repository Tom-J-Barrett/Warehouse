package control;

import database.Database;

public final class ControlFactory {

	private static final Database db = new Database();

	public static Scrap getScrap() {
		return new Scrap(db);
	}
}
