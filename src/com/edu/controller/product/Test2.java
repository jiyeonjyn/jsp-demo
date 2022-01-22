package com.edu.controller.product;

import java.util.ArrayList;

public class Test2 {
	public static void main(String[] args) {
		/*
		String[] array = new String[8];
		for(String str : array)
			System.out.println(str);
		
		for(int i=0; i<4; i++) {
			array[i] = ""+i;
		}
		for(String str : array)
			System.out.println(str);
		*/
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("aaaaaaaaaa");
		list.add("bbbbbbbbbbb");
		list.add("ccccc");
		list.stream().map(userid -> userid.substring(0, 2)
					+ new String(new char[userid.length()-4]).replace("\0", "*")
					+ userid.substring(userid.length()-2)).forEach(userid -> System.out.println(userid));
		System.out.println(new String(new char["asdfasdfasdf".length()-4]).replace("\0", "*"));
	}
}
