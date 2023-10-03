/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package todoapp;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author nadim
 */
public class HomePageController implements Initializable {

    @FXML
    private ListView<Event> eventList;
    @FXML
    private Button newBtn;
    @FXML
    private Button deleteBtn;
    private TextField name;
    @FXML
    private TextField eventName;
    @FXML
    private TextArea eventDescription;
    @FXML
    private DatePicker eventDate;
    @FXML
    private RadioButton today;
    @FXML
    private RadioButton all;
    @FXML
    private ToggleGroup eventShow;
    @FXML
    private Button editBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        eventDate.setValue(LocalDate.now());
        //fileRead();
    }    

    @FXML
    private void deleteEvent(MouseEvent event) throws IOException {
        int eventId=eventList.getSelectionModel().getSelectedIndex();
        eventList.getItems().remove(eventId);
        fileWrite(eventList);
    }

    @FXML
    private void addEvent(MouseEvent event) throws IOException {
        Event obj=new Event(eventDate.getValue(),eventName.getText(),eventDescription.getText());
        eventList.getItems().add(obj);
        eventDate.setValue(LocalDate.now());
        eventName.setText("");
        eventDescription.setText("");
        fileWrite(eventList);
    }

    @FXML
    private void editEvent(MouseEvent event) throws IOException {
        int eventId=eventList.getSelectionModel().getSelectedIndex();
        Event obj=new Event(eventDate.getValue(),eventName.getText(),eventDescription.getText());
        eventList.getItems().set(eventId, obj);
        eventDate.setValue(LocalDate.now());
        eventName.setText("");
        eventDescription.setText("");
        fileWrite(eventList);
    }
    
    private void fileWrite(ListView<Event> obj) throws IOException{
        FileWriter writer=new FileWriter("input.txt");
        PrintWriter printer=new PrintWriter(writer);
        
        int size=eventList.getItems().size();
        for(int i=0;i<eventList.getItems().size();i++){
            printer.println(obj.getItems().get(i).date);
            printer.println(obj.getItems().get(i).name);
            printer.println(obj.getItems().get(i).description);
        }
        printer.close();
        writer.close();
    }
    
    private void fileRead(){   
        String edate = "";
        String ename = "";
        String edescription = "";
            
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            
            while ((edate = reader.readLine()) != null) {
                ename = reader.readLine();
                edescription = reader.readLine();
                
                Event obj=new Event(edate,ename,edescription);
                eventList.getItems().add(obj);
            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void todaysEvent(ActionEvent event) {
        eventList.getItems().clear();
        String edate = "";
        String ename = "";
        String edescription = "";
            
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            
            while ((edate = reader.readLine()) != null) {
                ename = reader.readLine();
                edescription = reader.readLine();
                
                Event obj=new Event(edate,ename,edescription);
                if(edate.equals(LocalDate.now().toString())){
                    eventList.getItems().add(obj);
                }
            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        newBtn.setDisable(true);
        editBtn.setDisable(true);
        deleteBtn.setDisable(true);
    }

    @FXML
    private void allEvent(ActionEvent event) {
        eventList.getItems().clear();
        fileRead();
        newBtn.setDisable(false);
        editBtn.setDisable(false);
        deleteBtn.setDisable(false);
    }

}
