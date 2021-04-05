package model;

import java.util.ArrayList;

public class Player {

	private String name;
	private Integer score=0;
	private ArrayList<Integer> history=new ArrayList<>();
	private ArrayList<Boolean> eastHistory=new ArrayList<>();
	private boolean east;
	private int index;
	
	public Player(String name, int index){
		this.name=name;
		this.index=index;
	}
	
	public Integer getScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}
	
	public void setEast(boolean east) {
		this.east = east;
		this.eastHistory.add(east);
	}
	
	public boolean isEast() {
		return east;
	}
	
	public ArrayList<Integer> getHistory() {
		return history;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getIndex() {
		return index;
	}
	
	public ArrayList<Boolean> getEastHistory(){
		return this.eastHistory;
	}
	
	public void addScore(Integer score) {
		this.score=this.score+score;
		history.add(score);
	}

	public void setLastEast() {
		this.east=eastHistory.get(eastHistory.size()-1);
	}	
}
