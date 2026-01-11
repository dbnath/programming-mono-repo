package com.dbnath.cobol.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Section;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportProcedureToJSON {

    public static void exportProcedureLogic(ProcedureDivision procedureDivision, String outputFile) throws Exception {
        Map<String, Object> procedureData = new HashMap<>();

        // Extract sections
        List<Map<String, Object>> sectionsData = new ArrayList<>();
        for (Section section: procedureDivision.getSections()) {
            Map<String, Object> sectionMap = new HashMap<>();
            sectionMap.put("name", section. getName());
            sectionMap. put("paragraphs", extractParagraphsData(section. getParagraphs()));
            sectionsData.add(sectionMap);
        }
        procedureData.put("sections", sectionsData);

        // Extract root paragraphs
        procedureData.put("paragraphs", extractParagraphsData(procedureDivision. getRootParagraphs()));

        // Write to JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(outputFile)) {
            gson.toJson(procedureData, writer);
        }

        System.out.println("Procedure logic exported to: " + outputFile);
    }

    private static List<Map<String, Object>> extractParagraphsData(List<Paragraph> paragraphs) {
        List<Map<String, Object>> paragraphsData = new ArrayList<>();

        for (Paragraph paragraph: paragraphs) {
            Map<String, Object> paragraphMap = new HashMap<>();
            paragraphMap.put("name", paragraph.getName());

            List<String> statements = new ArrayList<>();
            for (Statement statement: paragraph.getStatements()) {
                statements. add(statement.getStatementType().toString());
            }
            paragraphMap.put("statements", statements);
            paragraphMap.put("statementCount", statements.size());

            paragraphsData.add(paragraphMap);
        }

        return paragraphsData;
    }
}
