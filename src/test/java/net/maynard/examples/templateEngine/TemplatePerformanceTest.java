package net.maynard.examples.templateEngine;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

import net.maynard.examples.templateEngine.util.Config;
import org.junit.Before;
import org.junit.Test;

public class TemplatePerformanceTest {

    private Template template;
    private final String templateText = "${one}${two}${3}${four}${5}${6}${7}${8}${9}${10}${11}${12}${13}${14}${15}${16}${17}${18}${19}${20}";
    private final long PERFORMANCE_TIME = 65L; // Originally wanted it at 200L, but performance was better than expected. The 

    // SetUp() for creating a 100 word template with 20 variables
    //  and populating it with approximately 15-character values
    @Before
    public void setUp() throws Exception {
        template = new Template(templateText);

        template.set("one", "AIRWORTHINESSES");
        template.set("two", "BIOENGINEERINGS");
        template.set("3",   "CHILDLIKENESSES");
        template.set("four","DEREGISTRATIONS");
        template.set("5",   "ECCLESIOLOGICAL");
        template.set("6",   "FORESIGHTEDNESS");
        template.set("7",   "GEOTECHNOLOGIES");
        template.set("8",   "HALLUCINOGENICS");
        template.set("9",   "IMPOSTHUMATIONS");
        template.set("10",  "JURISPRUDENTIAL");
        template.set("11",  "KINETONUCLEUSES");
        template.set("12",  "LAUGHABLENESSES");
        template.set("13",  "MAGISTRATICALLY");
        template.set("14",  "NONSEGREGATIONS");
        template.set("15",  "ODONTOSTOMATOUS");
        template.set("16",  "PATRIALIZATIONS");
        template.set("17",  "QUERULOUSNESSES");
        template.set("18",  "RECANALIZATIONS");
        template.set("19",  "SCHISTOSOMIASES");
        template.set("20",  "TROUBLESHOOTERS");
    }

    // TODO: Update to improve performance
//    @Test
//    public void patternSearchReplaceTest() throws Exception {
//        long expected = PERFORMANCE_TIME;
//        String localTempText = templateText;
//        long time = 1L;
//
//        for (Map.Entry<String, String> entry : template.variables.entrySet()) {
//            Matcher matcher = Pattern.compile("\\$\\{" + entry.getKey() + "\\}").matcher(localTempText);
//            time = System.currentTimeMillis();
//            template.evaluate();
//            time = System.currentTimeMillis() - time;
//            logTime(time);
//        }
//
//        assertTrue("Rendering template took " + time + "ms, while the target "
//                + "was " + expected + "ms", time <= expected);
//    }

    @Test
    public void templateWith100WordsAnd20Variables() throws Exception {
        long expected = PERFORMANCE_TIME;
        long time = System.currentTimeMillis();
        template.evaluate();
        time = System.currentTimeMillis() - time;
        logTime(time);

        assertTrue("Rendering template took " + time + "ms, while the target "
                + "was " + expected + "ms", time <= expected);
    }

    private void logTime(long time) {
//        String encoding = "utf-8";
        //TODO: Get From Config Property file
        String fullFilePath = Config.logFileLocation;

        try (   FileWriter fw = new FileWriter(fullFilePath, true);
                /*FileOutputStream fileOut = new FileOutputStream("/root/Desktop/GitHub_Projects/timeLog.txt");
                OutputStreamWriter outWriter = new OutputStreamWriter(fileOut, encoding);
                BufferedWriter writer = new BufferedWriter(outWriter)*/
            ) {

            fw.append(Long.toString(time) + "\n");
//            writer.append(Long.toString(time));
//            writer.newLine();
//            writer.flush();
//            writer.close();
        } catch (IOException ex) {
          // report
        }
    }
}
