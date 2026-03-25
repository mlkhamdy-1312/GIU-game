package game.engine;

import game.engine.dataloader.DataLoader;
import game.engine.monsters.Monster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Game {

    private Board board;
    private ArrayList<Monster> allMonsters;
    private Monster player;
    private Monster opponent;
    private Monster current;

    public Game(Role playerRole) throws IOException {
        this.board = new Board(DataLoader.readCards());
        this.allMonsters = DataLoader.readMonsters();
        this.player = selectRandomMonsterByRole(playerRole);
        Role oppositeRole = (playerRole == Role.SCARER) ? Role.LAUGHER : Role.SCARER;
        this.opponent = selectRandomMonsterByRole(oppositeRole);
        this.current = player;
    }

    private Monster selectRandomMonsterByRole(Role role) {
        ArrayList<Monster> candidates = new ArrayList<>();
        for (int i = 0; i < allMonsters.size(); i++) {
            if (allMonsters.get(i).getRole() == role) {
                candidates.add(allMonsters.get(i));
            }
        }
        Collections.shuffle(candidates);
        return candidates.get(0); 
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Monster> getAllMonsters() {
        return allMonsters;
    }

    public Monster getPlayer() {
        return player;
    }

    public Monster getOpponent() {
        return opponent;
    }

    public Monster getCurrent() {
        return current;
    }

    public void setCurrent(Monster current) {
        this.current = current;
    }
}
