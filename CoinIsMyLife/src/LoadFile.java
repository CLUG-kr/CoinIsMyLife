import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadFile {
	private Character loadedCharacter;
	
	private int cumulativeTime;
	
	private int year;
	private int month;
	private int date;
	private int hour;
	private int minute;
	private int second;
	
	private int time; //월세 내기까지 남은 시간
	
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
			
			if(i==1 || i==9 || i==11 || i==14 || i==19) 
			{i++;continue;}
			else if(i==2)
				{this.cumulativeTime = Integer.parseInt(s); i++; continue;}
			else if(i==3)
				{this.year = Integer.parseInt(s); i++; continue;}
			else if(i==4)
				{this.month = Integer.parseInt(s); i++; continue;}
			else if(i==5)
				{this.date = Integer.parseInt(s); i++; continue;}
			else if(i==6)
				{this.hour = Integer.parseInt(s); i++; continue;}
			else if(i==7)
				{this.minute = Integer.parseInt(s); i++; continue;}
			else if(i==8)
				{this.second = Integer.parseInt(s); i++; continue;}
			else if(i==10)
				{this.time = Integer.parseInt(s); i++; continue;}
			else if(i==12)
				{this.money = Integer.parseInt(s); i++;continue;}
			else if(i==13)
				{this.levelOfDeal = Integer.parseInt(s); i++;continue;}
			
			else if(i==15)
				{house.setName(s); i++;}
			else if(i==16)
				{house.setLevel(Integer.parseInt(s)); i++;continue;}
			else if(i==17)
				{house.setPrice(Integer.parseInt(s)); i++;continue;}
			else if(i==18)
				{house.setMonthlyRent(Integer.parseInt(s)); i++;continue;}
			else {
				if(i%10==0)
					{friend.setName(s);i++;continue;}
				if(i%10==1)
					{friend.setAge(Integer.parseInt(s));i++;continue;}
				if(i%10==2)
					{friend.setMoneyLevel(Integer.parseInt(s));i++;continue;}
				if(i%10==3)
					{friend.setCloseness(Integer.parseInt(s));i++;continue;}
				if(i%10==4) 
					{friend.setLoan(Integer.parseInt(s));i++;continue;}
				if(i%10==5)
					{friend.setRice(Integer.parseInt(s));i++;continue;}
				if(i%10==6)
					{friend.setAlcohol(Integer.parseInt(s));i++;continue;}
				if(i%10==7)
					{friend.setPrediction(Integer.parseInt(s));i++;continue;}
				if(i%10==8)
					{friend.setLoan_time(Integer.parseInt(s));
					friendsList.add(friend);
					i++;
					continue;
				}
				if(i%10==9) {
					i++;continue;
				}
					
			}
			
				
			
		}
	}
	
	public Character getLoadedCharacter() {
		loadedCharacter = new Character(money, levelOfDeal, house, friendsList);
		loadedCharacter.setCumulativeTime(cumulativeTime);
		loadedCharacter.setYear(year);
		loadedCharacter.setMonth(month);
		loadedCharacter.setDate(date);
		loadedCharacter.setHour(hour);
		loadedCharacter.setMinute(minute);
		loadedCharacter.setSecond(second);
		
		return loadedCharacter;
	}

}
