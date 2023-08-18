package com.example.bookbd;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Book {


  private   SimpleIntegerProperty isbn;
  private   SimpleStringProperty title;
   private SimpleIntegerProperty year;

    @Override
    public String toString() {
        return "isbn=" + isbn.get();
    }

    public Book(Integer isbn, String title, Integer year) {
        this.isbn = new SimpleIntegerProperty(isbn);
        this.title = new SimpleStringProperty(title);
        this.year = new SimpleIntegerProperty(year);
    }

    public int getIsbn() {
        return isbn.get();
    }

    public SimpleIntegerProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn.set(isbn);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public int getYear() {
        return year.get();
    }

    public SimpleIntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

}
