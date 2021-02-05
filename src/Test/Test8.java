package Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Test8 {
	public static void main(String[] args) {
		
//		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Num> nlist = new ArrayList<Num>();
//
//		
//		list.add(4);
//		list.add(1);
//		list.add(2);
//		list.add(5);
//		list.add(3);
//		
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
//		
//		Collections.sort(list);
//		
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}

		nlist.add(new Num(4, 5));
		nlist.add(new Num(1, 1));
		nlist.add(new Num(2, 4));
		nlist.add(new Num(5, 3));
		nlist.add(new Num(3, 2));

		
		
		for (int i = 0; i < nlist.size(); i++) {
			System.out.println(nlist.get(i).n2);
		}
		
		Collections.sort(nlist, new MyComparator2());
		
		for (int i = 0; i < nlist.size(); i++) {
			System.out.println(nlist.get(i).n2);
		}
	}

}

class Num {

	int n1;
	int n2;

	Num(int a, int b) {
		n1 = a;
		n2 = b;
	}

}

class MyComparator2 implements Comparator<Num> {

	@Override
	public int compare(Num obj1, Num obj2) {
		// 0 또는 1을 리턴하면 자리가 바뀐다.
		if(obj1.n2 < obj2.n2) {
			return 0;
		}
		// 음수 리턴하면 자리를 유지한다.
		return -1;
	}
	
}


