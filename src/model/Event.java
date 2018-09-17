package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Event {
    private String name;
    private String description;

    private Map<Ressource,Integer> ressources = new HashMap<>();

    private LocalDate begin;
    private LocalDate end;


}
