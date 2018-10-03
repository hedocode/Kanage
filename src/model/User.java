package model;

import javafx.beans.InvalidationListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class User extends Observable {
    private String firstName;
    private String lastName;
    private String nickName;
    private LocalDate birthDate;

    private List<Observer> observers = new ArrayList<>();

    private PropertyChangeListener pcl = new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            observers.forEach(observer -> observer.update( (Observable)evt.getSource(), null));
        }
    };

    public User(String firstName, String secondName, LocalDate birthDate){
        this.firstName = firstName;
        this.lastName = secondName;
        nickName = null;
        this.birthDate = birthDate;
    }

    public int getAge(){
        return (int)LocalDate.from(birthDate).until(LocalDate.now(), ChronoUnit.YEARS);
    }

    public String getNom() { return lastName; }
    public String getPrenom() { return firstName; }
    public LocalDate getBirthDate(){return birthDate;}

    public void setNom(String nom){ lastName = nom; }
    public void setPrenom(String prenom){ firstName = prenom; }
    public void setBirthDate(LocalDate date){birthDate = date;}

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(firstName);
        sb.append(" "+ lastName);
        sb.append("\n");
        sb.append(getAge());
        sb.append(" ans");
        return sb.toString();
    }
}
