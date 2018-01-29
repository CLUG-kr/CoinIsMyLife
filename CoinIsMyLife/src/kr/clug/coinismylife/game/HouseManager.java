
public class HouseManager extends House{
	public House level1;
	public House level2;
	public House level3;
	public House level4;
	public House level5;
	public House level6;
	
	public HouseManager() {
		level1 = new House("반지하 단칸방", 1, 200, 30); //이름 레벨 가격 월세
		level2 = new House("창문이 달린 단칸방", 2, 500, 35);
		level3 = new House("그거보단 좋은 집", 3 ,1000, 40);
		level4 = new House("그거보다 더 좋은 집", 4, 2000, 45);
		level5 = new House("오피스텔", 5, 4000, 50);
		level6 = new House("빌딩", 6, 10000, 200);
	}
	
	
	public House getLevel1() {
		return level1;
	}

	public House getLevel2() {
		return level2;
	}

	public House getLevel3() {
		return level3;
	}

	public House getLevel4() {
		return level4;
	}

	public House getLevel5() {
		return level5;
	}

	public House getLevel6() {
		return level6;
	}

}
