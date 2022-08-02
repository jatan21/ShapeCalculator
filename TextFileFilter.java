import java.io.File;
import java.io.FilenameFilter;

public class TextFileFilter implements FilenameFilter{	//A filter to get only text files
	@Override
	public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(".txt");	//Checks if the file name is a text file
    }
}