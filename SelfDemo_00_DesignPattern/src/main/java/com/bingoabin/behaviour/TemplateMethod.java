package com.bingoabin.behaviour;

/**
 * @Author: xubin34
 * @Date: 2022/3/13 4:25 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TemplateMethod {
	public static void main(String[] args) {
		Cooking cookingFood = new CookingFood();
		cookingFood.cook();
	}
}

abstract class Cooking {
	public abstract void step1();

	public abstract void step2();

	//模板方法
	public void cook() {
		System.out.println("start cooking");
		step1();
		step2();
		System.out.println("stop cooking");
	}
}

class CookingFood extends Cooking {

	@Override
	public void step1() {
		System.out.println("step1");
	}

	@Override
	public void step2() {
		System.out.println("step2");
	}
}


