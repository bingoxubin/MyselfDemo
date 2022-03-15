package com.bingoabin.create;

import java.util.Objects;

/**
 * @Author: xubin34
 * @Date: 2022/3/15 10:26 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ProtoType {
	public static void main(String[] args){
		Plane plane = new Plane();
		System.out.println(plane);

		Plane clone = plane.clone();
		System.out.println(clone);

		System.out.println(plane == clone);
		System.out.println(Objects.equals(plane,clone));
	}
}

interface Proto{
	Object clone();
}

class Plane implements Proto {
	private String name;
	private String type;

	public Plane() {
		this.name = "Name:" + Math.random();
		this.type = "Type:" + Math.random();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public Plane clone() {
		return new Plane(this);
	}

	public Plane(Plane plane) {
		this.name = plane.name;
		this.type = plane.type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Plane plane = (Plane) o;

		if (name != null ? !name.equals(plane.name) : plane.name != null) return false;
		return type != null ? type.equals(plane.type) : plane.type == null;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Plane{" +
				"name='" + name + '\'' +
				", type='" + type + '\'' +
				'}';
	}
}


