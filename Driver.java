package main;

public class Driver {

	public static void main(String[] args) {

		int correct = 0, total = 0;
		int numTests = 100;
		long bruteTime = 0, divideTime=0, unNamedTime=0;

		// test
		for (int i = 0; i < numTests; i++) {
			Point[] testPts1 = getRandomPoints(1000);
			Point[] testPts2 = copyArray(testPts1);
			Point[] testPts3 = copyArray(testPts1);

			Point[] ans1, ans2, ans3;

			long start = System.nanoTime();
			ans1 = SortingAlgos.bruteForce(testPts1);

			long lap1 = System.nanoTime();
			ans2 = SortingAlgos.divideAndConq(testPts2);

			long lap2 = System.nanoTime();
			ans3 = SortingAlgos.unNamed(testPts3);

			long lap3 = System.nanoTime();

			bruteTime += (lap1 - start);
			divideTime += (lap2 - lap1);
			unNamedTime += (lap3 - lap2);

		//	System.out.println(print(ans1));
		//	System.out.println(print(ans2));
		//	System.out.println(print(ans3));

			

			// check accuracy
			double bruteForceDistance = ans1[0].dist(ans1[1]);
			double unNamedMethodDistance = ans3[0].dist(ans3[1]);
			if (bruteForceDistance == unNamedMethodDistance) {
				correct++;
				total++;
			} else {
				total++;
			}

		}
		System.out.println("Brute Force Time Average: " + bruteTime/numTests);
		System.out.println("Divide and Conquer Time Average: " + divideTime/numTests);
		System.out.println("Unnamed method Time Average: " + unNamedTime/numTests);
		System.out.println("Total Percent Correct: "+ (double)correct / (double) total);

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
			newA[i] = new Point(0.0, 0.0);
		}

		for (int i = 0; i < newA.length; i++) {
			newA[i].setX(old[i].getX());
			newA[i].setY(old[i].getY());
		}

		return newA;
	}

	public static String print(Point[] pts) {
		String x = "";
		for (int i = 0; i < pts.length; i++) {
			x += pts[i].toString().substring(0, 4) + " ";
		}
		return x;

	}

}
