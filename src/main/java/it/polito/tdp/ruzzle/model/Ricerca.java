package it.polito.tdp.ruzzle.model;

import java.util.*;

public class Ricerca {
	
	public List<Pos> trovaParola(String parola, Board board) {
		
		for(Pos p : board.getPositions()) {
			//verifico se la lettera in pos è == alla prima lettera di parola
			if(board.getCellValueProperty(p).get().charAt(0) == parola.charAt(0)) {	
				//se la lettera nel board è uguale alla prima lettera di parola posso far partire la ricorsione
				List<Pos> parziale = new ArrayList<Pos>();
				parziale.add(p);
				if(cerca(parola, parziale, 1, board))
					return parziale;
			}
		}
		
		return null;
	}
	
	public boolean cerca(String parola, List<Pos> percorso, int livello, Board board) {
		//caso terminale
		if(livello == parola.length()) {	
			return true;
		}
		
		Pos ultima = percorso.get(percorso.size()-1);
		List<Pos> adiacenti = board.getAdjacencies(ultima);
		
		for(Pos a : adiacenti) {
			if(!percorso.contains(a) && board.getCellValueProperty(a).get().charAt(0) == parola.charAt(livello)) {
				//posso 'continuare' il mio percorso facendo andare avanti la ricorsione
				percorso.add(a);
				
				//uscita rapida dalla ricorsione
				if(cerca(parola, percorso, livello+1, board))
					return true;
				
				percorso.remove(percorso.size()-1);
			}
		}
		
		return false;	//parola non trovata
	}

}
