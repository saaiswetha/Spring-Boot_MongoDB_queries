package studentMongoApp.Model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "details")
public class student {

	@Id
	private ObjectId _id;
	private String fname;
	private String lname;
	private int score;
	private Date dob;	

	public student() {}
	
	  public student(String fname,int score) {super(); this.fname=fname;
	  this.score=score; }
	  
	 public student(String fname) { super(); this.fname=fname; }
	 
	 public student(ObjectId _id, String fname, String lname, int score, Date dob) {
			super();
			this._id = _id;
			this.fname = fname;
			this.lname = lname;
			this.score = score;
			this.dob = dob;
		}
	 
	public ObjectId getNum() {
		return _id;
	}

	public void setNum(ObjectId num) {
		this._id = num;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
