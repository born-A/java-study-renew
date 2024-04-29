package collection;

import java.util.Vector;

public class VectorTest01 {

	public static void main(String[] args) {
		Vector<String> v = new Vector<>();
		v.addElement("둘리");
		v.addElement("마이콜");
		v.addElement("또치");
		
		// 순회 1
		for(int i = 0; i < v.size(); i++) {
			String s = v.elementAt(i);
			System.out.println(s);
		}
		
		// 삭제
		v.removeElementAt(2);
	}

}
