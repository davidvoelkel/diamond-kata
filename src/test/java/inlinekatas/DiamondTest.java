package inlinekatas;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.reverse;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class DiamondTest {

    @Test
    public void diamondC() throws Exception {
        assertThat(diamond("C")).isEqualTo(asList(
                                           "__A__",
                                           "_B_B_",
                                           "C___C",
                                           "_B_B_",
                                           "__A__"));
    }

    @Test
    public void mirror() throws Exception {
        assertThat(mirror(asList("a"))).isEqualTo(asList("a"));
        assertThat(mirror(asList("a", "b"))).isEqualTo(asList("a", "b", "a"));
        assertThat(mirror(asList("a", "b", "c"))).isEqualTo(asList("a", "b", "c", "b", "a"));
    }

    @Test
    public void mirrorLine() throws Exception {
        assertThat(mirrorLine("a")).isEqualTo("a");
        assertThat(mirrorLine("ab")).isEqualTo("aba");
    }

    private List<String> diamond(String input) {
        Stream<String> upperLines = Stream.of(
                "__A",
                "_B_",
                "C__");
        return mirror(upperLines
                .map(line -> mirrorLine(line))
                .collect(toList()));
    }

    private String mirrorLine(String line) {
        return mirror(Stream.of(line.split(""))
                     .collect(toList()))
                     .stream()
                     .collect(joining());
    }

    private List<String> mirror(List<String> lines) {
        List<String> allLines = new ArrayList<>();
        List<String> firstLines = lines.subList(0, lines.size() - 1);
        allLines.addAll(firstLines);
        allLines.add(lastCharacterIn(lines));
        List<String> invertedFirstLines = new ArrayList<>(firstLines);
        reverse(invertedFirstLines);
        allLines.addAll(invertedFirstLines);
        return allLines;
    }

    private String lastCharacterIn(List<String> lines) {
        return lines.get(lines.size() - 1);
    }

}
