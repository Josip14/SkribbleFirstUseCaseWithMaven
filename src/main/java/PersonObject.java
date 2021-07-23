import com.opencsv.bean.CsvBindByPosition;

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


