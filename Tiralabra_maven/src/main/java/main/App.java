package main;

import PackerX.Packer;

final class App {

    public static void main(final String[] args) {
        final Packer packer = new Packer(args);
        packer.run();
    }
}
