package calofit.storage;

import calofit.commons.exceptions.IllegalValueException;
import calofit.commons.util.JsonUtil;
import calofit.model.AddressBook;
import calofit.testutil.Assert;
import calofit.testutil.TypicalMeals;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_MEALS_FILE = TEST_DATA_FOLDER.resolve("typicalMealsAddressBook.json");
    private static final Path INVALID_MEAL_FILE = TEST_DATA_FOLDER.resolve("invalidMealAddressBook.json");
    private static final Path DUPLICATE_MEAL_FILE = TEST_DATA_FOLDER.resolve("duplicateMealAddressBook.json");

    @Test
    public void toModelType_typicalMealsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_MEALS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalMealsAddressBook = TypicalMeals.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalMealsAddressBook);
    }

    @Test
    public void toModelType_duplicateMeals_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MEAL_FILE,
                JsonSerializableAddressBook.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_MEALS,
                dataFromFile::toModelType);
    }

}