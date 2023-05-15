package org.disneyWorld.sre.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.disneyWorld.sre.exceptions.CharacterAlreadyExistsException;
import org.disneyWorld.sre.model.DisneyCharacter;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.disneyWorld.sre.exceptions.StockUnavailable;

import java.util.Objects;

public class DisneyCharacterService {
    private static ObjectRepository<DisneyCharacter> characterRepository;

    /* public static void initDatabase(String characterCategory) {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile(characterCategory+".db").toFile())
                .openOrCreate("test", "test");

        characterRepository = database.getRepository(DisneyCharacter.class);
    } */

    public static ObservableList<DisneyCharacter> getCharacters(){
        ObservableList characterList= FXCollections.observableArrayList();
        characterList.removeAll();
        for (DisneyCharacter character:characterRepository.find()){
            characterList.add(character);
        }
        return characterList;
    }

    public static void addCharacter(String name, int age, String category, float price, int stock) throws CharacterAlreadyExistsException{
        checkCharacterDoesNotAlreadyExist(name);
        characterRepository.insert(new DisneyCharacter(name,age,category,price,stock));
    }

    public static void editCharacter(DisneyCharacter character, String name, int age, String category, float price, int stock){
        characterRepository.remove(character);
        characterRepository.insert(new DisneyCharacter(name, age, category, price, stock));
    }

    public static void clearDatabase(){
        for (DisneyCharacter character: characterRepository.find()){
            characterRepository.remove(character);
        }
    }

    public static int getLastIndex(){
        return getCharacters().toArray().length;
    }

    public static void deleteCharacter(DisneyCharacter character){
        characterRepository.remove(character);
    }

    public static void checkCharacterDoesNotAlreadyExist(String name) throws CharacterAlreadyExistsException {
        for (DisneyCharacter character: characterRepository.find()){
            if(Objects.equals(name, character.getName())){
                throw new CharacterAlreadyExistsException(name);
            }
        }
    }
    public static void checkStock(int stock) throws StockUnavailable {
        if(stock<=0)
            throw new StockUnavailable(stock);
    }

}

