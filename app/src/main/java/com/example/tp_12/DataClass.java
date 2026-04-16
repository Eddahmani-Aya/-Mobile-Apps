package com.example.tp_12;

public class DataClass {
    private String key ;
    private String dataTitle ;
    private String dataDesc ;
    private String dataLang ;
    public DataClass ( String key ,
                       String dataTitle ,
                       String dataDesc ,
                       String dataLang ) {
        this . key = key ;
        this . dataTitle = dataTitle ;
        this . dataDesc = dataDesc ;
        this . dataLang = dataLang ;
    }
    public String getKey () { return key ; }
    public String getDataTitle () { return dataTitle ; }
    public String getDataDesc () { return dataDesc ; }
    public String getDataLang () { return dataLang ; }
}
