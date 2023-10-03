package todoapp;

import java.time.LocalDate;

public class Event {
    public String date;
    public String name;
    public String description;

    public Event(LocalDate date, String name, String description) {
        this.date = date.toString();
        this.name = name;
        this.description = description;
    }
    public Event(String date, String name, String description) {
        this.date = date;
        this.name = name;
        this.description = description;
    }
    
    @Override
    public String toString() {
        return this.date+"           "+this.name+"              "+this.description;
    }
}
