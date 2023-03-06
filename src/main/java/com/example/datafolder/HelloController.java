package com.example.datafolder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class HelloController {
    @FXML
public TableView table;
public TableColumn<musicVideo, String> name;
public TableColumn<musicVideo, Integer> ranking;
public TableColumn<music, Float> interactions;
public TableColumn<musicVideo, String> uploader;
public TableColumn<music, String> released;
public void initialize() throws Exception {
    restoreOrReadData();
    ArrayList<music> temporaryList = (ArrayList<music>) music.getAllData();
    // Turn the read data's ArrayList into an ObservableList
    ObservableList temporaryObservableList = FXCollections.observableArrayList(temporaryList);
    // Make that ObservableList the list for my TableView
    table.setItems(temporaryObservableList);
    // Wire table's columns with BoxOfficeFilm's fields
    // This causes all stored values in BoxOfficeFilm fields to be displayed in their corresponding Columns
    ranking.setCellValueFactory(new PropertyValueFactory<>("Ranking"));
    name.setCellValueFactory(new PropertyValueFactory<>("Name"));
    interactions.setCellValueFactory(new PropertyValueFactory<>("Interactions"));
    uploader.setCellValueFactory(new PropertyValueFactory<>("YoutubeUploader"));
    released.setCellValueFactory(new PropertyValueFactory<>("Released"));

    // This is how a column detects the new value entered after a Column cell is edited
    ranking.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    name.setCellFactory(TextFieldTableCell.forTableColumn());
    interactions.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
   uploader.setCellFactory(TextFieldTableCell.forTableColumn());
    released.setCellFactory(TextFieldTableCell.forTableColumn());
    // This is how new value from Column cell edit gets stored in that row's corresponding BoxOfficeFilm field
    ranking.setOnEditCommit(
            (TableColumn.CellEditEvent<musicVideo, Integer> t) -> {
                int tableRow = t.getTablePosition().getRow();
                musicVideo videoFromTableRow = t.getTableView().getItems().get(tableRow);
                videoFromTableRow.setRanking(t.getNewValue());
            });
   /* name.setOnEditCommit(
            (TableColumn.CellEditEvent<musicVideo, String> t) -> {
                int tableRow = t.getTablePosition().getRow();
                musicVideo VideoFromTableRow = t.getTableView().getItems().get(tableRow);
                VideoFromTableRow.setNames(t.getNewValue());
            });

    */
    interactions.setOnEditCommit(
            (TableColumn.CellEditEvent<music, Float> t) -> {
                int tableRow = t.getTablePosition().getRow();
                music videoFromTableRow = t.getTableView().getItems().get(tableRow);
                videoFromTableRow.setInteractions(t.getNewValue());
            });
    /*
    uploader.setOnEditCommit(
            (TableColumn.CellEditEvent<musicVideo, String> t) -> {
                int tableRow = t.getTablePosition().getRow();
                musicVideo videoFromTableRow = t.getTableView().getItems().get(tableRow);
                videoFromTableRow.setUploader(t.getNewValue());
            });
     */
}
    public void saveData() throws Exception {
        FileOutputStream fileOut = new FileOutputStream("SavedMusicVideoObjects");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);

        // allTheTexts is my ListView. Save its ObservableList by turning it into an ArrayList.
        ArrayList<musicVideo> temporaryList = new ArrayList<>(table.getItems());
        out.writeObject(temporaryList);

        out.close();
        fileOut.close();
    }
    public void restoreOrReadData() throws Exception {
        // First try to restore saved objects, but then read data file if that fails.
        try {
            FileInputStream fileIn = new FileInputStream("SavedMusicVideoObjects");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            // Restored saved objects into Film's arrayList of all films
            music.setAllData((ArrayList<music>)in.readObject());
            in.close();
            fileIn.close();
        } catch (Exception exception) {
            // Restoring saved objects failed, so read data from text file instead
            musicVideo.readAllData();
        }

        ArrayList<music> temporaryList = music.getAllData();
        // Turn the read data's ArrayList into an ObservableList
        ObservableList temporaryObservableList = FXCollections.observableArrayList(temporaryList);
        // Make that ObservableList the list for my TableView
        table.setItems(temporaryObservableList);
    }
}