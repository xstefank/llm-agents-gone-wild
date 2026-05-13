package io.xstefank;

import io.xstefank.agents.AngerEvalAgent;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@Path("/anger")
public class AngerResource {

    private static final Logger LOG = Logger.getLogger(AngerResource.class);

    @Inject
    AngerEvalAgent angerEvalAgent;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public String submitAnger(@RestForm String angerText, @RestForm FileUpload angerFile) {
        LOG.infof("Received anger text: %s", angerText);
        if (angerFile != null) {
            LOG.infof("Received anger file: %s (%d bytes)", angerFile.fileName(), angerFile.size());
        }

        short angerLevel = angerEvalAgent.angerEvaluation(angerText);
        LOG.infof("Anger evaluation " + angerLevel);

        return "<p>Anger evaluated: " + angerLevel + "</p>";
    }
}
