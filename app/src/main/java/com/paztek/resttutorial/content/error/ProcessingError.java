package com.paztek.resttutorial.content.error;

/**
 * @author matthieu
 *
 * Error that can be raised by the processors during fetch, syncing with local data, etc...
 */
public class ProcessingError extends RuntimeException {

    private static final String TAG = ProcessingError.class.getSimpleName();

    private final Kind kind;

    public static ProcessingError networkError(String message, Throwable exception) {
        return new ProcessingError(message, exception, Kind.SERVER);
    }

    public static ProcessingError databaseError(String message, Throwable exception) {
        return new ProcessingError(message, exception, Kind.DATABASE);
    }

    public static ProcessingError unknownError(String message, Throwable exception) {
        return new ProcessingError(message, exception, Kind.UNKNOWN);
    }

    public ProcessingError(String message, Throwable throwable, Kind kind) {
        super(message, throwable);

        this.kind = kind;
    }

    public Kind getKind() {
        return kind;
    }

    public enum Kind {
        SERVER,
        DATABASE,
        UNKNOWN
    };
}
