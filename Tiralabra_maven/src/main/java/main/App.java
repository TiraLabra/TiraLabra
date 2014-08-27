package main;

import PackerX.Packer;
import java.io.IOException;

final class App {

    public static void main(final String[] args) throws IOException {
        if (args.length == 0) {
            final Packer packer = new Packer(new String[]{"pack", "D:\\Timo\\GitHub\\TiraLabra\\Tiralabra_maven\\target\\test.txt"});
            packer.run();
            return;
        }
        final Packer packer = new Packer(args);
        packer.run();
        System.out.println("Done");
    }
}
