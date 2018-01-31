import java.util.ArrayList;
import java.util.Calendar;

public class Character implements Runnable {
	private Thread thread;
	private Calendar cal;
	
	private int cumulativeTime;
	
	private int year;
	private int month;
	private int date;
	private int hour;
	private int minute;
	private int second;
	
	private int money; // 현재 가진 돈
	private int levelOfDeal; // 거래 레벨
	public House house; // 집
	public ArrayList<Friend> friendsList = new ArrayList<Friend>(); // 친구들
	public FriendsManager friendsManager = new FriendsManager();
	public HouseManager houseManager = new HouseManager();
	
	private int time; //월세 내기까지 남은 시간
	

	public Character() {
		cal = Calendar.getInstance();
		cumulativeTime = 0;
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		date = cal.get(Calendar.DATE);
		hour = cal.get(Calendar.HOUR_OF_DAY);
		minute = cal.get(Calendar.MINUTE);
		second = cal.get(Calendar.SECOND);
		time = 172800;
		
		
		this.money = 100000;
		this.levelOfDeal = 1;
		this.house = houseManager.getLevel1();
		this.friendsList.add(friendsManager.soulMate1);			this.friendsList.add(friendsManager.soulMate1);
		this.friendsList.add(friendsManager.bestFriend1); 		this.friendsList.add(friendsManager.bestFriend2);
		this.friendsList.add(friendsManager.friend1);			this.friendsList.add(friendsManager.friend2);
		this.friendsList.add(friendsManager.know1);				this.friendsList.add(friendsManager.know1);
		// TODO Auto-generated constructor stub
	}
	
	public Character(int money, int levelOfDeal, House house, ArrayList<Friend> friendsList) {
		this.money = money;
		this.levelOfDeal = levelOfDeal;
		this.house = house;
		this.friendsList = friendsList;
	}

	public int getMoney() {
		return money;
	}


	public void setMoney(int money) {
		this.money = money;
	}


	public int getLevelOfDeal() {
		return levelOfDeal;
	}


	public void setLevelOfDeal(int levelOfDeal) {
		this.levelOfDeal = levelOfDeal;
	}

	public int getCumulativeTime() {
		return cumulativeTime;
	}

	public void setCumulativeTime(int cumulativeTime) {
		this.cumulativeTime = cumulativeTime;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	@Override
	public void run() {
		while (!(time==0)) {
			time -=1;
			//System.out.println(time);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(time==0) { // 시간 초과시
				time=172800; // 초기화
				money-=house.getMonthlyRent(); // 월세만큼 차감
			}
		}
		
	}
	
	public void monthlyPayStart() {
		thread = new Thread(this);
		thread.start();
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
