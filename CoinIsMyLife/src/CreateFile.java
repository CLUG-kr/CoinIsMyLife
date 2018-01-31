import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class CreateFile {
	private ArrayList<Friend> list;
	
	
	public CreateFile(Character character, String fileName, int cumulativeTime) throws IOException {
		fileName +=".txt";
		
		
		BufferedWriter fw = new BufferedWriter(new FileWriter(fileName)); // 파일 덮어쓰기
		fw.write("플레이시간/년/월/일/시/분/초");
		fw.newLine();
		fw.write(Integer.toString(cumulativeTime));
		fw.newLine();
		fw.write(Integer.toString(character.getYear()));
		fw.newLine();
		fw.write(Integer.toString(character.getMonth()+1));
		fw.newLine();
		fw.write(Integer.toString(character.getDate()));
		fw.newLine();
		fw.write(Integer.toString(character.getHour()));
		fw.newLine();
		fw.write(Integer.toString(character.getMinute()));
		fw.newLine();
		fw.write(Integer.toString(character.getSecond()));
		fw.newLine();
		fw.write("월세내기까지 남은 시간");
		fw.newLine();
		fw.write(Integer.toString(character.getTime()));
		fw.newLine();
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
		fw.write("친구 : 이름/나이/재력레벨/친밀도/빌려준돈/밥쿨/술쿨/코인예측쿨/돈갚쿨");
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
			fw.write(Integer.toString(f.getMoneyLevel()));
			fw.newLine();
			fw.write(Double.toString(f.getCloseness()));
			fw.newLine();
			fw.write(Integer.toString(f.getLoan()));
			fw.newLine();
			fw.write(Integer.toString(f.getRice()));
			fw.newLine();
			fw.write(Integer.toString(f.getAlcohol()));
			fw.newLine();
			fw.write(Integer.toString(f.getPrediction()));
			fw.newLine();
			fw.write(Integer.toString(f.getLoan_time()));
			fw.newLine();
			fw.write("-----");
		} 
		
		fw.close();
		
	}

}
