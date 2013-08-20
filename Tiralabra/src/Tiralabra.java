
/**
 *
 * @author virta
 */
import Dictionary.MultiByteHashedTable;
import MultiByteEntities.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tiralabra {

    public static void main(String[] args) throws IOException {


//        Path path = Paths.get("joku");
//        byte[] randomData = Files.readAllBytes(path);

        byte[] m = Utilities.IntegerConverter.IntegerToByte(171, 1);
        System.out.println(m[0]);

    }

}
