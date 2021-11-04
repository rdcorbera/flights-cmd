package com.skyxairlines.services.flightcmd;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfig {
  @Bean
  XStream xstream(){
    XStream xstream = new XStream();
    // clear out existing permissions and set own ones
    xstream.addPermission(NoTypePermission.NONE);
    // allow any type from the same package
    xstream.allowTypesByWildcard(new String[] {
        "com.skyxairlines.**",
        "org.axonframework.**",
        "java.**",
        "com.thoughtworks.xstream.**"
    });

    return xstream;
  }

  @Bean
  @Primary
  public Serializer serializer(XStream xStream) {
    return XStreamSerializer.builder().xStream(xStream).build();
  }
}
