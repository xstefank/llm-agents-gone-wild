package io.xstefank.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RegisterAiService
public interface SmashingVisualizationAgent {

    @SystemMessage("""
      You are a visualization agent. Based on the smashing summary provided,
      write a vivid, dramatic image generation prompt (1-2 sentences) that
      captures the scene. Focus on the character (Hulk or Dr. Banner),
      the object being smashed, and the emotional intensity.
      Output only the image prompt text.
      """)
    @UserMessage("Generate an image of the smashing process based on the following summary: {smashSummary}")
    @Agent(description = "Visualization agent that creates a visual representation of the smashing process based on the provided smashing summary.", outputKey = "smashVisualization")
    Image visualizeSmash(String smashSummary);
}
