package nl.sogyo.model;

public class Kalaha extends Bakje{
	
	/*
		constructor voor kalaha
	*/
	public Kalaha(int kalahaCounter, Subbakje eersteBakje, Speler eigenaar) {
		inhoud = 0;
		this.eigenaar = eigenaar;
		
		// buurman van eerste kalaha is een nieuw subbakje, buurman van tweeede kalaha is het eerste subbakje
		if (kalahaCounter > 0) {
			buurman = new Subbakje(6, kalahaCounter, eersteBakje, new Speler(false));
		} else {
			buurman = eersteBakje;
		}
	}
	
	/*
		constructor voor kalaha - bij zelfingestelde inhoud bakjes
	*/
	public Kalaha(int kalahaCounter, Subbakje eersteBakje, Speler eigenaar, int[] inhoudBakjes, int indexBakje) {
		inhoud = 0;
		this.eigenaar = eigenaar;
		
		// buurman van eerste kalaha is een nieuw subbakje, buurman van tweeede kalaha is het eerste subbakje
		if (kalahaCounter > 0) {
			buurman = new Subbakje(6, kalahaCounter, eersteBakje, new Speler(false), inhoudBakjes, indexBakje + 1);
		} else {
			buurman = eersteBakje;
		}
	}
	
	/*
		geef hand door, voeg alleen steentje toe als de eigenaar van de hand overeen komt met de eigenaar van de kalaha
	*/
	public void geefDoor(int hand, Speler eigenaarHand) {
		if (eigenaar == eigenaarHand) {
			voegSteenToe();
			if (hand > 1) {
				buurman.geefDoor(hand - 1, eigenaarHand);
			} else if (checkBakjesLeeg(getEigenaar())) {
				getEigenaar().wisselBeurt();
			}
		} else {
			buurman.geefDoor(hand, eigenaarHand);
		}
	}
	
	/*
		leeg de inhoud van de hand in de kalaha
	*/
	public void geefKalaha(int hand) {
		inhoud += hand;
		getEigenaar().wisselBeurt();
		
		if (!buurman.checkBakjesLeeg(buurman.getEigenaar())) {
			buurman.getEigenaar().wisselBeurt();
		}
	}
}