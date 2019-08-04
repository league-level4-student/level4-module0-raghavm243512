//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

public class TheWrongWayCow {

	public static int[] findWrongWayCow(final char[][] field) {
		// Fill in the code to return the x,y coordinate position of the
		// head (letter 'c') of the wrong way cow!
		int up = 0, down = 0, left = 0, right = 0;
		int[] cow = new int[2];
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[0].length; j++) {
				if (field[i][j] == 'c' && checkUp(i, j, field)) {
					left++;
				}
				if (field[i][j] == 'c' && checkDown(i, j, field)) {
					right++;
				}
				if (field[i][j] == 'c' && checkLeft(i, j, field)) {
					up++;
				}
				if (field[i][j] == 'c' && checkRight(i, j, field)) {
					down++;
				}
			}
		}
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[0].length; j++) {
				if (left == 1&&checkUp(i,j,field)) {
					cow[1]=i;
					cow[0]=j;
				}
				if (right == 1&&checkDown(i,j,field)) {
					cow[1]=i;
					cow[0]=j;
				}
				if (up == 1&&checkLeft(i,j,field)) {
					cow[1]=i;
					cow[0]=j;
				}
				if (down == 1&&checkRight(i,j,field)) {
					cow[1]=i;
					cow[0]=j;
				}
			}
		}
		System.out.println(up + " " + down+ " " +left+ " " +right + " " + cow[0] + " "  + cow[1]);
		return cow;
	}

	public static boolean checkUp(int i, int j, char[][] c) {
		if (j < 2)
			return false;
		if (c[i][j - 1] == 'o' && c[i][j - 2] == 'w')
			return true;
		return false;
	}

	public static boolean checkDown(int i, int j, char[][] c) {
		if (j > c[0].length - 3)
			return false;
		if (c[i][j + 1] == 'o' && c[i][j + 2] == 'w')
			return true;
		return false;
	}

	public static boolean checkLeft(int i, int j, char[][] c) {
		if (i < 2)
			return false;
		if (c[i - 1][j] == 'o' && c[i - 2][j] == 'w')
			return true;
		return false;
	}

	public static boolean checkRight(int i, int j, char[][] c) {
		if (i > c.length - 3)
			return false;
		if (c[i + 1][j] == 'o' && c[i + 2][j] == 'w')
			return true;
		return false;
	}
}
