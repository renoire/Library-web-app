package ru.leti.alexeeva.shared;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="AUTHOR")
public class Author implements Serializable {
	  public Long id;
	  public String fio;
	  public Date birthYear;

      public Author() {

      }
	  
	  public Author(Date birthYear) {
          this.birthYear = birthYear;
      }
	  
	  public Author(String name, Date birthYear) {
		  fio = name;
          this.birthYear = birthYear;
      }

	  public Author(Long id, Date birthYear) {
	    this.id = id;
          this.birthYear = birthYear;
      }

      public Author(Long id, String name, Date birthYear) {
	    this.id = id;
          this.birthYear = birthYear;
      }

    public Date getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Date birthYear) {
        this.birthYear = birthYear;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
