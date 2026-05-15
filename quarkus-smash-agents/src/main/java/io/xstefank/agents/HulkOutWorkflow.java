package io.xstefank.agents;

import dev.langchain4j.agentic.declarative.ParallelAgent;

public interface HulkOutWorkflow {

    @ParallelAgent(outputKey = "hulkOutResult",
            subAgents = { DrBannerAgent.class, TheHulkAgent.class })
    String hulkOut(short angerLevel);

}
