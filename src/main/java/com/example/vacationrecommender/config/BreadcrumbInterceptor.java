package com.example.vacationrecommender.config;

import com.example.vacationrecommender.dto.Breadcrumb;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.List;

@Component
public class BreadcrumbInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String uri = request.getRequestURI();

        List<Breadcrumb> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new Breadcrumb("Acasă", "/choice")); // pagina de bază

        if (uri.equals("/choice")) {
            request.setAttribute("breadcrumbs", null); // ascundem breadcrumb-ul pe pagina principală
            return true;
        }

        // Reguli simple: adaugă breadcrumb în funcție de URL
        if (uri.startsWith("/vacante")) {
            breadcrumbs.add(new Breadcrumb("Vacanțe", uri));
        } else if (uri.startsWith("/ai")) {
            breadcrumbs.add(new Breadcrumb("Recomandare AI", uri));
        } else if (uri.startsWith("/glob")) {
            breadcrumbs.add(new Breadcrumb("Hartă", uri));
        }

        request.setAttribute("breadcrumbs", breadcrumbs);
        return true;
    }
}
