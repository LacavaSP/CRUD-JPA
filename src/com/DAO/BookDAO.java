package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.classes.Book;
import com.classes.BookShelf;
import com.jdbc.connection.connBookShelf;
import com.main.Main;

public class BookDAO {

	public static void choose() throws InterruptedException {
		Scanner entrada = new Scanner(System.in);

		int r = 0;

		System.out.println("");
		System.out.println(">>============================<<");
		System.out.println("||         BOOK TABLE        ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||    1.Delete an register    ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||    2.Delete all registers  ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||      3.Search Register     ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||     4.List All Registers   ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||     5.Update an Register   ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||  6.Insert an new Register  ||");
		System.out.println(">>============================<<");
		System.out.println(">>============================<<");
		System.out.println("||7.Register a Cubicle in Book||");
		System.out.println(">>============================<<");
		System.out.println(">>============================<<");
		System.out.println("||     8.Return to The Menu   ||");
		System.out.println(">>============================<<");
		System.out.println("");
		Thread.sleep(1);
		r = entrada.nextInt();

		switch (r) {
		case 1:
			BookDAO.delete();
			break;
		case 2:
			try {
			BookDAO.deleteAllItems();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			try {
			BookDAO.readByID();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			try {
			BookDAO.read();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 5:
			BookDAO.update();
			break;
		case 6:
			try {
			BookDAO.insert();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 7:
			try {
			BookDAO.registerCubicle();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 8:
			Main.menu();
			break;
		default:
			System.out.println("Invalid option!");
			break;
		}

	}
	public static void insert() { 

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databasejpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Scanner entrada = new Scanner(System.in);
		Book bks = new Book();
		String resp = null;
		System.out.println("Write the book name");
		resp = entrada.next();
		bks.setName(resp);
		System.out.println("Write the book gender");
		resp = entrada.next();
		bks.setGender(resp);
		System.out.println("Write the book pages number");
		bks.setPagesNumber(entrada.nextInt());
		entityManager.getTransaction().begin();
		entityManager.persist(bks);
		entityManager.getTransaction().commit();
		Main.menu();

	}
	public static void update() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databasejpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Scanner entrada = new Scanner(System.in);
		int resp = 0;
		System.out.println("Escreva o ID do livro");
		resp = entrada.nextInt();
		Book toPersist = entityManager.
				find(Book.class, resp);

		System.out.println("Escolha uma opção");

		System.out.println("");
		System.out.println(">>============================<<");
		System.out.println("||         Book Editor        ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("|| 1.  Modify an Book Name    ||");
		System.out.println(">>============================<<");	
		System.out.println("|| 2.  Modify an Book Gender  ||");
		System.out.println(">>============================<<");
		System.out.println("|| 3.  Modify an Pages Number ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("");

		resp = entrada.nextInt();	
		if(resp == 1) {
			String f = null;
			System.out.println("Write the book name");
			entityManager.getTransaction().begin();
			f = entrada.next();
			toPersist.setName(f);
			entityManager.persist(toPersist);
			entityManager.getTransaction().commit();
		}else if(resp == 2) {
			String f = null;
			System.out.println("Write the book gender");
			entityManager.getTransaction().begin();
			f = entrada.next();
			toPersist.setGender(f);
			entityManager.persist(toPersist);
			entityManager.getTransaction().commit();
		}else if(resp == 3) {
			int f = 0;
			System.out.println("Write the book pages number");
			entityManager.getTransaction().begin();
			f = entrada.nextInt();
			toPersist.setPagesNumber(f);
			entityManager.persist(toPersist);
			entityManager.getTransaction().commit();
		}
		Main.menu();
	}
 
	public static void registerCubicle() throws SQLException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databasejpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Scanner entrada = new Scanner(System.in);
		try {
			BookShelfDAO.read();
		} catch (SQLException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int resp = 0;
		System.out.println("Insert the cubicle ID");
		resp = entrada.nextInt();
		BookShelf object = entityManager.find(BookShelf.class, resp);
	
		System.out.println("Insert the book ID");
		resp = entrada.nextInt();
		Book object2 = entityManager.find(Book.class, resp);
		
		entityManager.getTransaction().begin();
		object2.setcubicleB(object);
		entityManager.persist(object2);
		entityManager.getTransaction().commit();
		Main.menu();
	}
	public static void read() throws SQLException, InterruptedException {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databasejpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String sql = "SELECT*FROM book WHERE id > 0";

		try {
			if(!isEmpty()) {
				List<Book> SQLList = entityManager
						.createNativeQuery(sql, Book.class)
						.getResultList();
				SQLList.forEach(Book -> System.out.println("Consulta BKSQLList: " + Book));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread.sleep(500);
		Main.menu();
	}
	public static void readByID() throws SQLException {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databasejpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Scanner entrada = new Scanner(System.in);
		System.out.println("Write the bookshelf ID");
		String sql = "SELECT * FROM book WHERE id ="+Integer.toString(entrada.nextInt());

		try {
			if(!isEmpty()) {
				List<Book> SQLList = entityManager
						.createNativeQuery(sql, Book.class)
						.getResultList();
				SQLList.forEach(Book -> System.out.println("Consulta BKSQLList: " + Book));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Main.menu();
	}
	public static boolean isEmpty() throws Exception {
		String sql = "SELECT * FROM book";

		try (Connection connTeste = connBookShelf.connectione();) {

			PreparedStatement testeST = connTeste.prepareStatement(sql);
			ResultSet testeRS = testeST.executeQuery();

			if(!testeRS.next()) {
				System.out.println("The current table doesn't have data to read");
				return true;}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return false;
		
	}
	public static void delete() {
		int resp = 0;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databasejpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Scanner entrada = new Scanner(System.in);
		System.out.println("Escreva o ID do livro");
		resp = entrada.nextInt();

		try {	
			Book toPersist = entityManager.
					find(Book.class, resp);

			entityManager.getTransaction().begin();
			entityManager.remove(toPersist);
			entityManager.getTransaction().commit();

		}catch(IllegalArgumentException e) {
			System.out.println("Desculpe houve um erro porquê o"+"\n"+ "ID digitado não existe");
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Main.menu();
	}
	public static void deleteAllItems() throws Exception {
		String sql = "SELECT * FROM `databasejpa`.book;";
		String trunc = "SET FOREIGN_KEY_CHECKS = 0;";

		try (Connection conn = connBookShelf.connectione();
				PreparedStatement stmt = conn.prepareStatement(sql);){



			ResultSet rs= stmt.executeQuery();
			stmt.executeQuery(trunc);
			sql = "truncate TABLE book;";
			stmt.executeUpdate(sql);
			stmt.executeQuery(trunc);
			System.out.println("The table 'book' has been reseted");



		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		Thread.sleep(500);
		Main.menu();
	}

}