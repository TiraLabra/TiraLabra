package Compressors;

import PackerX.FileStream;

public abstract class FileCompressionController {

    private final FileStream file;

    protected FileCompressionController(final FileStream file) {
        this.file = file;
    }

    protected FileStream getFile() {
        return file;
    }

    public abstract void processFile();
}
