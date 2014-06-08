package com.mycompany.tiralabra_maven.structures;

public class Link<E> {
	private Object value;
	private Link<E> next;
	private Link<E> previous;

	public Link(){
	}

	public Link(E value){
		this.value = value;
	}

	// setterit ja getterit	

}