package com.preps.practice.java;

public class Cards {

	public static void main(String[] args) {
		System.out.println(Card.Heart.getValue());
		System.out.println(Card.Heart.ordinal());
		System.out.println(Card.Heart.name());
		
		System.out.println(Card.Spade.getValue());
		System.out.println(Card.Spade.ordinal());
		System.out.println(Card.Spade.name());
		
		System.out.println(Card.Diamond.getValue());
		System.out.println(Card.Diamond.ordinal());
		System.out.println(Card.Diamond.name());
		
		System.out.println(Card.Club.getValue());
		System.out.println(Card.Club.ordinal());
		System.out.println(Card.Club.name());
	}
}


enum Card {

	Heart(2), Spade, Diamond, Club;

	private Card(){
		
	}
	int n;
	private Card(int n){
		this.n=n;
	}
	
	public int getValue(){
		return this.n;
	}
}