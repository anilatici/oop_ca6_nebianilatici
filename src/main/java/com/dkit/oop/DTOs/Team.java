package com.dkit.oop.DTOs;

public class Team {
    private int id;
    private String name;
    private String country;
    private String powerUnit;
    private int wins;
    private float budget;

    public Team(int id, String name, String country, String powerUnit, int wins, float budget) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.powerUnit = powerUnit;
        this.wins = wins;
        this.budget = budget;
    }

    public Team(String name, String country, String powerUnit, int wins, float budget)
    {
        this.id = 0;
        this.name = name;
        this.country = country;
        this.powerUnit = powerUnit;
        this.wins = wins;
        this.budget = budget;
    }

    public Team()
    {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPowerUnit() {
        return powerUnit;
    }

    public void setPowerUnit(String powerUnit) {
        this.powerUnit = powerUnit;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", powerUnit='" + powerUnit + '\'' +
                ", wins=" + wins +
                ", budget=" + budget +
                '}' +"\n";
    }
}
