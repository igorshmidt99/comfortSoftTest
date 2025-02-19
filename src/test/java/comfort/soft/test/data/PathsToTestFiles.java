package comfort.soft.test.data;

public enum PathsToTestFiles {

    DATA("data.xlsx"),
    BROKEN("broken.xlsx"),
    BROKEN_2("broken2.xlsx"),
    NO_SUCH_FILE("qwer.xlsx"),
    EMPTY("empty.xlsx");

    public final String path;

    PathsToTestFiles(String file) {
        this.path = String.format("src/test/resources/%s", file);

    }
}
