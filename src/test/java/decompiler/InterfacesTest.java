package decompiler;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

import static decompiler.TestUtils.TESTS_DIRECTORY;
import static decompiler.TestUtils.compile;
import static decompiler.TestUtils.writeDecompiledFile;

public class InterfacesTest {
    //TODO: DI
    Decompiler decompiler = new Decompiler();
    Loader loader = new Loader();

    @AfterClass //TODO inherit from abstract test
    public static void clear() {

//        purgeDirectory(TestUtils.OUTPUT_DIRECTORY);
//        Files.createDirectory("temp", null);
    }

    @Test
    public void testEmptyInterface() throws IOException {
        routine("EmptyInterface");
    }

    @Test
    public void testInterfaceExtend() throws IOException {
        routine("InterfaceExtend");
    }

    @Test
    public void testInterfaceWithFields() throws IOException {
        routine("InterfaceWithFields");
    }

    @Test
    public void testFullInterface() throws IOException {
        routine("FullInterface");
    }

    @Test
    public void testGenericInterface() throws IOException {
        routine("GenericInterface");
    }

    @Test
    public void testAnnotationInterface() throws IOException {
        routine("AnnotationInterface");
    }

    public static void purgeDirectory(String path) {

        File file = new File(path);
        File[] files = file.listFiles();
        assert files != null;
        for (File f : files) {
            if (f.isFile() && f.exists()) {
                f.delete();
            }
        }
    }

    public void routine (String name) throws IOException {
        final String path = TESTS_DIRECTORY  +"interfaces\\" + name + ".class";
        String content = decompiler.decompile(loader.loadFromFileSystem(path)).toString();
        String res = writeDecompiledFile(name + ".groovy", content);
        System.out.println(content);
        Assert.assertTrue(compile(res));
    }
}