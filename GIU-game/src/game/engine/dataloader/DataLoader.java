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
			
			String cardType = data[0];
			String name = data[1];
			String description = data[2];
			int rarity = Integer.parseInt(data[3]);
		try{
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
		catch(ArrayIndexOutOfBoundsException | IllegalArgumentException e){
		throw new InvalidCSVFormat(line);}
		}
		br.close();
		return cards;
	}


 public static ArrayList<Cell> readCells() throws IOException {
        ArrayList<Cell> cells = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(CELLS_FILE_NAME));
        String line;
        while ((line = br.readLine())!= null) {
            if (line.trim().isEmpty()) continue;
            String[] fields = line.split(",");
			 String name = fields[0].trim();
            try {
                if (fields.length == 3) {
                        Role role = Role.valueOf(fields[1].trim().toUpperCase());
                        int energy = Integer.parseInt(fields[2].trim());
                        cells.add(new DoorCell(name, role, energy));
                } else if (fields.length == 2) {
                    int effect = Integer.parseInt(fields[1].trim());
                    if (effect > 0) {
                        cells.add(new ConveyorBelt(name, effect));
                    } else {
                        cells.add(new ContaminationSock(name, effect));
                    }
                } else {
                    throw new InvalidCSVFormat(line);
                }
            } catch (NumberFormatException e) {
                throw new InvalidCSVFormat(line);
            }
			br.close();
        }

        
        return cells;
    }

}



