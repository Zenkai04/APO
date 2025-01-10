package com.gmail.darkfireyo;

public class Grid {
	
	private int[] gridArr;
	
	private int width;
	
	private int height;
	
	
	public Grid(int width, int height) {
		this.gridArr = new int[width * height];
		
		this.width = width;
		this.height = height;
	}
	
	public int getElement(int x, int y) {
		if (x >= width || y >= height || x < 0 || y < 0) {
			System.out.println("Bad Values");
			System.exit(1);
		}
		return gridArr[x + y * width];
	}
	
	public void setElement(int x, int value) {
		gridArr[x] = value;
	}

}
<<<<<<< HEAD

=======
>>>>>>> 491b5a9cff38986fc3aa589da228b4ef15605ac4
