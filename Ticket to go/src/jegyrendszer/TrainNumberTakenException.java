package jegyrendszer;

public class TrainNumberTakenException extends Exception{
	/**
	 * Egy kivételtípus, azt a hibát jelzi ha dobják, hogy az adott vonatszámmal már létezik vonat a nyilvántartásban.
	 */
	protected final String message = "Ilyen vonatszámmal már létezik vonat.";
	/**
	 * A string hibaüzenetet (message) adja vissza.
	 */
	public String getMessage() {
		return this.message;
	}
}
