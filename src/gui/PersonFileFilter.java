package gui;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class PersonFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        String name = f.getName();

        String ext = Utils.getFileExtention(name);

        if (ext == null) {
            return false;
        }

        if (ext.equals("per")){
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Person file (.per)";
    }
}
