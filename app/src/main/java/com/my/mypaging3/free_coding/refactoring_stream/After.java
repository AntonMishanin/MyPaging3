package com.my.mypaging3.free_coding.refactoring_stream;

import androidx.annotation.NonNull;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Be careful, because I didn't check it
 */

interface SelfCloseableStream<R> {

    @NonNull
    R invoke() throws IOException;
}

interface FileProvider {

    @NonNull
    File getFile();
}

abstract class SelfCloseableStreamAbstract<R, S extends Closeable> implements SelfCloseableStream<R> {

    @NonNull
    @Override
    final synchronized public R invoke() throws IOException {
        try (S stream = create()) {
            return executeBody(stream);
        }
    }

    @NonNull
    protected abstract S create() throws IOException;

    @NonNull
    protected abstract R executeBody(@NonNull S stream) throws IOException;
}

abstract class FileSource<R, S extends Closeable> extends SelfCloseableStreamAbstract<R, S> implements FileProvider {

    private final File file;

    //TODO: think about synchronized
    public FileSource(@NonNull String path) {
        this(new File(path));
    }

    public FileSource(@NonNull File file) {
        this.file = file;
    }

    @NonNull
    @Override
    public File getFile() {
        return file;
    }
}

abstract class ReadContent extends FileSource<String, InputStream> {

    public ReadContent(@NonNull String path) {
        super(path);
    }

    public ReadContent(@NonNull File file) {
        super(file);
    }

    abstract protected boolean isDataValid(int data);

    @NonNull
    @Override
    final protected String executeBody(@NonNull InputStream stream) throws IOException {
        StringBuilder result = new StringBuilder();
        int data;
        while ((data = stream.read()) >= 0) {
            if (isDataValid(data)) {
                result.append((char) data);
            }
        }
        return result.toString();
    }

    @NonNull
    @Override
    final protected InputStream create() throws IOException {
        return new FileInputStream(getFile());
    }
}

final class ReadContentFull extends ReadContent {

    private static final boolean EVERY_DATA_IS_VALID = true;

    public ReadContentFull(@NonNull String path) {
        super(path);
    }

    public ReadContentFull(@NonNull File file) {
        super(file);
    }

    @Override
    final protected boolean isDataValid(int data) {
        return EVERY_DATA_IS_VALID;
    }
}

final class ReadContentWithoutUnicode extends ReadContent {

    public ReadContentWithoutUnicode(@NonNull String path) {
        super(path);
    }

    public ReadContentWithoutUnicode(@NonNull File file) {
        super(file);
    }

    @Override
    final protected boolean isDataValid(int data) {
        return data < 0x80;
    }
}

final class WriteContent extends FileSource<Void, OutputStream> {

    private final String content;

    public WriteContent(@NonNull String path, @NonNull String content) {
        super(path);
        this.content = content;
    }

    public WriteContent(@NonNull File file, @NonNull String content) {
        super(file);
        this.content = content;
    }

    @NonNull
    @Override
    final protected OutputStream create() throws IOException {
        return new FileOutputStream(getFile());
    }

    @NonNull
    @Override
    final protected Void executeBody(@NonNull OutputStream stream) throws IOException {
        for (int i = 0; i < content.length(); i++) {
            stream.write(content.charAt(i));
        }
        return null;
    }
}