// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static final String NEW_LINE = System.getProperty("line.separator");
    public static String getCurrentLine(String markdown, int lineCount) {
        String[] strings = markdown.split(NEW_LINE);
        if (strings.length <= lineCount) return null;
        return strings[lineCount];
    }

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentLine = 0;
        while(getCurrentLine(markdown, currentLine) != null) {
            String line = getCurrentLine(markdown, currentLine);
            int nextOpenBracket = line.indexOf("[", 0);
            int nextCloseBracket = line.lastIndexOf("]", markdown.length());
            int openParen = line.indexOf("(", nextCloseBracket);
            int closeParen = line.lastIndexOf(")", markdown.length());
            if (!(nextOpenBracket == -1 || nextCloseBracket == -1 || openParen == -1 || closeParen == -1)) 
                toReturn.add(line.substring(openParen + 1, closeParen));
                
            currentLine++;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}