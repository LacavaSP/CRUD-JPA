package com.classes;
import java.util.List;
import javax.persistence.*;
@Entity(name = "book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int BookID;
	

	@ManyToOne(fetch = FetchType.LAZY)
	private BookShelf cubicleB;

	@Column(name="cubicle_ID",nullable = false)
	private int cubicleID;
	
	@Column(name="book_name",nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(name = "pages_number",nullable = false)
	private int pagesNumber;

	public Book(int bookID, int cubicleID, String name, String gender, int pagesNumber) {
		BookID = bookID;
		this.cubicleID = cubicleID;
		this.name = name;
		this.gender = gender;
		this.pagesNumber = pagesNumber;
	}
	
	public Book(){}
	
	public int getBookID() {
		return BookID;
	}

	public void setBookID(int bookID) {
		BookID = bookID;
	}

	public String getName() {
		return name;
	}

	public BookShelf getcubicleB() {
		return cubicleB;
	}

	public void setcubicleB(BookShelf bookShelf) {
		this.cubicleB = bookShelf;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getPagesNumber() {
		return pagesNumber;
	}

	public void setPagesNumber(int pagesNumber) {
		this.pagesNumber = pagesNumber;
	}

	@Override
	public String toString() {
		return "Book [BookID=" + BookID + "\n"+ ", name=" + name + "\n"+ ", gender=" + gender
				+ "\n"+ ", pagesNumber=" + pagesNumber + "]" + "\n";
	}


	
	
}
