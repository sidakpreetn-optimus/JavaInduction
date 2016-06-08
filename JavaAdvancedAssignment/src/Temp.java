import java.util.ArrayList;
public class Temp {
	public static void main(String[] args) {
		ArrayList<Integer> arr=new ArrayList<>();
		arr.add(null);
		arr.add(10);
		arr.add(3);
		arr.add(7);
		System.out.println(arr);
		MyArrayList<Integer> temp=new MyArrayList<Integer>();
		temp.add(null);
		temp.add(1);
		temp.add(5);
		temp.add(null,3);
		temp.add(1);
		temp.add(3);
		System.out.println(temp);
		temp.remove(null);
		System.out.println(temp);
		
	}

}
