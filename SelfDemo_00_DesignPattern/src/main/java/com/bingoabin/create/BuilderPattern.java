package com.bingoabin.create;

/**
 * @Author: xubin34
 * @Date: 2022/3/9 10:23 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class BuilderPattern {
	public static void main(String[] args) {
		House house = new House.Builder().setDoor(new Door()).setWall(new Wall()).setWindow(new Window()).build();
	}
}

class House {
	private Window window;
	private Wall wall;
	private Door door;

	private House(Builder builder) {
		window = builder.window;
		wall = builder.wall;
		door = builder.door;
	}

	static class Builder {
		private Window window;
		private Wall wall;
		private Door door;

		public Builder setWindow(Window window) {
			this.window = window;
			return this;
		}

		public Builder setWall(Wall wall) {
			this.wall = wall;
			return this;
		}

		public Builder setDoor(Door door) {
			this.door = door;
			return this;
		}

		public House build() {
			return new House(this);
		}
	}
}

class Window {

}

class Wall {

}

class Door {

}