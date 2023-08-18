package com.example.bookbd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class HelloController {
    @FXML
    TableView tableView;
    @FXML
    Button button;

    HashMap<Integer, Book> isbnMap = new HashMap<>();

    public void initialize() throws SQLException, IOException {
        ObservableList<Book> books = zapolnenieBooksFromBD();
        initTable(books);
        startChangeTracking(books);
        button.setOnAction(a->System.out.println(isbnMap));
        fileWriter(isbnMap);
        button.setOnAction(a->fileWriter(isbnMap));
    }

    public void startChangeTracking(ObservableList<Book> books){
        for (Book b: books) {
            b.titleProperty().addListener((val,o,n)->isbnMap.put(b.getIsbn(),b));
            b.yearProperty().addListener((val,o,n)->isbnMap.put(b.getIsbn(),b));
        }}
    public void fileWriter(HashMap<Integer, Book> isbnMap){
      try{  FileWriter fileWriter=new FileWriter("2.txt",true);//если есть"true"список не будет
          fileWriter.write(isbnMap.toString());                     //обнуляться,данные в него будут добавляться
        fileWriter.close();
    } catch (IOException e){
          System.out.println(":((");
      }
    }

    public void initTable(ObservableList<Book> books )
    {
        tableView.getColumns().clear();
        TableColumn<Book,Integer> columnA=new TableColumn<>("isbn");
        columnA.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tableView.getColumns().add(columnA);
        TableColumn<Book,String> columnB=new TableColumn<>("title");
        columnB.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnB.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView.getColumns().add(columnB);
        TableColumn<Book,Integer> columnC=new TableColumn<>("year");
        columnC.setCellValueFactory(new PropertyValueFactory<>("year"));
        columnC.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tableView.getColumns().add(columnC);
        tableView.setItems(books);
        tableView.setEditable(true);
    }


    private static  ObservableList<Book> zapolnenieBooksFromBD() throws SQLException {
        Connection conn =  connectToDB();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT isbn, title, year FROM book ORDER BY title;");
        ObservableList<Book> books = FXCollections.observableArrayList();
        while (rs.next()) {
         //   System.out.println(rs.getString(1));
            Book book=new Book(rs.getInt(1),rs.getString(2),rs.getInt(3));
            books.add(book);
        }
        rs.close();
        st.close();
        return books;
    }


        private static Connection connectToDB() {
            String url = "jdbc:postgresql://10.10.104.166:5432/Lib?user=postgres&password=123";//&ssl=true
            try {

                Connection conn =  DriverManager.getConnection(url);
                System.out.println("подключено");
                return conn;
            } catch (Exception e) {
                System.out.println("не удалось подключиться к базе. "+e.getMessage());
                return null;
            }
        }
    }