import java.util.Random;

public class Lines {
	private CharacterLine characterLine;
	private FriendLine friendLine;
	private NewsLine newsLine;
	
	public Lines(Character c) {
		characterLine = new CharacterLine(c);
		friendLine = new FriendLine(c);
		newsLine = new NewsLine();
	}
	
	public CharacterLine getCharacterLine() {
		return characterLine;
	}

	public FriendLine getFriendLine() {
		return friendLine;
	}

	public NewsLine getNewsLine() {
		return newsLine;
	}

}

class CharacterLine{ // 캐릭터 클릭 시 발생하는 대사, 캐릭터의 상태 (돈/집)에 따라 달라진다.
	private String lines[] = new String[5];
	
	CharacterLine(Character c){ // if문 써서 캐릭터 상태에 따라 대사 달라지게 구현
		this.lines[0] = "말 걸지마...";
		this.lines[1] = "죽는게 나을지도 몰라...";
		this.lines[2] = "떡상 가즈아!!!";
		this.lines[3] = "나도 좋은 집에서 살고 싶다...";
		this.lines[4] = "내 인생은 왜이럴까?";
	}
	
	public String getLines() {
		Random random = new Random();
		return lines[random.nextInt(5)];
	}

	
}
class FriendLine{ // 친구들 반응 대사 , 주인공의 상태(돈/집)과 해당 친구들의 상태(친밀도)에 따라 달라진다.
	FriendLine(Character c){
		
	}
}

class NewsLine{ // 뉴스 대사
	NewsLine(){
		
	}
	
}
