package org.disneyWorld.sre.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dizitart.no2.Nitrite;
import org.disneyWorld.sre.exceptions.CharacterAlreadyExistsException;
import org.disneyWorld.sre.model.Character;
import org.dizitart.no2.objects.ObjectRepository;
import org.disneyWorld.sre.exceptions.StockUnavailable;

import java.util.Objects;
import static org.disneyWorld.sre.services.FileSystemService.getPathToFile;

public class CharacterService {
    private static ObjectRepository<Character> characterRepository;

     public static void initDatabase(String supplier) {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile(supplier+".db").toFile())
                .openOrCreate("test", "test");

        characterRepository = database.getRepository(Character.class);
    }

    public static ObservableList<Character> getCharacters(){
        ObservableList characterList= FXCollections.observableArrayList();
        characterList.removeAll();
        for (Character character:characterRepository.find()){
            characterList.add(character);
        }
        return characterList;
    }

    public static void addCharacter(String name, String ageCategory, float price, int stock) throws CharacterAlreadyExistsException{
        checkCharacterDoesNotAlreadyExist(name);
        characterRepository.insert(new Character(name,ageCategory,price,stock));
    }

    public static void editCharacter(Character character, String name, String ageCategory, float price, int stock){
        characterRepository.remove(character);
        characterRepository.insert(new Character(name, ageCategory, price, stock));
    }

    public static void clearDatabase(){
        for (Character character: characterRepository.find()){
            characterRepository.remove(character);
        }
    }

    public static int getLastIndex(){
        return getCharacters().toArray().length;
    }

    public static void deleteCharacter(Character character){
        characterRepository.remove(character);
    }

    public static void checkCharacterDoesNotAlreadyExist(String name) throws CharacterAlreadyExistsException {
        for (Character character: characterRepository.find()){
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

