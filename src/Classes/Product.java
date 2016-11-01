package Classes;
import org.pojava.datetime.DateTime;
import java.lang.Object;
import Exception.DateException;

public class Product
{
    private  Product_Category category;
    private  String name;
    private  DateTime date_of_manufacture; // DateTime:  http://www.pojava.org/site/pojava-datetime-3.0.0/apidocs/org/pojava/datetime/DateTime.html#DateTime(java.lang.String)
    private  DateTime date_of_admission;
    private  DateTime date_fitness;

    public Product()
    {
        category = null;
        name = "";
        date_of_manufacture = new DateTime("2016/10/21T10:00:00Z");
        date_of_admission =  new DateTime("2016/10/21T10:00:00Z");
        date_fitness =  new DateTime("2016/10/21T10:00:00Z");
    }

    public  Product(String tname, Product_Category tcategory)
    {
        category = tcategory;
        name = tname;
        date_of_manufacture = new DateTime("2016/10/21T10:00:00Z");
        date_of_admission =  new DateTime("2016/10/21T10:00:00Z");
        date_fitness =  new DateTime("2016/10/21T10:00:00Z");
    }

    public Product( Product_Category c, String n, DateTime Date_of_manufacture, DateTime Date_of_admission, DateTime Date_fitness) throws DateException {
        category = c;
        name = n;
        if(Date_of_manufacture.compareTo(Date_fitness)>0)  throw new DateException();
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

    public void setDate_of_manufacture(DateTime date_of_manufacture) {
        this.date_of_manufacture = date_of_manufacture;
    }
    public DateTime getDate_of_manufacture() {

        return date_of_manufacture;
    }

    public DateTime getDate_of_admission() {
        return date_of_admission;
    }
    public void setDate_of_admission(DateTime date_of_admission) {
        this.date_of_admission = date_of_admission;
    }

    public DateTime getDate_fitness() {
        return date_fitness;
    }
    public void setDate_fitness(DateTime date_fitness) {
        this.date_fitness = date_fitness;
    }

    public boolean IsCategory(Product_Category Category)
    {
        return   this.category == Category;
    }


    @Override
    public String toString() {
        return  ">>>Product: " + name +
                "\n>>>Category: " + category +
                "\n>>>Date of manufacture: " + date_of_manufacture +
                "\n>>>Date of admission: " + date_of_admission +
                "\n>>>Date fitness: " + date_fitness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return category == product.category;
    }

}
