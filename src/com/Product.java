package com;
import Classes.Product_Category;
import org.pojava.datetime.DateTime;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 * Created by romanchubak on 01.11.2016.
 */
@XmlRootElement(name = "Product")
@XmlType(propOrder = {"category","name","date_of_manufacture","date_of_admission","date_fitness"})
public class Product {
    private Product_Category category;
    private  String name;
    private DateTime date_of_manufacture;
    private  DateTime date_of_admission;
    private  DateTime date_fitness;

    public Product()
    {
        category = null;
        name = "";
        date_fitness = null;
        date_of_admission = null;
        date_of_manufacture = null;
    }

    public  Product(Product_Category pc, String n, DateTime d_of_admission, DateTime d_fitness, DateTime d_of_manufacture)
    {
        category = pc;
        name = n;
        date_of_manufacture = d_of_manufacture;
        date_of_admission = d_of_admission;
        date_fitness = d_fitness;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Product_Category getCategory() {
        return category;
    }
    public void setCategory(Product_Category category) {
        this.category = category;
    }

    public DateTime getDate_of_manufacture() {
        return date_of_manufacture;
    }
    public void setDate_of_manufacture(DateTime date_of_manufacture) {
        this.date_of_manufacture = date_of_manufacture;
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

    @Override
    public String toString()
    {
        return "Product [ name=" + name + ", category=" + category + ", Date of manufacture=" + date_of_manufacture +
                ", Date of admission=" + date_of_admission + ", Date fitness=" + date_fitness +"]";
    }
}
