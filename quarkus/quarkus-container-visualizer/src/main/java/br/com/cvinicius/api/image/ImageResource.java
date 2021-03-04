package br.com.cvinicius.api.image;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cvinicius.core.domain.image.ImageResolver;

@ApplicationScoped
@Path("images")
@Produces(MediaType.APPLICATION_JSON)
public class ImageResource {

	@Inject
	private ImageResolver imageResolver;

	@GET
	public Response listAll() {

		return Response.ok(imageResolver.listAll()).build();
	}
}
