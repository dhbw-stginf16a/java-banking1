package banking.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextTable {
    private int cols;
    private int width = 80;

    private List<String> header = new ArrayList<>();
    private List<List<String>> rows = new ArrayList<>();

    public TextTable(int cols) {
        this.cols = cols;
    }

    public void setHeader(String... titles) {
        if (titles.length != cols) {
            throw new IllegalArgumentException(
                    String.format("Header(%s) has to be as long as the cols(%s).",
                            titles.length, cols)
            );
        }
        header = Arrays.asList(titles);
    }

    public void addRow(Object... values) {
        if (values.length != cols) {
            throw new IllegalArgumentException(
                    String.format("Only as many values(%s) as cols(%s) are allowed.",
                            values.length, cols)
            );
        }
        List<String> cells = Arrays.stream(values)
                .map(Object::toString)
                .collect(Collectors.toList());
        rows.add(cells);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String cell : header) {
            builder.append(cell);
            builder.append(" ");
        }
        for (List<String> row : rows) {
            builder.append("\n");
            builder.append(row.stream().reduce("", String::concat));
            builder.append(" ");
        }
        return builder.toString();
    }

    public void addRows(Function<Void, Object>... getters) {
        // TODO
        for (Function<Void, Object> getter : getters) {

        }
        //rows.add();
    }
}
