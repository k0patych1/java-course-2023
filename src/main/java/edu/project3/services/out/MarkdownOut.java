package edu.project3.services.out;

import edu.project3.entities.LogReport;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MarkdownOut implements IOut {
    @Override
    public void writeStatistics(LogReport logReport, String filePath) throws IOException {
        StringBuilder output = new StringBuilder();

        output.append("#### Общая информация\n\n");
        output.append(generateGeneralInfoTable(logReport));
        output.append("\n");

        output.append("#### Запрашиваемые ресурсы\n\n");
        output.append(generateResourceTable(logReport));
        output.append("\n");

        output.append("#### Коды ответа\n\n");
        output.append(generateResponseCodeTable(logReport));
        output.append("\n");

        output.append("#### Протоколы\n\n");
        output.append(generateProtocolTable(logReport));
        output.append("\n");

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(output.toString());
        }
    }

    private String generateGeneralInfoTable(LogReport logReport) {
        StringBuilder table = new StringBuilder();
        table.append("|           Метрика           | Значение |\n");
        table.append("|:---------------------------:|---------:|\n");
        table.append(String.format("|           Файл(-ы)          |   `%s`   |\n", getFieldText(logReport.getFiles())));
        table.append(String.format("|          Ссылка(-и)         |   `%s`   |\n", getFieldText(logReport.getUrls())));
        table.append(String.format("|        Начальная дата       |    %s    |\n", logReport.getFromDate()));
        table.append(String.format("|         Конечная дата       |    %s    |\n", logReport.getToDate()));
        table.append(String.format("|     Количество запросов     |    %d    |\n", logReport.getTotalRequests()));
        table.append(String.format("|    Средний размер ответа    |   %.2f   |\n", logReport.getAverageResponseSize()));
        table.append(String.format("| Максимальный размер ответа  |    %d    |\n", logReport.getMaximumResponseSize()));
        table.append(String.format("|  Минимальный размер ответа  |    %d    |\n", logReport.getMinimumResponseSize()));

        return table.toString();
    }

    private String generateResourceTable(LogReport logReport) {
        StringBuilder table = new StringBuilder();

        table.append("|     Ресурс      | Количество |\n");
        table.append("|:---------------:|-----------:|\n");
        for (String resource : logReport.getResourceFrequency().keySet()) {
            table.append(String.format("|    `%s`    |    %d   |\n",
                resource,
                logReport.getResourceFrequency().get(resource)));
        }

        return table.toString();
    }

    private String generateResponseCodeTable(LogReport logReport) {
        StringBuilder table = new StringBuilder();

        table.append("| Код |          Имя          | Количество |\n");
        table.append("|:---:|:---------------------:|-----------:|\n");
        for (Integer value : logReport.getResponseCodeFrequency().keySet()) {
            table.append(String.format("| %d |          %s           |  %d  |\n",
                value,
                getCodeName(value),
                logReport.getResponseCodeFrequency().get(value)));
        }

        return table.toString();
    }

    private String generateProtocolTable(LogReport logReport) {
        StringBuilder table = new StringBuilder();

        table.append("| Протокол | Количество |\n");
        table.append("|:--------:|:----------:|\n");
        for (String value : logReport.getProtocolFrequency().keySet()) {
            table.append(
                String.format("| %s |    %d   |\n",
                    value,
                    logReport.getProtocolFrequency().get(value)));
        }

        return table.toString();
    }

    private String getFieldText(List<String> field) {
        if (field == null || field.isEmpty()) {
            return "-";
        }

        return String.join(", ", field);
    }
}
