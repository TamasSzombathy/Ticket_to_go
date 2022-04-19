package jegyrendszer;

public class TrainNumberTakenException extends Exception{
	/**
	 * Egy kiv�telt�pus, azt a hib�t jelzi ha dobj�k, hogy az adott vonatsz�mmal m�r l�tezik vonat a nyilv�ntart�sban.
	 */
	protected final String message = "Ilyen vonatsz�mmal m�r l�tezik vonat.";
	/**
	 * A string hiba�zenetet (message) adja vissza.
	 */
	public String getMessage() {
		return this.message;
	}
}
