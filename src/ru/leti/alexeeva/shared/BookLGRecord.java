package ru.leti.alexeeva.shared;

import com.smartgwt.client.widgets.grid.ListGridRecord;

import java.util.Date;

/**
 * Author: Alex A. Renoire (kronshtein78rus@gmail.com)
 * Date: 27.03.12
 */

public class BookLGRecord extends ListGridRecord {
    public BookLGRecord() {
    }

    public BookLGRecord(Long id, String author, String keywords, String name,
                        Long price, String publisher, Long quantity,
                        Date timeOfArrival, Date year) {
        setId(id);
        setAuthor(author);
        setKeywords(keywords);
        setName(name);
        setPrice(price);
        setPublisher(publisher);
        setQuantity(quantity);
        setTimeOfArrival(timeOfArrival);
        setDate(year);
    }

    public void setId(Long id) {
        setAttribute("id", id);
    }

    public Long getId() {
        return getAttributeAsLong("id");
    }

    public void setAuthor(String author){
        setAttribute("author", author);
    }

    public String getAuthor(){
        return getAttributeAsString("author");
    }

    public void setKeywords(String keywords){
        setAttribute("keywords", keywords);
    }

    public String getKeywords(){
        return getAttributeAsString("keywords");
    }

    public void setName(String name){
        setAttribute("name", name);
    }

    public String getName(){
        return getAttributeAsString("name");
    }

    public void setPrice(Long price){
        setAttribute("price", price);
    }

    public Long getPrice(){
        return getAttributeAsLong("price");
    }

    public void setPublisher(String publisher){
        setAttribute("publisher", publisher);
    }

    public String getPublisher(){
        return getAttributeAsString("publisher");
    }

    public void setQuantity(Long quantity){
        setAttribute("quantity", quantity);
    }

    public Long getQuantity(){
        return getAttributeAsLong("quantity");
    }

    public void setTimeOfArrival(Date timeOfArrival){
        setAttribute("timeOfArrival", timeOfArrival);
    }

    public Date getTimeOfArrival(){
        return getAttributeAsDate("timeOfArrival");
    }

    public void setDate(Date year){
        setAttribute("year", year);
    }

    public Date getDate(){
        return getAttributeAsDate("year");
    }
}
