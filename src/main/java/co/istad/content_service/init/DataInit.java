package co.istad.content_service.init;

//import co.istad.content_service.domain.Article;

import co.istad.content_service.domain.CommunityEngagement;
import co.istad.content_service.domain.Content;
import co.istad.content_service.domain.Tags;
import co.istad.content_service.feature.content.ContentRepository;
//import co.istad.content_service.feature.article.articleRepository;
import co.istad.content_service.feature.tag.TagRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInit {

    //    private final articleRepository articleRepository;
    private final TagRepository tagRepository;

    private final ContentRepository contentRepository;

    @PostConstruct
    public void init() {
        log.info("Tags = {}", tagRepository.count());
        if (tagRepository.count() == 0) {
            Tags java = new Tags();
            java.setName("java");

            Tags spring = new Tags();
            spring.setName("spring-boot");

            Tags kotlin = new Tags();
            kotlin.setName("kotlin");

            Tags go = new Tags();
            go.setName("golang");

            Tags python = new Tags();
            python.setName("python");

            Tags javascript = new Tags();
            javascript.setName("javascript");

            Tags typescript = new Tags();
            typescript.setName("typescript");

            Tags html = new Tags();
            html.setName("html");

            Tags css = new Tags();
            css.setName("css");

            Tags bootstrap = new Tags();
            bootstrap.setName("bootstrap");

            Tags tailwind = new Tags();
            tailwind.setName("tailwind");

            Tags sql = new Tags();
            sql.setName("sql");

            Tags postgresql = new Tags();
            postgresql.setName("postgresql");

            Tags mysql = new Tags();
            mysql.setName("mysql");

            Tags mongodb = new Tags();
            mongodb.setName("mongodb");

            Tags redis = new Tags();
            redis.setName("redis");

            Tags elasticsearch = new Tags();
            elasticsearch.setName("elasticsearch");

            Tags kafka = new Tags();
            kafka.setName("kafka");

            Tags docker = new Tags();
            docker.setName("docker");

            Tags kubernetes = new Tags();
            kubernetes.setName("kubernetes");

            Tags jenkins = new Tags();
            jenkins.setName("jenkins");

            Tags aws = new Tags();
            aws.setName("aws");

            Tags azure = new Tags();
            azure.setName("azure");

            Tags gcp = new Tags();
            gcp.setName("gcp");

            Tags ciCd = new Tags();
            ciCd.setName("ci-cd");

            Tags git = new Tags();
            git.setName("git");

            Tags github = new Tags();
            github.setName("github");

            Tags gitlab = new Tags();
            gitlab.setName("gitlab");

            Tags bitbucket = new Tags();
            bitbucket.setName("bitbucket");

            Tags devops = new Tags();
            devops.setName("devops");

            Tags restApi = new Tags();
            restApi.setName("rest-api");

            Tags graphql = new Tags();
            graphql.setName("graphql");

            Tags microservices = new Tags();
            microservices.setName("microservices");

            Tags hibernate = new Tags();
            hibernate.setName("hibernate");

            Tags jpa = new Tags();
            jpa.setName("jpa");

            Tags springSecurity = new Tags();
            springSecurity.setName("spring-security");

            Tags jwt = new Tags();
            jwt.setName("jwt");

            Tags oauth2 = new Tags();
            oauth2.setName("oauth2");

            Tags unitTesting = new Tags();
            unitTesting.setName("unit-testing");

            Tags integrationTesting = new Tags();
            integrationTesting.setName("integration-testing");

            Tags jest = new Tags();
            jest.setName("jest");

            Tags mocha = new Tags();
            mocha.setName("mocha");

            Tags cypress = new Tags();
            cypress.setName("cypress");

            Tags selenium = new Tags();
            selenium.setName("selenium");

            Tags flutter = new Tags();
            flutter.setName("flutter");

            Tags dart = new Tags();
            dart.setName("dart");

            Tags rust = new Tags();
            rust.setName("rust");

            Tags scala = new Tags();
            scala.setName("scala");

            Tags c = new Tags();
            c.setName("c");

            Tags cpp = new Tags();
            cpp.setName("c++");

            Tags ruby = new Tags();
            ruby.setName("ruby");

            Tags php = new Tags();
            php.setName("php");

            Tags rubyOnRails = new Tags();
            rubyOnRails.setName("ruby-on-rails");

            Tags aspNet = new Tags();
            aspNet.setName("asp.net");

            Tags django = new Tags();
            django.setName("django");

            Tags flask = new Tags();
            flask.setName("flask");

            Tags fastApi = new Tags();
            fastApi.setName("fastapi");

            Tags machineLearning = new Tags();
            machineLearning.setName("machine-learning");

            Tags artificialIntelligence = new Tags();
            artificialIntelligence.setName("artificial-intelligence");

            Tags dataScience = new Tags();
            dataScience.setName("data-science");

            Tags pandas = new Tags();
            pandas.setName("pandas");

            Tags numpy = new Tags();
            numpy.setName("numpy");

            Tags scikitLearn = new Tags();
            scikitLearn.setName("scikit-learn");

            Tags tensorflow = new Tags();
            tensorflow.setName("tensorflow");

            Tags pytorch = new Tags();
            pytorch.setName("pytorch");

            Tags spark = new Tags();
            spark.setName("apache-spark");

            Tags hadoop = new Tags();
            hadoop.setName("hadoop");

            Tags bigData = new Tags();
            bigData.setName("big-data");

            Tags cassandra = new Tags();
            cassandra.setName("cassandra");

            Tags couchbase = new Tags();
            couchbase.setName("couchbase");

            Tags graphQLApi = new Tags();
            graphQLApi.setName("graphql-api");

            Tags expressJs = new Tags();
            expressJs.setName("expressjs");

            Tags nestJs = new Tags();
            nestJs.setName("nestjs");

            Tags sequelize = new Tags();
            sequelize.setName("sequelize");

            Tags prisma = new Tags();
            prisma.setName("prisma");

            Tags webpack = new Tags();
            webpack.setName("webpack");

            Tags rollup = new Tags();
            rollup.setName("rollup");

            Tags vite = new Tags();
            vite.setName("vite");

            Tags babel = new Tags();
            babel.setName("babel");

            Tags eslint = new Tags();
            eslint.setName("eslint");

            Tags prettier = new Tags();
            prettier.setName("prettier");

            Tags tailwindUi = new Tags();
            tailwindUi.setName("tailwind-ui");

            Tags materialUi = new Tags();
            materialUi.setName("material-ui");

            Tags antDesign = new Tags();
            antDesign.setName("ant-design");

            Tags chakraUi = new Tags();
            chakraUi.setName("chakra-ui");

            Tags vuetify = new Tags();
            vuetify.setName("vuetify");

            Tags svelte = new Tags();
            svelte.setName("svelte");

            Tags d3js = new Tags();
            d3js.setName("d3.js");

            Tags chartJs = new Tags();
            chartJs.setName("chart.js");

            Tags threeJs = new Tags();
            threeJs.setName("three.js");

            Tags nextAuth = new Tags();
            nextAuth.setName("next-auth");

            Tags firebase = new Tags();
            firebase.setName("firebase");

            Tags supabase = new Tags();
            supabase.setName("supabase");

            Tags sanity = new Tags();
            sanity.setName("sanity");

            Tags strapi = new Tags();
            strapi.setName("strapi");

            Tags swagger = new Tags();
            swagger.setName("swagger");

            Tags openApi = new Tags();
            openApi.setName("openapi");

            Tags jsonWebToken = new Tags();
            jsonWebToken.setName("json-web-token");

            Tags keycloak = new Tags();
            keycloak.setName("keycloak");

            Tags sonarQube = new Tags();
            sonarQube.setName("sonarqube");

            Tags logstash = new Tags();
            logstash.setName("logstash");

            Tags prometheus = new Tags();
            prometheus.setName("prometheus");

            Tags grafana = new Tags();
            grafana.setName("grafana");

            Tags newRelic = new Tags();
            newRelic.setName("new-relic");

            Tags datadog = new Tags();
            datadog.setName("datadog");

            // Save all tags
            tagRepository.saveAll(List.of(
                    java, spring, kotlin, go, python, javascript, typescript, html, css, bootstrap,
                    tailwind, sql, postgresql, mysql, mongodb, redis, elasticsearch, kafka, docker,
                    kubernetes, jenkins, aws, azure, gcp, ciCd, git, github, gitlab, bitbucket,
                    devops, restApi, graphql, microservices, hibernate, jpa, springSecurity, jwt,
                    oauth2, unitTesting, integrationTesting, jest, mocha, cypress, selenium,
                    flutter, dart, rust, scala, c, cpp, ruby, php, rubyOnRails, aspNet, django, flask, fastApi, machineLearning, artificialIntelligence,
                    dataScience, pandas, numpy, scikitLearn, tensorflow, pytorch, spark, hadoop, bigData,
                    cassandra, couchbase, graphQLApi, expressJs, nestJs, sequelize, prisma, webpack, rollup,
                    vite, babel, eslint, prettier, tailwindUi, materialUi, antDesign, chakraUi, vuetify,
                    svelte, d3js, chartJs, threeJs, nextAuth, firebase, supabase, sanity, strapi, swagger,
                    openApi, jsonWebToken, keycloak, sonarQube, logstash, prometheus, grafana, newRelic, datadog
            ));
        }

        if (contentRepository.count() == 0) {
            CommunityEngagement communityEngagement = new CommunityEngagement();
            communityEngagement.setLikeCount(0L);
            communityEngagement.setCommentCount(0L);
            communityEngagement.setReportCount(0L);
            communityEngagement.setShareCount(0L);
            communityEngagement.setLastUpdated(LocalDateTime.now());

            Content Content = new Content();
            Content.setTitle("Java 8 Features");
            Content.setContent("Java 8 Features");
            Content.setThumbnail("https://www.javatpoint.com/images/java-8.png");
            Content.setSlug("java-8-features");
            Content.setTags(List.of("java", "spring-boot", "kotlin"));
            Content.setCommunityEngagement(communityEngagement);
            Content.setIsDeleted(false);
            Content.setIsDraft(false);
//            Content.setAuthorId(1L);
//            Content.setCreatedDate(LocalDateTime.now());
            contentRepository.save(Content);
        }
    }


}
