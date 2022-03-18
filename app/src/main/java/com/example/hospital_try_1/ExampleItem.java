package com.example.hospital_try_1;

public class ExampleItem {

    private String mdate;
    private String mpdflink;

    public ExampleItem( String date, String pdflink) {

        mdate= date;
        mpdflink = pdflink;
    }

    public String getMdate() {
        return mdate;
    }
    public String getMpdflink() {
        return mpdflink;
    }
}
