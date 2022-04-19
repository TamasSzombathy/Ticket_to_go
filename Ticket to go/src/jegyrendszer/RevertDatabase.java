package jegyrendszer;

public class RevertDatabase extends Menupoint{
	/**
	 * Konstruktor, a Menupoint oszt�lyt�l �r�k�lt konstruktort haszn�lja.
	 * @param menuid
	 * @param menupointname
	 */
	public RevertDatabase(int menuid, String menupointname) {
		super(menuid, menupointname);
	}
	/**
	 * Megh�vja a Serializer oszt�ly LoadData met�dus�t, ami deszerializ�l�ssal visszat�lti az !eggyel! kor�bbi �llapotot az application_data mapp�ban l�v� Trains �s Tickets f�jlokb�l.   
	 */
	@Override
	void action() {
		System.out.println("Figyelem! A m�r kinyomtatott (printed_tickets mapp�ban l�v� .txt) jegyeket a rendszer nem t�rli.");
		Serializer.loadData();
		
	}

}
