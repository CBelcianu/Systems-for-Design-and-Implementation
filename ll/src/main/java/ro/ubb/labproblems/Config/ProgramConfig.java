package ro.ubb.labproblems.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"ro.ubb.labproblems.Repository", "ro.ubb.labproblems.Service", "ro.ubb.labproblems.UI"})
public class ProgramConfig {
}
