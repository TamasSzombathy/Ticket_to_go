package jegyrendszer;

public class RevertDatabase extends Menupoint{
	/**
	 * Konstruktor, a Menupoint osztálytól örökölt konstruktort használja.
	 * @param menuid
	 * @param menupointname
	 */
	public RevertDatabase(int menuid, String menupointname) {
		super(menuid, menupointname);
	}
	/**
	 * Meghívja a Serializer osztály LoadData metódusát, ami deszerializálással visszatölti az !eggyel! korábbi állapotot az application_data mappában lévõ Trains és Tickets fájlokból.   
	 */
	@Override
	void action() {
		System.out.println("Figyelem! A már kinyomtatott (printed_tickets mappában lévõ .txt) jegyeket a rendszer nem törli.");
		Serializer.loadData();
		
	}

}
