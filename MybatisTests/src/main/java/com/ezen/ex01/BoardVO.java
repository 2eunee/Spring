package com.ezen.ex01;

/*create table BoardVO (
    id number(4) primary key,
    name varchar(30),
    phone varchar(30),
    address varchar(200)
);
*/

public class BoardVO {
	int id;
	String name, phone, address;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
