package lexicon.workshop15springdata.eception;

public class DataDuplicateException extends RuntimeException{

    public DataDuplicateException(String message) {
        super(message);
    }
}
