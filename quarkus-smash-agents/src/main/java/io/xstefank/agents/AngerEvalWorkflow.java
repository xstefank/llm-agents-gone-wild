package io.xstefank.agents;

import dev.langchain4j.agentic.declarative.Output;
import dev.langchain4j.agentic.declarative.SequenceAgent;

public interface AngerEvalWorkflow {

    @SequenceAgent(outputKey = "angerEvaluation",
        subAgents = {AngerEvalAgent.class, HulkOutWorkflow.class, SmashSufficiencyAgent.class})
    String evaluateAngerAndHulkOut(String text);

    @Output
    static String output(String smashSummary) {
        boolean isHulk = smashSummary.startsWith("HULK");
        String smasherClass = isHulk ? "hulk" : "banner";
        String smasherLabel = isHulk ? "THE HULK" : "DR. BANNER";
        String cleanSummary = escape(smashSummary.replaceFirst("^(DR_BANNER|HULK)\\s*", ""));
        String svg = isHulk ? HULK_SVG : BANNER_SVG;

        String resultCard = """
            <div class="result-card %s-result" data-winner="%s">
                <div class="result-header">%s RESPONDS</div>
                %s
                <p class="result-text">%s</p>
            </div>
            """.formatted(smasherClass, smasherClass, smasherLabel, svg, cleanSummary);

        String historyItem = "<div class=\"history-item %s-result\" onclick=\"showPopup(this)\" data-popup=\"%s\">%s<div class=\"history-label\">%s</div></div>"
            .formatted(smasherClass, escapeAttr(resultCard), svg, smasherLabel);

        return resultCard + "<div id=\"history-container\" hx-swap-oob=\"beforeend\">" + historyItem + "</div>";
    }

    private static String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    private static String escapeAttr(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;").replace("\n", "&#10;");
    }

    String HULK_SVG = "<svg class=\"char-svg\" viewBox=\"0 0 80 110\"><use href=\"#symbol-hulk\"/></svg>";
    String BANNER_SVG = "<svg class=\"char-svg\" viewBox=\"0 0 80 110\"><use href=\"#symbol-banner\"/></svg>";
}
