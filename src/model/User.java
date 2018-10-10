package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class User {

    private String firstName;
    private String lastName;
    private String nickName;
    private LocalDate birthDate;

    public static final String PROP_FN = "firstName";

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }


    public User(String firstName, String secondName, LocalDate birthDate){
        this.firstName = firstName;
        this.lastName = secondName;
        nickName = null;
        this.birthDate = birthDate;
    }

    public int getAge(){
        return (int)LocalDate.from(birthDate).until(LocalDate.now(), ChronoUnit.YEARS);
    }

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
