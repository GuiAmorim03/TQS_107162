package tqs.ua.pt;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookSteps {

    static final Logger log = getLogger(lookup().lookupClass());

    private Library library = new Library();
    List<Book> searchedBooks = new ArrayList<>();

    @SuppressWarnings("deprecation")
    @ParameterType("([0-9]{2}) (\\w+) ([0-9]{4})")
    public Date isoDate(String day, String month, String year) {
        int monthValue = Month.valueOf(month.toUpperCase()).getValue();
        return new Date(Integer.parseInt(year), monthValue, Integer.parseInt(day));
    }

    @Given("a book with the title {string}, written by {string}, published in {isoDate}")
    public void addBook(String string, String string2, Date date) {
        library.addBook(new Book(string, string2, date));
    }

    @And("another book with the title {string}, written by {string}, published in {isoDate}")
    public void add_Book(String string, String string2, Date date) {
        library.addBook(new Book(string, string2, date));
    }

    @SuppressWarnings("deprecation")
    @When("the customer searches for books published between {int} and {int}")
    public void findBooks(Integer int1, Integer int2) {
        searchedBooks = library.findBooks(new Date(int1, 1, 1), new Date(int2, 12, 31));
    }

    @Then("{int} books should have been found")
    public void numBooksFound(Integer num) {
        assertEquals(num, searchedBooks.size());
    }

    @Then("Book {int} should have the title {string}")
    public void titleBookFound(Integer pos, String title) {
        assertEquals(searchedBooks.get(pos-1).getTitle(), title);
    }
}