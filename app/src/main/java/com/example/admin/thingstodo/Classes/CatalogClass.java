package com.example.admin.thingstodo.Classes;

import java.io.Serializable;

/**
 * Created by Admin on 2/5/2018.
 */

public class CatalogClass implements Serializable {

    String id;
    String date;
    String eventTitle;
    String description;
    String price;
    String discount;
    String imageurl;
    String imageurl2;
    String imageurl3;
    String location;
    String time;
    String fulldesscription;
    String cellno;


    public CatalogClass() {
    }

    public CatalogClass(String id, String s) {

    }


    public CatalogClass(String id, String date, String eventTitle, String description, String price,
                        String discount, String imageurl,String imageurl2, String imageurl3,  String location, String time,String fulldesscription, String cellno,  String enquiries ) {

        this.id = id;
        this.date = date;
        this.eventTitle = eventTitle;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.imageurl = imageurl;
        this.imageurl2 = imageurl2;
        this.imageurl3 = imageurl3;
        this.location = location;

        this.time = time;
        this.fulldesscription = fulldesscription;
        this.cellno = cellno;
        this.enquiries = enquiries;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFulldesscription() {
        return fulldesscription;
    }

    public void setFulldesscription(String fulldesscription) {
        this.fulldesscription = fulldesscription;}

    public String getCellno() {
        return cellno;
    }

    public void setCellno(String cellno) {
        this.cellno = cellno;
    }

    public String getEnquiries() {
        return enquiries;
    }

    public void setEnquiries(String enquiries) {
        this.enquiries = enquiries;
    }

    String enquiries;


    public String getImageurl2() {
        return imageurl2;
    }

    public void setImageurl2(String imageurl2) {
        this.imageurl2 = imageurl2;
    }

    public String getImageurl3() {
        return imageurl3;
    }

    public void setImageurl3(String imageurl3) {
        this.imageurl3 = imageurl3;
    }

}



