package tn.esprit.spring.tpcafe_ons_kochtane.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ons kochtane Café ☕")
                        .version("1.0.0")
                        .description("""
                                📘 *Projet  Café – Gestion complète du système*
                                
                                👨‍💻 Développé par : *Onss kochtane*
                                
                                🔗 Liens utiles :
                                - 🌐 [Page Facebook](https://www.facebook.com/nousa.kochtane?mibextid=wwXIfr&rdid=aIQ7YN7tCdvGpdUd&share_url=https%3A%2F%2Fwww.facebook.com%2Fshare%2F19dGEPDQE3%2F%3Fmibextid%3DwwXIfr#)
                                - 💼 [Profil LinkedIn](https://www.linkedin.com)
                                
                                📧 Pour toute question : [onskoo6@gmail.com](mailto:onskoo6@gmail.com)
                                """)
                        .contact(new Contact()
                                .name("ons kochtane")
                                .email("onskoo6@gmail.com")
                                .url("https://www.linkedin.com/in/med-amin-chniti-08a3562a2")
                        )
                        .license(new License()
                                .name("Documentation & Démonstration du Projet")
                                .url("https://www.facebook.com/nousa.kochtane?mibextid=wwXIfr&rdid=aIQ7YN7tCdvGpdUd&share_url=https%3A%2F%2Fwww.facebook.com%2Fshare%2F19dGEPDQE3%2F%3Fmibextid%3DwwXIfr#")
                        )
                );
    }

    // ========================
    // 🔹 FILTRAGE PAR VOS CONTROLEURS
    // ========================

    // 📚 1. TOUTES LES APIs
    @Bean
    public GroupedOpenApi allApis() {
        return GroupedOpenApi.builder()
                .group("all-apis")
                .displayName("📚 Toutes les APIs")
                .pathsToMatch("/**")
                .build();
    }

    // 👥 2. GESTION DES CLIENTS
    @Bean
    public GroupedOpenApi clientApis() {
        return GroupedOpenApi.builder()
                .group("clients")
                .displayName("👥 Gestion des Clients")
                .pathsToMatch("/client/**")
                .build();
    }

    // 🏠 3. GESTION DES ADRESSES
    @Bean
    public GroupedOpenApi adressApis() {
        return GroupedOpenApi.builder()
                .group("adresses")
                .displayName("🏠 Gestion des Adresses")
                .pathsToMatch("/adresse/**")
                .build();
    }

    // ☕ 4. GESTION DES ARTICLES
    @Bean
    public GroupedOpenApi articleApis() {
        return GroupedOpenApi.builder()
                .group("articles")
                .displayName("☕ Gestion des Articles")
                .pathsToMatch("/article/**")
                .build();
    }

    // 💳 5. CARTES DE FIDÉLITÉ
    @Bean
    public GroupedOpenApi carteFideliteApis() {
        return GroupedOpenApi.builder()
                .group("cartes-fidelite")
                .displayName("💳 Cartes de Fidélité")
                .pathsToMatch("/carteF/**")
                .build();
    }

    // 📦 6. GESTION DES COMMANDES
    @Bean
    public GroupedOpenApi commandeApis() {
        return GroupedOpenApi.builder()
                .group("commandes")
                .displayName("📦 Gestion des Commandes")
                .pathsToMatch("/commande/**")
                .build();
    }

    // 📋 7. DÉTAILS DES COMMANDES
    @Bean
    public GroupedOpenApi detailCommandeApis() {
        return GroupedOpenApi.builder()
                .group("details-commande")
                .displayName("📋 Détails des Commandes")
                .pathsToMatch("/dc/**")
                .build();
    }

    // 🎯 8. PROMOTIONS
    @Bean
    public GroupedOpenApi promotionApis() {
        return GroupedOpenApi.builder()
                .group("promotions")
                .displayName("🎯 Gestion des Promotions")
                .pathsToMatch("/promotion/**")
                .build();
    }


}

