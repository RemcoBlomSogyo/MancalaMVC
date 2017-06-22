package nl.sogyo.model;

public class Mancala
{
	Subbakje eersteBakje;
    
    public Mancala() {
    	eersteBakje = new Subbakje();
    }
    
    public void speelBakje(int bakjeNummer) {
    	if (bakjeNummer == 1) {
    		eersteBakje.startBeurt();
    	} else if (bakjeNummer <= 6) {
    		((Subbakje) eersteBakje.getBuurman(bakjeNummer - 1)).startBeurt();
    	} else {
    		((Subbakje) eersteBakje.getBuurman(bakjeNummer)).startBeurt();
    	}
    }
    
    public int getSpelerBeurt() {
    	if (eersteBakje.getEigenaar().getBeurt()) {
    		return 1;
    	} else if (eersteBakje.getOverbuurman().getEigenaar().getBeurt()) {
    		return 2;
    	} else {
    		return 0;
    	}
    }
    
    public int getWinnaar() {
    	if (getInhoudKalahaSpeler1() > getInhoudKalahaSpeler2()) {
    		return 1;
    	} else if (getInhoudKalahaSpeler1() < getInhoudKalahaSpeler2()){
    		return 2;
    	} else {
    		return 0;
    	}
    }
    
    public boolean checkEindeSpel() {
    	return beideSpelersZijnNietAanDeBeurt();
    }
    
    public int[] getWaardesBakjes() {
    	int[] waardesBakjes = new int[14];
    	waardesBakjes[0] = eersteBakje.getInhoud();
    	
    	for (int i = 1; i < waardesBakjes.length; i++) {
    		waardesBakjes[i] = eersteBakje.getBuurman(i).getInhoud();
    	}
    	
    	return waardesBakjes;
    }
    
    private boolean beideSpelersZijnNietAanDeBeurt() {
    	return eersteBakje.getEigenaar().getBeurt() 
    			== eersteBakje.getOverbuurman().getEigenaar().getBeurt();
    }
    
    private int getInhoudKalahaSpeler1() {
    	return eersteBakje.getBuurman(6).getInhoud();
    }
    
    private int getInhoudKalahaSpeler2() {
    	return eersteBakje.getBuurman(13).getInhoud();
    }
}
