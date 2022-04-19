package jegyrendszer;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class TrainMaker extends Menupoint{
	/**
	 * Konstruktor, a Menupoint oszt�lyt�l �r�k�lt konstruktort haszn�lja.
	 * @param menuid
	 * @param menupointname
	 */
	public TrainMaker(int menuid, String menupointname) {
		super(menuid,menupointname);
	}
	/**
	 * A megadott adatok alapj�n egy �j Train objektumot fog l�trehozni �s hozz�adni a nyilv�ntart�shoz, ami a TicketToGo trains ArrayList.
	 * K�t azonos vonatsz�m� vonatot nem enged a rendszer ez�rt azt is figyeli, hogy a l�trehozand� vonat sz�mmal nem szerepel-e egy m�sik vonat a nyilv�ntart�sban.
	 * A l�trehozott Train objektumhoz hozz�rendeli a kocsikat is.
	 */
	void action() {
		System.out.println("Vonat l�trehoz�sa");
		Scanner input = new Scanner(System.in);
		System.out.println("�rja be a vonat adatait (vessz�vel elv�lasztva) Vonatsz�m,kiindul�si �llom�s,indul�s id�pontja,c�l�llom�s,v�rhat� �rkez�si id�."+"\n");
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
		System.out.print("H�ny kocsit h�zzon a vonat?: ");
		int kivkocsiszam = input.nextInt();
		temptrain.connectWagons(kivkocsiszam);
		TicketToGo.addTrain(temptrain);
		System.out.println("Vonat sikeresen l�trehozva.");
		 } catch (TrainNumberTakenException  | IndexOutOfBoundsException | DateTimeParseException e){
				System.out.println(e.getMessage());
		 }
	}
}
