package Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Test {

	public static void main(String[] args) {
//		try {
//
//			FileWriter fw = new FileWriter("C:\\test\\test.txt");
//			BufferedWriter bw = new BufferedWriter(fw);
//			bw.write("asdfsdf");
//			bw.write("4asdfasdf");
//			bw.write("7sdasfda");
//			bw.close();
//		} catch (Exception ex) {
//
//		}

		try {
			FileReader fileReader = new FileReader("C:\\test\\test.txt");
			BufferedReader reader = new BufferedReader(fileReader);
			String line = null;
			String rst = "";
			while((line = reader.readLine()) != null) {
				rst += line;
			}
			
			reader.close();
			
			System.out.println(rst);
		} catch (Exception ex) {

		}
	}

}
