package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class User {
    private String firstName;
    private String lastName;
    private String nickName;
    private LocalDate birthDate;

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
