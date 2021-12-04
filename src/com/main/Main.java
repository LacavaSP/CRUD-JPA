package com.main;

import java.sql.SQLException;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.DAO.BookDAO;
import com.DAO.BookShelfDAO;

public class Main {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databasejpa");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	public static void main(String[] args) throws Exception {
		//BookShelfDAO.insert();
		//BookShelfDAO.read();
		//BookShelfDAO.delete();
		//BookShelfDAO.deleteAllItems();
		//BookDAO.insert();
		//BookDAO.update();
		//BookDAO.read();
		//BookDAO.readByID();
		//BookDAO.delete();
		//BookDAO.deleteAllItems();
		//BookShelfDAO.insert();
		//BookDAO.read();
		//BookShelfDAO.delete();
		//BookShelfDAO.insert();
		//BookDAO.registerCubicle();
		//BookShelfDAO.read();
		menu();


	}


	public static void menu() {
		int resp = 0;
		Scanner data = new Scanner(System.in);

		System.out.println(">>============================<<");
		System.out.println("||  DATABASE CONSOLE MANAGER  ||");
		System.out.println(">>============================<<");	 
		System.out.println(">>============================<<");
		System.out.println("||      CHOOSE A OPTION!      ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||         1.Book CRUD        ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||       2.BookShelf CRUD     ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||      3.End Application     ||");
		System.out.println(">>============================<<");	
		resp = data.nextInt();


		switch (resp) {
		case 1:
			try {
				BookDAO.choose();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				BookShelfDAO.choose();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			break;
		default:
			System.out.println("Número inválido");

		}
	}
}

