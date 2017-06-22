package nl.sogyo.model;

public class Subbakje extends Bakje{
	
	/*
		constructor eerste subbakje
	*/
	public Subbakje() {
		inhoud = 4;
		eigenaar = new Speler(true);
		buurman = new Subbakje(5, 2, this, eigenaar);
	}
	
	/*
		constructor voor de rest van subbakjes
	*/
	public Subbakje(int subbakjeCounter, int kalahaCounter, Subbakje eersteBakje, Speler eigenaar) {
		inhoud = 4;
		this.eigenaar = eigenaar; 
		
		if (subbakjeCounter > 1) {
			buurman = new Subbakje(subbakjeCounter - 1, kalahaCounter, eersteBakje, eigenaar);
		} else {
			buurman = new Kalaha(kalahaCounter - 1, eersteBakje, eigenaar);
		}
	}
	
	/*
		constructor eerste subbakje - bij zelfingestelde inhoud bakjes
	 */
	public Subbakje(int[] inhoudBakjes) {
		inhoud = inhoudBakjes[0];
		eigenaar = new Speler(true);
		buurman = new Subbakje(5, 2, this, eigenaar, inhoudBakjes, 1);
	}
	
	/*
		constructor voor de rest van subbakjes - bij zelfingestelde inhoud bakjes
	 */
	public Subbakje(int subbakjeCounter, int kalahaCounter, Subbakje eersteBakje, Speler eigenaar, int[] inhoudBakjes, int indexBakje) {
		inhoud = inhoudBakjes[indexBakje];
		this.eigenaar = eigenaar; 
		if (subbakjeCounter > 1) {
			buurman = new Subbakje(subbakjeCounter - 1, kalahaCounter, eersteBakje, eigenaar, inhoudBakjes, indexBakje + 1);
		} else {
			buurman = new Kalaha(kalahaCounter - 1, eersteBakje, eigenaar, inhoudBakjes, indexBakje);
		}
	}
	
	/*
		start de beurt op dit bakje - haal bakje leeg en geef door
	*/
	public void startBeurt() {
			
		// als het juiste bakje is gevonden en het bakje is niet leeg, haal het bakje leeg en geef de steentjes door
		if (inhoud != 0 && eigenaar.getBeurt()) {
			buurman.geefDoor(haalLeeg(), eigenaar);
		}
	}
	
	/*
		haal bakje leeg en return de inhoud
	*/
	public int haalLeeg() {
		int hand = inhoud;
		inhoud = 0;
		return hand;
	}
	
	/*
	 * haal bakje een aantal steentjes uit bakje - alleen voor testen
	 */
	public int haalLeeg(int steentjes) {
		int hand = steentjes;
		inhoud -= steentjes;
		return hand;
	}
	
	/*
		doorgeven van de hand aan buurman, bij ontvangst doe een steentje in bakje
	*/
	public void geefDoor(int hand, Speler eigenaarHand) {
		voegSteenToe();
		
		// geef hand aan buurman door als de hand nog steentjes heeft
		if (hand > 1) {
			buurman.geefDoor(hand - 1, eigenaarHand);
		
		// als de hand geen steentjes meer heeft om door te geven en het bakje was leeg en van de eigen speler, steel dan de steentjes van de overbuurman
		} else if (eigenaarHand == getEigenaar() && inhoud == 1) {
			Subbakje overbuurman = (Subbakje) getOverbuurman();
			hand = overbuurman.haalLeeg();
			hand += haalLeeg();
			geefKalaha(hand);
			
		// de beurt is voorbij en moet gewisseld worden. De speler die aan de beurt was wordt sowieso op false gezet.
		} else {
			System.out.println("eigenaarhand.wisselbeurt");
			eigenaarHand.wisselBeurt();
			
			// of de andere speler op true gaat, hangt af of zijn bakjes nog gevuld zijn
			// controleer of laatste bakje eigen bakje is
			if (getEigenaar() == eigenaarHand) {
				if (!checkBakjesLeeg(getOverbuurman().getEigenaar())) {
					getOverbuurman().getEigenaar().wisselBeurt();
				}
				
			// anders is einde bakje het bakje van tegenstander
			} else if (!checkBakjesLeeg(getEigenaar())) {
				getEigenaar().wisselBeurt();
			}
		}
	}
	
	/*
	 	geef de hand door richting kalaha - als buurman subbakje is geef de hand door
	*/
	public void geefKalaha(int hand) {
		buurman.geefKalaha(hand);
	}
	
	/*
		krijg het overbuurman-subbakje van dit subbakje
	*/
	public Bakje getOverbuurman() {
		int stappen = 0;
		Bakje currentBakje = this;
		
		// loop eerst alle eigen bakjes af en tel elke stap op
		while (currentBakje.getBuurman().getEigenaar() == eigenaar) {
			currentBakje = currentBakje.getBuurman();
			stappen++;
		}
		
		// loop daarna alle bakjes van de tegenstander af en haal telkens een stap eraf
		while (stappen != 0) {
			currentBakje = currentBakje.getBuurman();
			stappen--;
		}
		
		// als stappen weer op 0 staat, return het bakje
		return currentBakje;
	}
}
