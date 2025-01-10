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
	
	public void setElement(int x, int y,int value) {
		gridArr[x + y * width] = value;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public boolean isValid(int x, int y, int value) {
		for (int i = 0; i < width; i++) {
			if (getElement(i, y) == value) {
				return false;
			}
		}

		for (int j = 0; j < height; j++) {
			if (getElement(x, j) == value) {
				return false;
			}
		}

		int startX = (x / 3) * 3;
		int startY = (y / 3) * 3;
		for (int i = startX; i < startX + 3; i++) {
			for (int j = startY; j < startY + 3; j++) {
				if (getElement(i, j) == value) {
					return false;
				}
			}
		}

		return true;
	}

}

