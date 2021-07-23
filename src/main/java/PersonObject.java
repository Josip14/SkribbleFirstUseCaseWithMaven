import com.opencsv.bean.CsvBindByPosition;

/**
 * This class is extendable as you like
 * you can add as much as you want of CSVBindByPosition as soon you have enough columns of the Object in your .csv file
 * means you can add birthday, address, gender ... but you need them in your .csv file
 * to get this information you need to set getters like the examples below
 */

public class PersonObject {

    @CsvBindByPosition(position = 0)
    private String firstName;

    @CsvBindByPosition(position = 1)
    private String lastName;

    @CsvBindByPosition(position = 2)
    private String email;

    //  getters, setters, toString
    public String getFirstName(){
        return firstName;
    }
    public String getEmail(){
        return email;
    }
    public String getLastName(){
        return lastName;
    }

}


