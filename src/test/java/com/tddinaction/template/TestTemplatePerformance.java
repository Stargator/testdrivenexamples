package com.tddinaction.template;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestTemplatePerformance {
    private Template template;

    @Before
    public void setUp() throws Exception {
        String templateText = "${one}${two}${3}${four}${5}${6}${7}${8}${9}${10}${11}${12}${13}${14}" +
                "${15}${16}${17}${18}${19}${20}";
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

    @Test
    public void templateWith100WordsAnd20Variables() throws Exception {
        long expected = 200L;
        long time = System.currentTimeMillis();
        template.evaluate();
        time = System.currentTimeMillis() - time;
        assertTrue("Rendering the template took " + time
                        + "ms while the target was " + expected + "ms",
                time <= expected);
    }
}