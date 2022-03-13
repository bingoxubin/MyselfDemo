package com.bingoabin.behaviour;

/**
 * @Author: xubin34
 * @Date: 2022/3/13 8:07 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class State {
	public static void main(String[] args){
		Lisi lisi = new Lisi();
		lisi.changeState(new Happy());
		lisi.doSomething();
	}
}

abstract class StatePattern{
	abstract void doSomething();
}

class Happy extends StatePattern {

	@Override
	void doSomething() {
		System.out.println("happy");
	}
}

class Angry extends StatePattern {

	@Override
	void doSomething() {
		System.out.println("angry");
	}
}

class Lisi{
	private StatePattern statePattern;

	public void changeState(StatePattern statePattern) {
		this.statePattern = statePattern;
	}

	public void doSomething(){
		statePattern.doSomething();
	}
}

