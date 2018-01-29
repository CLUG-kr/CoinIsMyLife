
public class Friend {
	private String name; // 이름
	private int age; // 나이
	private int level; // 재력 레벨
	private int closeness; // 주인공과의 친밀도
	private int loan; // 빌려 준 돈

	public Friend() {
	}
	public Friend(String name, int age, int level, int closeness, int loan) {
		this.name = name;
		this.age = age;
		this.level = level;
		this.closeness = closeness;
		this.loan = loan;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getCloseness() {
		return closeness;
	}
	public void setCloseness(int closeness) {
		this.closeness = closeness;
	}
	public int getLoan() {
		return loan;
	}
	public void setLoan(int loan) {
		this.loan = loan;
	}
}
