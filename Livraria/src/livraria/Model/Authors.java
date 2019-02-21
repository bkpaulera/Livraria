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
public class Authors {
    
    private int author_id;
    private String name;
    private String fname;
	
	public int getAuthor_id() {
		return author_id;
	}
	
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public Authors(int author_id, String name, String fname) {
		super();
		this.author_id = author_id;
		this.name = name;
		this.fname = fname;
	}

	public Authors() {
		
	}
}
