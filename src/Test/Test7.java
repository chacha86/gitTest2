package Test;

public class Test7 {

	public static void main(String[] args) {
		
		고양이 a고양이 = new 고양이();
		
		a고양이.숨쉬다();
		
		동물 a동물 = new 강아지();
		a동물.숨쉬다();
	}

}

class 동물 {
	void 숨쉬다() {
		System.out.println("숨쉬다");
	}
}

class 고양이 extends 동물 {
	void 숨쉬다() {
		System.out.println("고로롱 숨쉰다.");
	}
}

class 강아지 extends 동물 {
	void 숨쉬다() {
		System.out.println("헥헥 숨쉰다.");
	}
}