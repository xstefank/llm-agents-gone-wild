package io.xstefank.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface SmashingVisualizationAgent {

    @SystemMessage("""
        You are a visualization agent that creates a visual representation of the smashing process.
        Based on the provided smashing response, generate a simple ASCII art or textual description that illustrates the smashing action.
        Focus on conveying the intensity and impact of the smash in a creative way.
        """)
    @UserMessage("Create a visual representation of the smashing process based on the following summary: {smashSummary}")
    @Agent(description = "Visualization agent that creates a visual representation of the smashing process based on the provided smashing summary.", outputKey = "smashVisualization")
    ImageContent visualizeSmash(String smashSummary);
}
