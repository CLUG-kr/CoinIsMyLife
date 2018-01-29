import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class CreateFile {
	private ArrayList<Friend> list;
	
	
	public CreateFile(Character character, String fileName) throws IOException {
		fileName +=".txt";
		
		BufferedWriter fw = new BufferedWriter(new FileWriter(fileName)); // 파일 덮어쓰기
		fw.write("돈/거래레벨");
		fw.newLine();
		fw.write(Integer.toString(character.getMoney()));
		fw.newLine();
		fw.write(Integer.toString(character.getLevelOfDeal()));
		fw.newLine();
		fw.write("집 : 이름/레벨/가격/월세");
		fw.newLine();
		fw.write(character.house.getName());
		fw.newLine();
		fw.write(Integer.toString(character.house.getLevel()));
		fw.newLine();
		fw.write(Integer.toString(character.house.getPrice()));
		fw.newLine();
		fw.write(Integer.toString(character.house.getMonthlyRent()));
		
		
		//친구들 정보 입력
		
		fw.newLine();
		fw.write("친구 : 이름/나이/재력레벨/친밀도/빌려준돈");
		list = character.friendsList;
		Friend f ;
		Iterator<Friend> itr = list.iterator();
		while(itr.hasNext()) {
			f =itr.next();
			fw.newLine();
			fw.write(f.getName());
			fw.newLine();
			fw.write(Integer.toString(f.getAge()));
			fw.newLine();
			fw.write(Integer.toString(f.getLevel()));
			fw.newLine();
			fw.write(Integer.toString(f.getCloseness()));
			fw.newLine();
			fw.write(Integer.toString(f.getLoan()));
		} 
		
		fw.close();
		
	}

}
