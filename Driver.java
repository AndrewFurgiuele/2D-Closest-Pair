package main;

public class Driver {

	public static void main(String[] args) {

		Point[] testPts1 = getRandomPoints(100);
		Point[] testPts2 = copyArray(testPts1);
		Point[] testPts3 = copyArray(testPts1);

		Point[] ans1, ans2, ans3;
		long bruteTime, divideTime, unNamedTime;

		long start = System.nanoTime();
		ans1 = SortingAlgos.bruteForce(testPts1);
		
		long lap1 = System.nanoTime();
		ans2 = SortingAlgos.divideAndConq(testPts2);
		
		long lap2 = System.nanoTime();
		ans3 = SortingAlgos.unNamed(testPts3);
		
		long lap3 = System.nanoTime();
		
		
		bruteTime = lap1-start;
		divideTime = lap2-lap1;
		unNamedTime = lap3-lap2;
		
		
		System.out.println(print(ans1));
		System.out.println(print(ans2));
		System.out.println(print(ans3));

		System.out.println("Brute Force Time: "+ bruteTime);
		System.out.println("Divide and Conquer Time: "+ divideTime);
		System.out.println("Unnamed method Time: "+ unNamedTime);

	}

	public static Point[] getRandomPoints(int size) {

		Point[] newP = new Point[size];

		for (int i = 0; i < size; i++) {
			newP[i] = new Point((Math.random() * 100), (Math.random() * 100));
		}

		return newP;
	}

	public static Point[] copyArray(Point[] old) {

		Point[] newA = new Point[old.length];

		for (int i = 0; i < newA.length; i++) {
			newA[i]= new Point(0.0,0.0);
		}
		
		for (int i = 0; i < newA.length; i++) {
			newA[i].setX(old[i].getX());
			newA[i].setY(old[i].getY());
		}

		return newA;
	}

	
	
	
	
	public static String print(Point[] pts){
		String x = "";
		for(int i = 0; i < pts.length; i++){
			x += pts[i].toString().substring(0, 4) + " ";
		}
		return x;
		
		
	}
	
	
	
}
