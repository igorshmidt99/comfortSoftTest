package comfort.soft.test.data;

import comfort.soft.test.search.dto.NReqDto;

import static comfort.soft.test.data.PathsToTestFiles.*;

public class TestData {

    public static final NReqDto foundSix = new NReqDto(DATA.path, 6);
    public static final NReqDto isNotExist = new NReqDto(DATA.path, 100);
    public static final NReqDto noSuchFile = new NReqDto(NO_SUCH_FILE.path, 6);
    public static final NReqDto emptyFile = new NReqDto(EMPTY.path, 0);
    public static final NReqDto withTextInjection = new NReqDto(BROKEN_2.path, 2);
    public static final NReqDto onlyNumbers = new NReqDto(BROKEN.path, 123);

    public static final String noSuchValue = "No such value.";

}
