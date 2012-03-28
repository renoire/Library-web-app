package ru.leti.alexeeva.shared;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="BOOK")
public class Book implements Serializable {
      public Long id;
      public String author;
      public String name;
      public String publisher;
      public Date year;
      public Long price;
      public Long quantity;
      public Date timeOfArrival;
      public String keywords;

      public Book() {
      }
          public Book(final String name) {
              this.name = name;
      }

    public Book(final String name, final String author, final String publisher,
                final Date year, final Long price, final Long quantity,
                final Date timeOfArrival, final String keywords) {
        this.author = author;
        this.name = name;
        this.publisher = publisher;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.timeOfArrival = timeOfArrival;
        this.keywords = keywords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

     public Date getYear(){
        return year;
    }

     public void setYear(Date year){
        this.year = year;
     }

     public Long getPrice(){
        return price;
    }

     public void setPrice(Long price){
        this.price = price;
     }

     public Long getQuantity(){
        return quantity;
    }

     public void setQuantity(Long quantity){
        this.quantity = quantity;
     }

     public Date getTimeOfArrival(){
        return timeOfArrival;
    }

     public void setTimeOfArrival(Date timeOfArrival){
        this.timeOfArrival = timeOfArrival;
     }
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
            this.keywords = keywords;
    }
}
