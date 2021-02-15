package gui;

public class Utils {
    public static String getFileExtention(String name){
        int index = name.lastIndexOf(".");
        if (index == -1){
            return null;
        }
        if(index == name.length() -1){
            return null;
        }
        return name.substring(index+1,name.length());
    }
}
