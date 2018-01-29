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

class CharacterLine{ // ĳ���� Ŭ�� �� �߻��ϴ� ���, ĳ������ ���� (��/��)�� ���� �޶�����.
	private String lines[] = new String[5];
	
	CharacterLine(Character c){ // if�� �Ἥ ĳ���� ���¿� ���� ��� �޶����� ����
		this.lines[0] = "�� ������...";
		this.lines[1] = "�״°� �������� ����...";
		this.lines[2] = "���� �����!!!";
		this.lines[3] = "���� ���� ������ ��� �ʹ�...";
		this.lines[4] = "�� �λ��� ���̷���?";
	}
	
	public String getLines() {
		Random random = new Random();
		return lines[random.nextInt(5)];
	}

	
}
class FriendLine{ // ģ���� ���� ��� , ���ΰ��� ����(��/��)�� �ش� ģ������ ����(ģ�е�)�� ���� �޶�����.
	FriendLine(Character c){
		
	}
}

class NewsLine{ // ���� ���
	NewsLine(){
		
	}
	
}
