package io.xstefank.agents;

import dev.langchain4j.agentic.declarative.Output;
import dev.langchain4j.agentic.declarative.SequenceAgent;
import dev.langchain4j.data.message.ImageContent;
import io.xstefank.model.Smasher;
import io.xstefank.model.SmashingResponse;

public interface AngerEvalWorkflow {

    @SequenceAgent(outputKey = "angerEvaluation",
        subAgents = {AngerEvalAgent.class, HulkOutWorkflow.class, SmashSufficiencyAgent.class, SmashingVisualizationAgent.class})
    SmashingResponse evaluateAngerAndHulkOut(String text);

    @Output
    static SmashingResponse output(String smashSummary, ImageContent smashingVisualization) {
        return new SmashingResponse(smashSummary.startsWith("DR_BANNER") ? Smasher.DR_BANNER : Smasher.HULK,
            smashSummary.replaceFirst("^(DR_BANNER|HULK)\\s*", ""));
    }
}
