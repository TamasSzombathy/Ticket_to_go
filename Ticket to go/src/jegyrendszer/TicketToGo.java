package jegyrendszer;
//import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketToGo {
	static ArrayList<Train> trains = new ArrayList<>();
	/**
	 * Jelenleg l�trehozott, a TicketToGo trains ArrayListben t�rolt Train objektumokat adja vissza. Kimenete egy ArrayList ami Train t�pus� objektumokat tartalmaz.
	*/
	static ArrayList<Train> getTrains() {return TicketToGo.trains; }
	/**
	 * A kimenetre ki�rja az �sszes Train t�pus� objektum adat�t, ami a TicketToGo trains ArrayListben van t�rolva. Ha a trains lista �res, akkor a kimeneten ezt is jelzi. 
	 */
	static void listTrains() {
		if (TicketToGo.trains.isEmpty()) {
			System.out.println("Jelenleg nincs vonat a nyilv�ntart�sban.");
		} else {
			for (Train train : TicketToGo.trains ) {
				System.out.println(train.toString());
				System.out.println("Szabad helyek sz�ma: "+train.getFreeSeats());
			}
		}
	}
	/**
	 * A param�terk�nt kapott Train t�pus� objektumot behelyezi a TicketToGo trains ArrayList-be. 
	 * @param temp
	 */
	static void addTrain(Train temp) {
		trains.add(temp);
	}
	static ArrayList<Ticket> tickets = new ArrayList<>();
	/**
	 * A param�terk�nt kapott Ticket t�pus� objektumot behelyezi a TicketToGo tickets ArrayList-be.
	 * @param temp
	 */
	static void addTicket(Ticket temp) {
		tickets.add(temp);
	}
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm");
	/**
	 * A program Main f�ggv�nye, feladata a men�pontok l�trehoz�sa a menupoints ArrayList-be �s egy "�r�k" ciklusban a f�men� men�pont elemeit list�zza, illetve k�ri be a felhaszn�l� men�pontv�laszt�s�t �s az ennek megfelel� men�pont oszt�ly action f�ggv�ny�t h�vja meg.
	 * Minden programindul�skor ellen�rzi a program working directory-j�ban, hogy adottak-e a felt�telek a m�k�d�shez, pl ellen�rzi, hogy az application_data mappa l�tezik-e, ha nem l�trehozza, mert ebben t�rolja a program a szerializ�lt vonat �s jegy adatokat.
	 * Ha neml�tez� men�pontsz�mot �rtak be, ezt jelzi �s �jra a f�men� elemeit mutatja.
	 * @param args
	 */
	static ArrayList<Menupoint> menupoints = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//Train elso = new Train(1,"Budapest-Keleti",LocalDateTime.parse("2021.04.27-13:05",formatter),"Bonyh�d",LocalDateTime.parse("2021.04.27-15:05",formatter));
		//elso.connectWagons(3);
		//System.out.println(elso.toString());
		//trains.add(elso);
		menupoints.add(new TrainMaker(1,"Vonat l�trehoz�sa"));
		menupoints.add(new TicketMaker(2,"Jegy kiad�sa"));
		menupoints.add(new TrainLister(3,"Vonatok kilist�z�sa"));
		menupoints.add(new OverwriteTrain(4,"Vonat adatainak fel�l�r�sa"));
		menupoints.add(new DeleteTrain(5,"Vonat t�rl�se"));
		menupoints.add(new RevertDatabase(6,"V�ltoztat�sok visszavon�sa"));
		menupoints.add(new Exit(7,"Ment�s �s kil�p�s"));
		
		boolean corruption_found = Serializer.filesystemCheck();
		if (corruption_found == false) {
			Serializer.loadData();
		}
		
		while (true) {
			try {
				System.out.println("\nF�men�");
				for (Menupoint menupont : menupoints) {
					System.out.println(menupont.getMenuid()+". "+menupont.getMenuPointName()+" ("+menupont.getMenuid()+")");
				}
				System.out.print("�rja be a v�lasztott men�pont sz�m�t: ");
				int menupont = input.nextInt();
				if (menupoints.get(0).getMenuid() == menupont) {
					menupoints.get(0).action();
				} else if (menupoints.get(1).getMenuid() == menupont) {
					menupoints.get(1).action();
				} else if (menupoints.get(2).getMenuid() == menupont) {
					menupoints.get(2).action();
				} else if (menupoints.get(3).getMenuid() == menupont) {
					menupoints.get(3).action();
				} else if (menupoints.get(4).getMenuid() == menupont) {
					menupoints.get(4).action();
				} else if (menupoints.get(5).getMenuid() == menupont) {
					menupoints.get(5).action();
				} else if (menupoints.get(6).getMenuid() == menupont) {
					menupoints.get(6).action();
					break;
				} else {
					System.out.println("Ilyen men�pont nem l�tezik.");
				}
				
			} catch (java.util.InputMismatchException e) {
				System.out.println("A be�rt men�pont sz�m nem sz�m.");
				e.printStackTrace();
				Serializer.saveData();
				break;
			}
		}
	}
}
