package com.example.hospital_try_1;

public class reports_example_item {

    String loc_date;
    String loc_pdf_link;

    public reports_example_item(String date,String pdf_link)
    {
        loc_date=date;
        loc_pdf_link=pdf_link;
    }

    public String getLoc_date()
    {
        return  loc_date;
    }
    public String getLoc_pdf_link()
    {
        return loc_pdf_link;
    }
}
