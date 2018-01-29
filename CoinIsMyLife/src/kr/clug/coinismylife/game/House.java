
public class House{
	private String name; // 집 이름
	private int level; // 집 레벨
	private int price; // 가격
	private int monthlyRent; // 월세
	

	public House() {
		// TODO Auto-generated constructor stub
	}
	
	public House(String name, int level, int price, int monthlyRent) {
		super();
		this.name = name;
		this.level = level;
		this.price = price;
		this.monthlyRent = monthlyRent;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMonthlyRent() {
		return monthlyRent;
	}

	public void setMonthlyRent(int monthlyRent) {
		this.monthlyRent = monthlyRent;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
