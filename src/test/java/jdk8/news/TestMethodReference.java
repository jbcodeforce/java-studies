package jdk8.news;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class TestMethodReference {
	
	@Test
	public void testFileSystem(){
		FileSystem fs = FileSystems.getDefault() ;
		for (Path p : fs.getRootDirectories()) {
			System.out.println(p.toString());
		}
	}

	@Test
	public void test() throws IOException {
		Files.lines(Paths.get("src/test/java/jdk8/news/TestMethodReference.java"))
		             .map(String::trim)
		             .forEach(System.out::println);
	}

}
