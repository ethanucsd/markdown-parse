import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParseTest {
    @Before
    public void setUp() {
        
    }

    @Test
    public void testNoEntries() throws IOException {
        Path fileName = Path.of("noEntries");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(0, links.size());
    }
    
    @Test
    public void testLinkWithParenthesis() throws IOException {
        Path fileName = Path.of("linkWithParenthesis");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals("https://something.com", links.get(0));
    }
    
    @Test
    public void testBrokenRef() throws IOException {
        Path fileName = Path.of("brokenRef");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(0, links.size());
    }
}