package main;

import java.util.ArrayList;

public class SortingAlgos {

	// unnamed method
	/*
	 This method does run in O(n) time, but it does not always produce the correct answer
	 */
	public static Point[] unNamed(Point[] pts){
		Point[] ptsX = Point.sortByX(pts);
		Point[] ptsY = Point.sortByY(pts);
		return unNamed(ptsX, ptsY);
	}
	
	public static Point[] unNamed(Point[] ptsX, Point[] ptsY) {
		Point[] smallest = new Point[2];
		smallest[0] = ptsX[0];
		smallest[1] = ptsX[1];
	
		
		Point[] smallestY = new Point[2];
		smallestY[0] = ptsY[0];
		smallestY[1] = ptsY[1];
		
		Point[] smallestX = new Point[2];
		smallestX[0] = ptsX[0];
		smallestX[1] = ptsX[1];
		
		for(int i = 0; i < ptsX.length-1; i++){
			if(ptsX[i].dist(ptsX[i+1]) < smallestX[0].dist(smallestX[1])){
				smallestX[0] = ptsX[i];
				smallestX[1] = ptsX[i+1];
			}
		}
		
		for(int i = 0; i < ptsX.length-1; i++){
			if(ptsY[i].dist(ptsY[i+1]) < smallestY[0].dist(smallestY[1])){
				smallestY[0] = ptsY[i];
				smallestY[1] = ptsY[i+1];
			}
		}
		
		
		if(smallestY[0].dist(smallestY[1]) <  smallestX[0].dist(smallestX[1]) ){
			return smallestY;
		}else{
			return smallestX;
		}
		
	
	}

	// brute force
	public static Point[] bruteForce(Point[] pts) {
		Point p1 = pts[0], p2 = pts[1];
		double lowestDistance = 1000;

		for (int i = 0; i < pts.length; i++) {
			for (int j = 0; j < pts.length; j++) {
				double distance = pts[i].dist(pts[j]);
				if (distance < lowestDistance && pts[i] != pts[j]) {
					lowestDistance = distance;
					p1 = pts[i];
					p2 = pts[j];
				}
			}
		}
		Point rtPs[] = { p1, p2 };
		return rtPs;
	}

	public static Point[] divideAndConq(Point[] pts) {
		Point[] ptsX = Point.sortByX(pts);
		Point[] ptsY = Point.sortByY(pts);
		return divideAndConq(ptsX, ptsY);
	}

	public static Point[] divideAndConq(Point[] ptsX, Point[] ptsY) {

		/*
		 * if (ptsX.length < 2) { return null; } if(ptsX.length ==2){ return
		 * ptsX; }
		 */
		if (ptsX.length < 4) {
			return bruteForce(ptsX);
		}
		int median = ptsX.length / 2;

		Point[] leftCenter = new Point[median];
		Point[] rightCenter = new Point[ptsX.length - median];

		for (int i = 0; i < median; i++) {
			leftCenter[i] = ptsX[i];
		}
		for (int i = 0; i < ptsX.length - median; i++) {
			rightCenter[i] = ptsX[i + median];
		}

		Point[] lowestL = divideAndConq(leftCenter);
		Point[] lowestR = divideAndConq(rightCenter);

		// starting middle distance
		double minD;

		if (lowestL[0].dist(lowestL[1]) < lowestR[0].dist(lowestR[1])) {
			minD = lowestL[0].dist(lowestL[1]);
		} else {
			minD = lowestR[0].dist(lowestR[1]);
		}

		Point[] lowestM = new Point[2];

		ArrayList<Point> list = new ArrayList<>();

		for (int i = 0; i < ptsX.length; i++) {
			if (ptsX[i].x > ptsX[median].x - minD
					&& ptsX[i].x < ptsX[median].x + minD) {
				list.add(ptsX[i]);
			}
		}
		Point[] middlePoints = new Point[list.size()];

		for (int i = 0; i < list.size(); i++) {
			middlePoints[i] = list.get(i);
		}

		middlePoints = Point.sortByY(middlePoints);

		for (int i = 0; i < middlePoints.length; i++) {
			for (int j = i + 1; j < Math.min(middlePoints.length, i + 15); j++) {
				if (middlePoints[i].dist(middlePoints[j]) < minD) {
					lowestM[0] = middlePoints[i];
					lowestM[1] = middlePoints[j];
					minD = middlePoints[i].dist(middlePoints[j]);
				}
			}
		}

		Point[] lowest = new Point[2];
		if (lowestL[0].dist(lowestL[1]) < lowestR[0].dist(lowestR[1])) {
			lowest[0] = lowestL[0];
			lowest[1] = lowestL[1];
		} else {
			lowest[0] = lowestR[0];
			lowest[1] = lowestR[1];
		}

		if (lowestM[1] != null) {
			if (lowest[0].dist(lowest[1]) > lowestM[0].dist(lowestM[1])) {
				return lowestM;
			} else {
				return lowest;
			}
		} else {
			return lowest;
		}

	}

}
