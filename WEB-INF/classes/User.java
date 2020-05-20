import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;



/* 
	Users class contains class variables id,name,password,usertype.

	Users class has a constructor with Arguments name, String password, String usertype.
	  
	Users  class contains getters and setters for id,name,password,usertype.

*/

public class User implements Serializable{
	private int id;
	private String firstName;
	private String lastName;
	private String name;
	private String password;
	private String usertype;
	
	public User(String firstName, String lastName, String name, String password, String usertype) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.name=name;
		this.password=password;
		this.usertype=usertype;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getfirstName() {
		return firstName;
	}

	public void setfirName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
}
