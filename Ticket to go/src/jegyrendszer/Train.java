package jegyrendszer;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.io.Serializable;

public class Train implements Serializable{
	
	/**
	 * Vonatot reprezentáló osztály, implementálja a Serializable interfészt. Attribútumai:
	 * number: integer típusú, a vonat száma.
	 * wagons: ArrayList típusú, Wagon objektumokat (kocsikat) tárol ezzel szimulálva a vonatra csatolt kocsikat.
	 * startPoint: string típusú, az indulási állomás neve.
	 * startTime: LocalDateTime típusú, az indulás dátuma.
	 * endPoint: string típusú, a célállomás neve.
	 * endTime: LocalDateTime típusú, a célbaérés dátuma. 
	 */
	private int number;
	private ArrayList<Wagon> wagons = new ArrayList<>();
	private String startPoint;
	private LocalDateTime startTime;
	private String endPoint;
	private LocalDateTime endTime;
	
	/**
	 * Az osztály konstruktora.
	 * @param szam
	 * @param indulopont
	 * @param induloido
	 * @param vegpont
	 * @param vegido
	 */
	public Train(int szam,String indulopont, LocalDateTime induloido, String vegpont, LocalDateTime vegido) {
		this.number = szam;
		this.startPoint = indulopont;
		this.startTime = induloido;
		this.endPoint = vegpont;
		this.endTime = vegido;
	}
	
	/**
	 * Az osztály konstruktora, az idõpontokat string típusban veszi be és alakítja át LocalDateTime típusúvá.
	 * @param szam
	 * @param indulopont
	 * @param induloido
	 * @param vegpont
	 * @param vegido
	 */
	public Train(int szam, String indulopont, String induloido, String vegpont, String vegido) {
		this.number = szam;
		this.startPoint = indulopont;
		this.startTime = LocalDateTime.parse(induloido, TicketToGo.formatter);
		this.endPoint = vegpont;
		this.endTime = LocalDateTime.parse(vegido, TicketToGo.formatter);
	}
	
	/**
	 * A vonatszámot adja vissza, ami integer típusú.
	 * @return
	 */
	public int getNumber() {return this.number;}
	
	/**
	 * A kiinduló állomást nevét adja vissza, ami string típusú.
	 * @return
	 */
	public String getStartPoint() {return this.startPoint;}
	
	/**
	 * Az indulás idõpontját adja vissza, ami egy LocalTimDate objektum.
	 * @return
	 */
	public LocalDateTime getStartTime() {return this.startTime;}
	
	/**
	 * A célállomás nevét adja vissza, ami string típusú.
	 * @return
	 */
	public String getEndPoint() {return this.endPoint;}
	
	/**
	 * Az érkezés idõpontját adja vissza, ami egy LocalTimDate objektum.
	 * @return
	 */
	public LocalDateTime getEndTime() {return this.endTime;}

	
	public int getNumberOfWagons() {return this.wagons.size();}
	
	/**
	 * Az osztály toString metódusa, kiírja a vonat attribútumait.
	 */
	public String toString() {
		return ("Vonatszám: "+this.number+" Kiinduló állomás: "+this.startPoint+" Indulás dátuma: "+this.startTime.format(TicketToGo.formatter)+" Célállomás: "+this.endPoint+" Érkezési idõ: "+this.endTime.format(TicketToGo.formatter));
	}
	
	/**
	 * Integer típusú értéket ad vissza, hogy a vonaton mennyi szabad hely van. 
	 * @return
	 */
	public int getFreeSeats() {
		int freeSeats = 0;
		for (Wagon kocsi : this.wagons) {
			freeSeats += kocsi.getAmountOfFreeSeats();
		}
		return freeSeats;
	}
	
	/**
	 * Egy integer típusban megadott számú Wagon objektumot hoz létre és csatol hozzá a vonathoz.
	 * @param amount
	 */
	public void connectWagons(int amount) {
		for (int i = 0; i<amount; i++) {
			this.wagons.add(new Wagon(i+1));
		}
	}
	
	/**
	 * Végigmegy a vonaton és a legelsõ szabad helyet lefoglalja a vonaton a kocsiban és visszaadja a kocsi számát és a hely számát amit lefoglalt egy integereket tartalmazó tömbben, ami két elemû.
	 * Ha nem talált szabad helyet, null értékkel tér vissza.
	 * @return
	 */
	public int[] reserveSeat() {
		for (Wagon kocsi : this.wagons) {
			if (kocsi.getAmountOfFreeSeats()>0) {
				int[] helyadat = new int[] {kocsi.getNumber(), kocsi.reserveSeat()};
				return helyadat;
			}
		}
		return null;
	}
}
