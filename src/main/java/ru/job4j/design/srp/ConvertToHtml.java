package ru.job4j.design.srp;

public class ConvertToHtml implements TextConverter {

    @Override
    public String convert(String text) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("<div>").append(System.lineSeparator())
                .append(text)
                .append("</div>").append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        return sb.toString();
    }
}

