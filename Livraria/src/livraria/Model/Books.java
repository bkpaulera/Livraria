/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livraria.Model;

/**
 *
 * @author thaismor
 */
public class Books {
    
    private String title;
    private String isbn;
    private double price;
    private String publisher_id;
	
	public Books(String title, String isbn, double price, String publisher_id) {
		this.title = title;
		this.isbn = isbn;
		this.price = price;
		this.publisher_id = publisher_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(String publisher_id) {
		this.publisher_id = publisher_id;
	}

	public Books() {
		
	}
	
    
}
