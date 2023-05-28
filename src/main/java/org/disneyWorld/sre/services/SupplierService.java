package org.disneyWorld.sre.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.disneyWorld.sre.exceptions.UsernameAlreadyExistsException;
import org.disneyWorld.sre.model.ModelPH;

import java.util.Objects;

import static org.disneyWorld.sre.services.FileSystemService.getPathToFile;

public class SupplierService {
    private static ObjectRepository<ModelPH> phRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("suppliers.db").toFile())
                .openOrCreate("test", "test");

        phRepository = database.getRepository(ModelPH.class);
    }

    @org.jetbrains.annotations.NotNull

    public static ObservableList<ModelPH> getSuppliers() {
        ObservableList<ModelPH> supp = FXCollections.observableArrayList();
        for (ModelPH ph : phRepository.find()) {
            if(Objects.equals("Admin", ph.getRole()))
                supp.add(new ModelPH(ph.getSupplierName(), ph.getRole()));
        }
        return supp;
    }

    public static void addUser(String username, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        phRepository.insert(new ModelPH(username, role));
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (ModelPH user : phRepository.find()) {
            if (Objects.equals(username, user.getSupplierName()))
                throw new UsernameAlreadyExistsException(username);
        }
    }
}
