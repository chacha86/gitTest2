package Test;

import java.util.ArrayList;

public class Test6 {
	public static void main(String args[]) {
		
		
		ArrayList<String> aList = new ArrayList<>();
		aList.add("aa");
		aList.add("bb");
		aList.add("cc");
		
		aList.remove("aa");
		
		for(int i = 0; i < aList.size(); i++) {
			System.out.println(aList.get(i));
		}
	}
}
