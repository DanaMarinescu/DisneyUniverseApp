package org.disneyWorld.sre.exceptions;

public class CharacterAlreadyExistsException extends Exception{
    private String name;

    public CharacterAlreadyExistsException(String name){
        super(String.format("A character with the name %s already exists!", name));
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
