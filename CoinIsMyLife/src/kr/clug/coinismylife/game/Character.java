import java.util.ArrayList;

public class Character {


	private int money; // ���� ���� ��
	private int levelOfDeal; // �ŷ� ����
	public House house; // ��
	public ArrayList<Friend> friendsList = new ArrayList<Friend>(); // ģ����
	public FriendsManager friendsManager = new FriendsManager();
	public HouseManager houseManager = new HouseManager();
	

	public Character() {
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
		super();
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

}
