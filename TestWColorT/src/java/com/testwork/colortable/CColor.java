package com.testwork.colortable;

/*
CColor :
    id  - color_number
    Name - name
 */
public class CColor {

    private final String id; // color_number

    private final String Name; //name

    CColor(String pId, String pName) {
        this.id = pId;
        this.Name = pName;
    }

    public String GetId() {
        return this.id;
    }

    public String GetName() {
        return this.Name;
    }
}
