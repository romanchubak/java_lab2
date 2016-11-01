package Classes;
import org.pojava.datetime.DateTime;
import java.lang.Object;
import Exception.DateException;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.NotNull;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.ParserConfigurationException;

@XmlAccessorType(XmlAccessType.FIELD)
public class Product
{
    @XmlElement(name = "category")
    @SerializedName("category")
    @Expose
    private  String category;
    @XmlElement(name = "name")
    @SerializedName("name")
    @Expose
    private  String name;
    @XmlElement(name = "date_of_manufacture")
    @SerializedName("date_of_manufacture")
    @Expose
    private  String date_of_manufacture; // DateTime:  http://www.pojava.org/site/pojava-datetime-3.0.0/apidocs/org/pojava/datetime/DateTime.html#DateTime(java.lang.String)
    @XmlElement(name = "date_of_admission")
    @SerializedName("date_of_admission")
    @Expose
    private  String date_of_admission;
    @XmlElement(name = "date_fitness")
    @SerializedName("date_fitness")
    @Expose
    private  String date_fitness;

    public Product()
    {
        category = "";
        name = "";
        date_of_manufacture = "";
        date_of_admission =  "";
        date_fitness =  "";
    }

    public  Product(String tname, Product_Category tcategory)
    {

        category = tcategory.toString();
        name = tname;
        date_of_manufacture = "2016/10/21T10:00:00Z";
        date_of_admission = "2016/10/21T10:00:00Z";
        date_fitness = "2016/10/21T10:00:00Z)";
    }

    public Product( Product_Category c, String n, DateTime Date_of_manufacture, DateTime Date_of_admission, DateTime Date_fitness) throws DateException {
        category = c.toString();
        name = n;
        if(Date_of_manufacture.compareTo(Date_fitness)>0)  throw new DateException();
        date_of_manufacture = Date_of_manufacture.toString();
        date_of_admission = Date_of_admission.toString();
        date_fitness = Date_fitness.toString();
    }

    public Product_Category getCategory() {
        return Product_Category.valueOf(category);
    }
    public void setCategory(Product_Category category) {
        this.category = category.toString();
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
        this.date_of_manufacture = date_of_manufacture.toString();
    }
    public DateTime getDate_of_manufacture() {

        return new DateTime(date_of_manufacture);
    }

    public DateTime getDate_of_admission() {
        return new DateTime(date_of_admission);
    }
    public void setDate_of_admission(DateTime date_of_admission) {
        this.date_of_admission = date_of_admission.toString();
    }

    public DateTime getDate_fitness() {
        return new DateTime(date_fitness);
    }
    public void setDate_fitness(DateTime date_fitness) {
        this.date_fitness = date_fitness.toString();
    }

    public boolean IsCategory(Product_Category Category)
    {
        return   Product_Category.valueOf(this.category) == Category;
    }


    @Override
    public String toString() {
        return  ">>>Product: " + name +
                "\n>>>Category: " + category +
                "\n>>>Date of manufacture: " + new DateTime(date_of_manufacture).toString() +
                "\n>>>Date of admission: " + new DateTime(date_of_admission).toString() +
                "\n>>>Date fitness: " + new DateTime(date_fitness).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return category == product.category;
    }

}
