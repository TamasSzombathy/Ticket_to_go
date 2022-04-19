package jegyrendszer;
//import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketToGo {
	static ArrayList<Train> trains = new ArrayList<>();
	/**
	 * Jelenleg létrehozott, a TicketToGo trains ArrayListben tárolt Train objektumokat adja vissza. Kimenete egy ArrayList ami Train típusú objektumokat tartalmaz.
	*/
	static ArrayList<Train> getTrains() {return TicketToGo.trains; }
	/**
	 * A kimenetre kiírja az összes Train típusú objektum adatát, ami a TicketToGo trains ArrayListben van tárolva. Ha a trains lista üres, akkor a kimeneten ezt is jelzi. 
	 */
	static void listTrains() {
		if (TicketToGo.trains.isEmpty()) {
			System.out.println("Jelenleg nincs vonat a nyilvántartásban.");
		} else {
			for (Train train : TicketToGo.trains ) {
				System.out.println(train.toString());
				System.out.println("Szabad helyek száma: "+train.getFreeSeats());
			}
		}
	}
	/**
	 * A paraméterként kapott Train típusú objektumot behelyezi a TicketToGo trains ArrayList-be. 
	 * @param temp
	 */
	static void addTrain(Train temp) {
		trains.add(temp);
	}
	static ArrayList<Ticket> tickets = new ArrayList<>();
	/**
	 * A paraméterként kapott Ticket típusú objektumot behelyezi a TicketToGo tickets ArrayList-be.
	 * @param temp
	 */
	static void addTicket(Ticket temp) {
		tickets.add(temp);
	}
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm");
	/**
	 * A program Main függvénye, feladata a menüpontok létrehozása a menupoints ArrayList-be és egy "örök" ciklusban a fõmenü menüpont elemeit listázza, illetve kéri be a felhasználó menüpontválasztását és az ennek megfelelõ menüpont osztály action függvényét hívja meg.
	 * Minden programinduláskor ellenõrzi a program working directory-jában, hogy adottak-e a feltételek a mûködéshez, pl ellenõrzi, hogy az application_data mappa létezik-e, ha nem létrehozza, mert ebben tárolja a program a szerializált vonat és jegy adatokat.
	 * Ha nemlétezõ menüpontszámot írtak be, ezt jelzi és újra a fõmenü elemeit mutatja.
	 * @param args
	 */
	static ArrayList<Menupoint> menupoints = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//Train elso = new Train(1,"Budapest-Keleti",LocalDateTime.parse("2021.04.27-13:05",formatter),"Bonyhád",LocalDateTime.parse("2021.04.27-15:05",formatter));
		//elso.connectWagons(3);
		//System.out.println(elso.toString());
		//trains.add(elso);
		menupoints.add(new TrainMaker(1,"Vonat létrehozása"));
		menupoints.add(new TicketMaker(2,"Jegy kiadása"));
		menupoints.add(new TrainLister(3,"Vonatok kilistázása"));
		menupoints.add(new OverwriteTrain(4,"Vonat adatainak felülírása"));
		menupoints.add(new DeleteTrain(5,"Vonat törlése"));
		menupoints.add(new RevertDatabase(6,"Változtatások visszavonása"));
		menupoints.add(new Exit(7,"Mentés és kilépés"));
		
		boolean corruption_found = Serializer.filesystemCheck();
		if (corruption_found == false) {
			Serializer.loadData();
		}
		
		while (true) {
			try {
				System.out.println("\nFõmenü");
				for (Menupoint menupont : menupoints) {
					System.out.println(menupont.getMenuid()+". "+menupont.getMenuPointName()+" ("+menupont.getMenuid()+")");
				}
				System.out.print("Írja be a választott menüpont számát: ");
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
					System.out.println("Ilyen menüpont nem létezik.");
				}
				
			} catch (java.util.InputMismatchException e) {
				System.out.println("A beírt menüpont szám nem szám.");
				e.printStackTrace();
				Serializer.saveData();
				break;
			}
		}
	}
}
