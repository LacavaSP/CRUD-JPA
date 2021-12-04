package com.classes;


import java.util.List;

import javax.persistence.*;

 
 
@Entity(name = "bookshelf")
public class BookShelf {

	
	
	
	public BookShelf(int bookShelfID, List<Book> booksByCubicle, String cubicleName) {

		this.cubicle = bookShelfID;
		this.booksByCubicle = booksByCubicle;

		this.cubicleName = cubicleName;
	}
	
	public BookShelf(int bookShelfID, String cubicleName) {

		this.cubicle = bookShelfID;
 
		this.cubicleName = cubicleName;
	}
	
	public BookShelf(){}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cubicle_id")
	private int cubicle;
	 
	@OneToMany
	(
            mappedBy = "cubicleB",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
	private List<Book> booksByCubicle;
	
	
	@Column(name = "cubicle_name",nullable = false)
	private String cubicleName;

	public int getBookShelfID() {
		return cubicle;
	}


	public List<Book> getBooksByCubicle() {
		return booksByCubicle;
	}

	public void setBookShelfID(int bookShelfID) {
		this.cubicle = bookShelfID;
	} 
	
	public void setBooksByCubicle(List<Book> booksByCubicle) {
		this.booksByCubicle = booksByCubicle;
	}


	public String getCubicleName() {
		return cubicleName;
	}

	public void setCubicleName(String cubicleName) {
		this.cubicleName = cubicleName;
	}

	@Override
	public String toString() {
		return "BookShelf [cubicle_ID=" + cubicle +"\n" +",cubicleName=" + cubicleName
				+ "\n" +", booksByCubicle="+ "\n" + booksByCubicle + "]";
	}

 
 
}
