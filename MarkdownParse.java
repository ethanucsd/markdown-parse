// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static int getNthSlashN(String markdown, int lineCount) {
        if (lineCount > 0) {
            int place = 0;
            int i = 0;
            while (place < markdown.length()) {
                if (markdown.charAt(place) == '\n') {
                    i++;
                    if (i == lineCount) return place;
                }
                place++;
            }

        } else return 0;
        return -1;
    }

    public static String getCurrentLine(String markdown, int lineCount) {
        int begin = getNthSlashN(markdown, lineCount);
        int end = getNthSlashN(markdown, lineCount + 1);
        
        if (begin == -1) return null;
        if (end == -1) end = markdown.length();
        
        return markdown.substring(begin, end - 1);
    }

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentLine = 0;
        while(getCurrentLine(markdown, currentLine) != null) {
            String line = getCurrentLine(markdown, currentLine);
            int nextOpenBracket = line.indexOf("[", 0);
            int nextCloseBracket = line.lastIndexOf("]", nextOpenBracket);
            int openParen = line.lastIndexOf("(", nextCloseBracket);
            int closeParen = line.lastIndexOf(")", openParen);
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