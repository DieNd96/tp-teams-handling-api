package fr.sorbonne.paris.nord.university.api.controller;

public class TeamDto {
    private long id;
    private String name;
    private String slogan;

    public TeamDto() {
    }

    public TeamDto(String name, String slogan) {

        this.name = name;
        this.slogan = slogan;
    }

    public TeamDto(long id, String name, String slogan) {
        this.id = id;
        this.name = name;
        this.slogan = slogan;
    }



    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
}
