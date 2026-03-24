package game.engine.dataloader;

import java.io.*;
import java.util.*;
import game.engine.cards.*;
import game.engine.cells.*;
import game.engine.exceptions.InvalidCSVFormat;
import game.engine.monsters.*;
import game.engine.Role;

public class DataLoader {
	private static final String CARDS_FILE_NAME = "cards.csv";
	private static final String CELLS_FILE_NAME = "cells.csv";
	private static final String MONSTERS_FILE_NAME = "monsters.csv";
	
	public static ArrayList<Card> readCards() throws IOException{
		ArrayList<Card> cards = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(CARDS_FILE_NAME));
			String line;
			while((line = br.readLine()) != null){
				String[] data = line.split(",");
					String cardType = data[0].trim();
					String name = data[1].trim();
					String description = data[2].trim();
					int rarity = Integer.parseInt(data[3].trim());
					switch(cardType){
						case "SWAPPER" :
							cards.add(new SwapperCard(name, description, rarity));
							break;
						case "STARTOVER" :
							boolean lucky = Boolean.parseBoolean(data[4]);
							cards.add(new StartOverCard(name, description, rarity, lucky));
							break;
						case "ENERGYSTEAL" :
							int energy = Integer.parseInt(data[4]);
							cards.add(new EnergyStealCard(name, description, rarity, energy));
							break;
						case "SHIELD" :
							cards.add(new ShieldCard(name, description, rarity));
							break;
						case "CONFUSION" :
							int duration = Integer.parseInt(data[4]);
							cards.add(new ConfusionCard(name, description, rarity, duration));
							break;
							default: throw new InvalidCSVFormat(line);
					}
				}
			br.close();
		return cards;
	}


 public static ArrayList<Cell> readCells() throws IOException {
        ArrayList<Cell> cells = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(CELLS_FILE_NAME));
        	String line;
            while ((line = br.readLine())!= null) {
                if (line.trim().isEmpty()) 
                	continue;
                String[] data = line.split(",");
    			String name = data[0].trim();
                
                    if (data.length == 3) {
                            Role role = Role.valueOf(data[1].trim().toUpperCase());
                            int energy = Integer.parseInt(data[2].trim());
                            cells.add(new DoorCell(name, role, energy));
                    } else if (data.length == 2) {
                        int effect = Integer.parseInt(data[1].trim());
                        if (effect > 0) {
                            cells.add(new ConveyorBelt(name, effect));
                        } else {
                            cells.add(new ContaminationSock(name, effect));
                        }
                    }
            }
            br.close();
        return cells;
 }
 
 public static ArrayList<Monster> readMonsters() throws IOException{
	ArrayList<Monster> monsters = new ArrayList<>();
	BufferedReader br = new BufferedReader(new FileReader(MONSTERS_FILE_NAME));
		String line;
		while((line = br.readLine()) != null){
			if(line.trim().isEmpty())
				continue;
			String[] data = line.split(",");
				String monsterType = data[0];
				String name = data[1].trim();
				String description = data[2].trim();
				Role role = Role.valueOf(data[3].trim().toUpperCase());
				int energy = Integer.parseInt(data[4].trim());
				
				switch(monsterType){
				case "DYNAMO" : monsters.add(new Dynamo(name, description, role, energy)); break;
				case "SCHEMER" : monsters.add(new Schemer(name, description, role, energy)); break;
				case "MULTITASKER" : monsters.add(new MultiTasker(name, description, role, energy)); break;
				case "DASHER" : monsters.add(new Dasher(name, description, role, energy)); break;
				}
	}
		br.close();
	return monsters;
}

}

