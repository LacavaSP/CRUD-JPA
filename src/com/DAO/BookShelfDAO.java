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


import com.classes.BookShelf;
 
import com.jdbc.connection.connBookShelf;
import com.main.Main;

public class BookShelfDAO {
	public static void choose() throws InterruptedException {
		Scanner entrada = new Scanner(System.in);

		int r = 0;

		System.out.println("");
		System.out.println(">>============================<<");
		System.out.println("||   BookShelf Cubicle TABLE  ||");
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
		System.out.println("||  7.Return to the menu      ||");
		System.out.println(">>============================<<");

		System.out.println("");
		Thread.sleep(1);
		r = entrada.nextInt();

		switch (r) {
		case 1:
			delete();
			break;
		case 2:
			try {
			deleteAllItems();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			try {
			readByID();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			try {
			read();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 5:
			update();
			break;
		case 6:
			try {
			insert();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 7:
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
		BookShelf bks = new BookShelf();
		System.out.println("Escreva o gênero do cubículo da estante");
		bks.setCubicleName(entrada.next());
		entityManager.getTransaction().begin();
		entityManager.persist(bks);
		entityManager.getTransaction().commit();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Main.menu();
	}

	public static void update() throws InterruptedException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databasejpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Write the cubicle ID");
		
		BookShelf toPersist = entityManager.
				find(BookShelf.class, entrada.nextInt());
		
		System.out.println("Escolha uma opção");
		System.out.println("");
		System.out.println(">>============================<<");
		System.out.println("||  BookShelf Cubicle Editor  ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("|| 1.Modify an Cubicle gend.  ||");
		System.out.println(">>============================<<");	
		System.out.println("");
		toPersist.setCubicleName(entrada.next());
		entityManager.getTransaction().begin();
		entityManager.persist(toPersist);
		entityManager.getTransaction().commit();
		Thread.sleep(500);
		Main.menu();
	}


	public static void read() throws SQLException, InterruptedException {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databasejpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String sql = "SELECT*FROM bookshelf WHERE cubicle_id > 0";
		
		try {
			if(!isEmpty()) {
			List<BookShelf> SQLList = entityManager
					.createNativeQuery(sql, BookShelf.class)
					.getResultList();
			SQLList.forEach(BookShelf -> System.out.println("Consulta BKSQLList: "+"\n"+ BookShelf));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(500);
		Main.menu();
	}
	
	
public static void readByID() throws SQLException, InterruptedException {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databasejpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Write the bookshelf ID");
		
		String sql = "SELECT*FROM bookshelf WHERE id ="+Integer.toString(entrada.nextInt());
		
		try {
			if(!isEmpty()) {
			List<BookShelf> SQLList = entityManager
					.createNativeQuery(sql, BookShelf.class)
					.getResultList();
			SQLList.forEach(BookShelf -> System.out.println("Consulta BKSQLList: " + BookShelf));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(500);
		Main.menu();
	}

 
	
	public static boolean isEmpty() throws Exception {
		String sql = "SELECT * FROM bookshelf";

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
	public static void delete() throws InterruptedException {
		int resp = 0;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databasejpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Scanner entrada = new Scanner(System.in);
		System.out.println("Write the cubicle ID");
		resp = entrada.nextInt();

		try {	
			BookShelf toPersist = entityManager.
					find(BookShelf.class, resp);

			entityManager.getTransaction().begin();
			entityManager.remove(toPersist);
			entityManager.getTransaction().commit();

		}catch(IllegalArgumentException e) {
			System.out.println("Sorry the ID doesn't exist!");
		}
		Thread.sleep(500);
		Main.menu();
	}
	
	public static void deleteAllItems() throws Exception {
		String sql = "SELECT * FROM `databasejpa`.bookshelf;";
		String trunc = "SET FOREIGN_KEY_CHECKS = 0;";

		try (Connection conn = connBookShelf.connectione();
				PreparedStatement stmt = conn.prepareStatement(sql);){

		 

			ResultSet rs= stmt.executeQuery();
			stmt.executeQuery(trunc);
			sql = "truncate TABLE bookshelf;";
			stmt.executeUpdate(sql);
			stmt.executeQuery(trunc);
			System.out.println("The table 'bookshelf' has been reseted");
			
			

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		Thread.sleep(500);
		Main.menu();
	}

}
