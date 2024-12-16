package com.prototipo;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration

public class ProjectConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locate");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

//    @Bean
//    public LocaleChangeInterceptor monedaChangeInterceptor() {
//        var lci = new LocaleChangeInterceptor();
//        lci.setParamName("moneda"); 
//        return lci;
//    }
    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
//        registro.addInterceptor(monedaChangeInterceptor());
    }

    @Override
    //Ciertas paginas se pueda ingresar de manera directa
    public void addViewControllers(ViewControllerRegistry registro) {
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/index").setViewName("index");
        registro.addViewController("/login").setViewName("/login");
        registro.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        //A todo el mundo le permitimos esas rutas
        http.authorizeHttpRequests((request) -> request
                .requestMatchers("/", "/index", "/js/**", "/webjars/**", "/registro/**",
                        "/css/**", "/img/**","/encuentranos/listado","/pruebas/detalles/**",
                        "/carrito/**","/pruebas/listado/**","/promociones/listado","/envios/listado")
                .permitAll()
                .requestMatchers("/categoria/listado", "/vehiculo/listado","/accesorio/listado")
                .hasRole("VENDEDOR")
                .requestMatchers("/categoria/nuevo", "/categoria/modificar/**", "/categoria/eliminar/**", "/categoria/guardar",
                        "/vehiculo/nuevo", "/vehiculo/modificar/**", "/vehiculo/eliminar/**", "/vehiculo/guardar","/vehiculo/editar", "/pruebas/listado/**",
                        "/marketing/**","/informe/**",
                        "/accesorio/nuevo", "/accesorio/modificar/**", "/accesorio/eliminar/**", "/accesorio/guardar")
                .hasRole("ADMIN")
                .requestMatchers("/facturar/carrito","/perfil/**")
                .hasRole("USER")
        )
                .formLogin((form) -> form.loginPage("/login").permitAll().defaultSuccessUrl("/", true))
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder)
            throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder()); //Metodo para encriptar las contraseñas
    }
}
