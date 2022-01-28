import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MarkdownParseTest {
    @Before
    public void setUp() {
        
    }

    @Test
    public void testNoEntries() throws IOException, URISyntaxException {
        Path fileName = Path.of("noEntries.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(0, links.size());
    }
    
    @Test
    public void testLinkWithParenthesis() throws IOException, URISyntaxException {
        URL url = this.getClass().getResource("linkWithParenthesis.md");
        Path fileName = Paths.get(url.toURI());
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals("https://something.com", links.get(0));
    }
    
    @Test
    public void testBrokenRef() throws IOException, URISyntaxException {
        URL url = this.getClass().getResource("brokenRef.md");
        Path fileName = Paths.get(url.toURI());
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(0, links.size());
    }
}