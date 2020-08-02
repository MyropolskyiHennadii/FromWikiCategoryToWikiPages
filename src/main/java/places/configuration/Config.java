package places.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import places.wiki.ManageWikiCategoriesAndPages;

@EnableWebMvc
@Configuration
public class Config implements WebMvcConfigurer {

    // == constants ==
    public static final String RESOLVER_PREFIX = "/WEB-INF/view/";
    public static final String RESOLVER_SUFFIX = ".jsp";

    // == bean methods
    @Bean
    public ViewResolver viewResolver() {
        UrlBasedViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(RESOLVER_PREFIX);
        viewResolver.setSuffix(RESOLVER_SUFFIX);
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    @Bean
    public ConstantsParsingWiki getConstants() {
        return new ConstantsParsingWiki();
    }

    @Bean
    public ManageWikiCategoriesAndPages getManageLanguagesWiki() {
        return new ManageWikiCategoriesAndPages();
    }

}
