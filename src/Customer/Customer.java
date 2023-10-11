package Customer;

import java.util.Random;

public class Customer {

    Long id;
    String name;
    Integer ties;

    public Customer( String name, Integer ties) {
        Random random = new Random();

        this.id= random.nextLong(1000000000);
        this.name = name;
        this.ties = ties;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTies() {
        return ties;
    }

    public void setTies(Integer ties) {
        this.ties = ties;
    }
}
