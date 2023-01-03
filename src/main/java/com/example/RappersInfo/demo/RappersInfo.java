package com.example.RappersInfo.demo;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.JspConfigDescriptorImpl;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroup;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroupDescriptorImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.stereotype.Component;

import java.util.Collections;

@SpringBootApplication
public class RappersInfo {

	public static void main(String[] args) {

		SpringApplication.run(RappersInfo.class, args);

	}
	@Component
	public class JspContextCustomizer implements TomcatContextCustomizer {
		@Override
		public void customize(Context context) {
			JspPropertyGroup group = new JspPropertyGroup();
			group.addUrlPattern("/*");
			group.setPageEncoding("UTF-8");
			context.setJspConfigDescriptor(new JspConfigDescriptorImpl(
					Collections.singletonList(new JspPropertyGroupDescriptorImpl(group)),
					Collections.emptyList()));
		}
	}

}
