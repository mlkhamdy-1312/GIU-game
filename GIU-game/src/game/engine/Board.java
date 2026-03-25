package game.engine;
import game.engine.cells.*;
import game.engine.Constants;
import java.util.*;
import game.engine.monsters.*;
import game.engine.cards.*;
import game.engine.dataloader.*;

public class Board {
	
	private  Cell[][] boardCells;
	private  static ArrayList<Monster> stationedMonsters;
	private  static ArrayList<Card> originalCards;
	public  static ArrayList<Card> cards;
	
	public Board(ArrayList<Card> readCards){
		boardCells = new Cell[Constants.BOARD_ROWS][Constants.BOARD_COLS];
		stationedMonsters = new ArrayList<>();
		cards = new ArrayList<>();
		originalCards.clear();
		originalCards.addAll(readCards); 
	}
	
	public Cell[][] getBoardCells(){
		return boardCells;
	}
	
	public static ArrayList<Monster> getStationedMonsters(){
		return stationedMonsters;
	}
	
	public static void setStationedMonsters(ArrayList<Monster> stationedMonsters){
		Board.stationedMonsters = stationedMonsters;
	}
	
	public static ArrayList<Card> getOriginalCards(){
		return originalCards;
	}
	
	public static ArrayList<Card> getCards(){
		return cards;
	}
	
	public static void setCards(ArrayList<Card> cards){
		Board.cards = cards;
	}
}
