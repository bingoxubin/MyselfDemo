package com.bingoabin.create;

/**
 * @Author: xubin34
 * @Date: 2022/3/9 10:08 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SingleTon {

}

class SingleTon1 {
	private static SingleTon1 singleTon1 = new SingleTon1();

	private SingleTon1() {

	}

	public static SingleTon1 getInstance() {
		return singleTon1;
	}
}

class SingleTon2 {
	private static SingleTon2 singleTon2;

	private SingleTon2() {

	}

	private static synchronized SingleTon2 getInstance() {
		if (singleTon2 == null) {
			singleTon2 = new SingleTon2();
		}
		return singleTon2;
	}
}

class SingleTon3 {
	private static volatile SingleTon3 singleTon3;

	private SingleTon3() {

	}

	private static SingleTon3 getInstance() {
		if (singleTon3 == null) {
			synchronized (SingleTon3.class) {
				if (singleTon3 == null) {
					singleTon3 = new SingleTon3();
				}
			}
		}
		return singleTon3;
	}
}