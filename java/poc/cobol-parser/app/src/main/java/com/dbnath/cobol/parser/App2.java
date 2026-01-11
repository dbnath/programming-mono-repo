package com.dbnath.cobol.parser;
import java.io.File;
import java.io.FileWriter;
import java.io. IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

import io.proleap.cobol.preprocessor.CobolPreprocessor;
import org.antlr.v4.gui.TestRig;
import org.antlr.v4.runtime. CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import io.proleap.cobol. CobolLexer;
import io.proleap.cobol.CobolParser;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;
import io.proleap.cobol.asg.params.CobolParserParams;
import io.proleap.cobol.asg.params.impl.CobolParserParamsImpl;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.Trees;

public class App2 {

    public static void generateTreeFile(File cobolInputFile, File outputDirectory) throws IOException, IOException {
        File outputFile = new File(outputDirectory + "/" + cobolInputFile.getName() + ".tree");

        // Create the output file
        outputFile.createNewFile();

        System.out.println("Creating tree file: " + outputFile);

        // Set up parser parameters
        File parentDirectory = cobolInputFile.getParentFile();
        CobolParserParams params = new CobolParserParamsImpl();
        params.setCopyBookDirectories(Collections.singletonList(parentDirectory));
        params.setFormat(CobolPreprocessor.CobolSourceFormatEnum. FIXED); // Or FIXED, FREE, etc.

        // Preprocess the COBOL file
        String preProcessedInput = new CobolPreprocessorImpl().process(cobolInputFile, params);

        // Create lexer and parser
        CobolLexer lexer = new CobolLexer(CharStreams.fromString(preProcessedInput));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CobolParser parser = new CobolParser(tokens);

        // Parse and generate the tree
        CobolParser.StartRuleContext tree = parser.startRule();


        System. out. println("AST tree file generated successfully!");
        // Find procedure division
        CobolParser.ProcedureDivisionContext procDivCtx = findProcedureDivision(tree);

        if (procDivCtx != null) {
            // Get formatted text with token stream
            int start = procDivCtx.getStart(). getStartIndex();
            int stop = procDivCtx.getStop().getStopIndex();
            String formattedText = tokens.getText(procDivCtx);
            System.out.println("\nFormatted Procedure Division:");
            System.out.println(formattedText);
        }
    }

    private static CobolParser.ProcedureDivisionContext findProcedureDivision(ParserRuleContext ctx) {
        if (ctx instanceof CobolParser.ProcedureDivisionContext) {
            return (CobolParser.ProcedureDivisionContext) ctx;
        }

        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (ctx.getChild(i) instanceof ParserRuleContext) {
                CobolParser.ProcedureDivisionContext result = findProcedureDivision((ParserRuleContext) ctx. getChild(i));
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    public static void main(String[] args) throws Exception {
        File inputFile = new File("app/src/main/resources/data/test.cbl");
        File outputDir = new File("output");
        outputDir.mkdirs();

       generateTreeFile(inputFile, outputDir);
//        TestRig testRig = new TestRig(new String[]{"io.proleap.cobol.Cobol", "startRule", "-gui", "app/src/main/resources/data/CM101M.CBL"});
//        testRig.process();
    }
}
