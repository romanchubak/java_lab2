package lab1;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Created by Роман on 27.09.2016.
 */

enum Product_Category { DAIRY , MEAT, CEREALS ,ALCOHOL , BAKERY, FISH, GROCERY }

public class Product
{
    private  Product_Category category;
    private  String name;
    private  Calendar date_of_manufacture;
    private  Calendar date_of_admission;
    private  Calendar date_fitness;

    public Product( Product_Category c, String n, Calendar Date_of_manufacture, Calendar Date_of_admission, Calendar Date_fitness)
    {
        category = c;
        name = n;
        date_of_manufacture = Date_of_manufacture;
        date_of_admission = Date_of_admission;
        date_fitness = Date_fitness;
    }

    public Product_Category getCategory() {
        return category;
    }
    public void setCategory(Product_Category category) {
        this.category = category;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String Name)
    {
        this.name = Name;
    }

    public void setDate_of_manufacture(Calendar date_of_manufacture) {
        this.date_of_manufacture = date_of_manufacture;
    }
    public Calendar getDate_of_manufacture() {

        return date_of_manufacture;
    }

    public Calendar getDate_of_admission() {
        return date_of_admission;
    }
    public void setDate_of_admission(Calendar date_of_admission) {
        this.date_of_admission = date_of_admission;
    }

    public Calendar getDate_fitness() {
        return date_fitness;
    }
    public void setDate_fitness(Calendar date_fitness) {
        this.date_fitness = date_fitness;
    }

    public boolean IsCategory(Product_Category Category)
    {
        return   this.category == Category;
    }


    @Override
    public String toString() {
        return "Product{" +
                "category=" + category +
                ", name='" + name + '\'' +
                ", date_of_manufacture=" + date_of_manufacture +
                ", date_of_admission=" + date_of_admission +
                ", date_fitness=" + date_fitness +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return category == product.category;
    }

}
