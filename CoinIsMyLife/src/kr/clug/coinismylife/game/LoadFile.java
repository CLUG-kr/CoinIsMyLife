import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadFile {
	private Character loadedCharater;
	private int money;
	private int levelOfDeal;
	private House house;
	private Friend friend;
	private ArrayList<Friend> friendsList = new ArrayList<Friend>();
	
	public LoadFile(String fileName) throws IOException {
		house = new House();
		friend = new Friend();
		fileName +=".txt";
		
		BufferedReader fr = new BufferedReader(new FileReader(fileName));
		String s;
		int i = 1; // count
		while((s=fr.readLine())!=null){
			
			if(i==1 || i==4 || i==9) 
			{i++;continue;}
			else if(i==2)
				{this.money = Integer.parseInt(s); i++;continue;}
			else if(i==3)
				{this.levelOfDeal = Integer.parseInt(s); i++;continue;}
			
			else if(i==5)
				{house.setName(s); i++;}
			else if(i==6)
				{house.setLevel(Integer.parseInt(s)); i++;continue;}
			else if(i==7)
				{house.setPrice(Integer.parseInt(s)); i++;continue;}
			else if(i==8)
				{house.setMonthlyRent(Integer.parseInt(s)); i++;continue;}
			else {
				if(i%5==0)
					{friend.setName(s);i++;continue;}
				if(i%5==1)
					{friend.setAge(Integer.parseInt(s));i++;continue;}
				if(i%5==2)
					{friend.setLevel(Integer.parseInt(s));i++;continue;}
				if(i%5==3)
					{friend.setCloseness(Integer.parseInt(s));i++;continue;}
				if(i%5==4) {
					friend.setLoan(Integer.parseInt(s));
					friendsList.add(friend);
					i++;
					continue;
				}
					
			}
			
				
			
		}
	}
	
	public Character getLoadedCharacter() {
		loadedCharater = new Character(money, levelOfDeal, house, friendsList);
		return loadedCharater;
	}

}
