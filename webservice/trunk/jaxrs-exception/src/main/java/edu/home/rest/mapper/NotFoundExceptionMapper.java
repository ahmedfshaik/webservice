package edu.home.rest.mapper;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import edu.home.rest.exception.NotFoundException;
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException>{
  // @Override
   public Response toResponse(NotFoundException exception){
          return Response.status(404).entity(exception.getMessage()).type("text/plain").build();
   }
}
