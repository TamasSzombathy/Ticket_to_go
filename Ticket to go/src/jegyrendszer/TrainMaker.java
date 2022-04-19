package jegyrendszer;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class TrainMaker extends Menupoint{
	/**
	 * Konstruktor, a Menupoint osztálytól örökölt konstruktort használja.
	 * @param menuid
	 * @param menupointname
	 */
	public TrainMaker(int menuid, String menupointname) {
		super(menuid,menupointname);
	}
	/**
	 * A megadott adatok alapján egy új Train objektumot fog létrehozni és hozzáadni a nyilvántartáshoz, ami a TicketToGo trains ArrayList.
	 * Két azonos vonatszámú vonatot nem enged a rendszer ezért azt is figyeli, hogy a létrehozandó vonat számmal nem szerepel-e egy másik vonat a nyilvántartásban.
	 * A létrehozott Train objektumhoz hozzárendeli a kocsikat is.
	 */
	void action() {
		System.out.println("Vonat létrehozása");
		Scanner input = new Scanner(System.in);
		System.out.println("Írja be a vonat adatait (vesszõvel elválasztva) Vonatszám,kiindulási állomás,indulás idõpontja,célállomás,várható érkezési idõ."+"\n");
		String[] adatok = input.nextLine().split(",");
		try {
			for (Train train : TicketToGo.trains) {
				if (Integer.parseInt(adatok[0]) == train.getNumber()) {
					System.out.println(train.toString());
					throw new TrainNumberTakenException();
				}
			}
		
		LocalDateTime startTime = LocalDateTime.parse(adatok[2], TicketToGo.formatter);
		LocalDateTime endTime = LocalDateTime.parse(adatok[4], TicketToGo.formatter);
		Train temptrain = new Train(Integer.parseInt(adatok[0]),adatok[1],startTime,adatok[3],endTime);
		System.out.println(temptrain.toString());
		System.out.print("Hány kocsit húzzon a vonat?: ");
		int kivkocsiszam = input.nextInt();
		temptrain.connectWagons(kivkocsiszam);
		TicketToGo.addTrain(temptrain);
		System.out.println("Vonat sikeresen létrehozva.");
		 } catch (TrainNumberTakenException  | IndexOutOfBoundsException | DateTimeParseException e){
				System.out.println(e.getMessage());
		 }
	}
}
