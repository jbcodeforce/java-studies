package jbcodeforce.microprofile.app;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// The @ApplicationPath annotation has a value that indicates the path within 
// the WAR that the JAX-RS application accepts requests from.
@ApplicationPath("System")
public class BasicRestApp extends Application {

}
