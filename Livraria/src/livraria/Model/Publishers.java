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
public class Publishers {
    private int publisher_id;
	private String name;
	private String url;
	
	public int getPublisher_id() {
		return publisher_id;
	}
	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Publishers() {
	
	}
	
	public Publishers(int publisher_id, String name, String url) {
		super();
		this.publisher_id = publisher_id;
		this.name = name;
		this.url = url;
	}
}
