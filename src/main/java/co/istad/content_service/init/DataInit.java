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
            java.setDescription("Java គឺជាភាសាកម្មវិធីកូដប្រភពខ្ពស់ដែលបង្កើតឡើងដើម្បីអនុញ្ញាតឱ្យអ្នកអភិវឌ្ឍសរសេរកម្មវិធីដែលអាចដំណើរការបាននៅលើបរិស្ថានផ្សេងៗគ្នា (cross-platform)។ វាបានបង្កើតឡើងនៅឆ្នាំ 1995 ដោយក្រុមហ៊ុន Sun Microsystems (ពេលនេះជាកម្មសិទ្ធិរបស់ក្រុមហ៊ុន Oracle)។");

            Tags spring = new Tags();
            spring.setName("spring-boot");
            spring.setDescription("Spring Boot គឺជាផ្នែកមួយនៃ Spring Framework ដែលងាយស្រួលប្រើ និងមានគោលបំណងបំផុសសមត្ថភាពអភិវឌ្ឍកម្មវិធី Java ជា Microservices ឬកម្មវិធីដែលមានរចនាសម្ព័ន្ធធំៗ។ វាបង្កើតឡើងដើម្បីកាត់បន្ថយការកំណត់រចនាសម្ព័ន្ធ (Configuration) និងបង្កើតកម្មវិធី Java ដែលអាចដំណើរការបានឆាប់រហ័ស។");

            Tags kotlin = new Tags();
            kotlin.setName("kotlin");
            kotlin.setDescription("Kotlin គឺជា​ភាសាកូដប្រភព​ខ្ពស់ ដែលបង្កើតឡើងដោយ JetBrains ក្នុងឆ្នាំ 2011 និងសរសេរដោយប្រើ JVM (Java Virtual Machine)។ វាមានលក្ខណៈស្របទៅនឹង Java ហើយមានគោលបំណងផ្តល់ភាពងាយស្រួល និងសុវត្ថិភាពជាង Java។ Kotlin ត្រូវបានប្រើសម្រាប់ការអភិវឌ្ឍកម្មវិធី Android, Web, និង Backend។");

            Tags go = new Tags();
            go.setName("golang");
            go.setDescription("Golang ឬ Go គឺជា​ភាសាកូដដែលបង្កើតឡើងដោយ Google ក្នុងឆ្នាំ 2009 ដើម្បីផ្តល់នូវប្រសិទ្ធភាពខ្ពស់, ការគាំទ្រទម្រង់ Concurrency, និងភាពងាយស្រួលក្នុងការប្រើប្រាស់។ វាត្រូវបានប្រើសម្រាប់ការអភិវឌ្ឍប្រព័ន្ធ Microservices, Network Tools, និង Cloud Applications។");

            Tags python = new Tags();
            python.setName("python");
            python.setDescription("Python គឺជា​ភាសាកូដប្រភព​ខ្ពស់ដែលងាយស្រួលសិក្សា និងប្រើប្រាស់។ វាត្រូវបានប្រើសម្រាប់កម្មវិធី Web, Data Science, Machine Learning, និងការបង្កើត Script ជាច្រើន។");

            Tags javascript = new Tags();
            javascript.setName("javascript");
            javascript.setDescription("JavaScript គឺជា​ភាសាកូដ client-side ដែលត្រូវបានប្រើសម្រាប់បន្ថែមលក្ខណៈ interactive ក្នុង Web Applications និងមានសារៈសំខាន់ក្នុងការអភិវឌ្ឍ Frontend និង Backend។");

            Tags typescript = new Tags();
            typescript.setName("typescript");
            typescript.setDescription("TypeScript គឺជា​ភាសាបន្ថែមនៅលើ JavaScript ដែលផ្តល់នូវ Static Typing និងឧបករណ៍ច្រើនសម្រាប់ការអភិវឌ្ឍកម្មវិធីធំៗដែលមានស្ថេរភាព។");

            Tags html = new Tags();
            html.setName("html");
            html.setDescription("HTML (HyperText Markup Language) គឺជាភាសាមូលដ្ឋានសម្រាប់បង្កើតគេហទំព័រ។ វាត្រូវបានប្រើដើម្បីបែងចែក និងរៀបចំព័ត៌មាននៅលើ Web។");

            Tags css = new Tags();
            css.setName("css");
            css.setDescription("CSS (Cascading Style Sheets) គឺជា​ភាសាដើម្បីរចនារូបរាងនៃគេហទំព័រ ដូចជា ពណ៌, ការរៀបចំ Layout, និងការរចនាទូទៅ។");

            Tags bootstrap = new Tags();
            bootstrap.setName("bootstrap");
            bootstrap.setDescription("Bootstrap គឺជា Framework រចនាមុខមាត់ដែលផ្តល់ Component និង Utility ច្រើនសម្រាប់បង្កើតគេហទំព័រដែលមានភាពឆបគ្នានឹងឧបករណ៍ផ្សេងៗ។");

            Tags tailwind = new Tags();
            tailwind.setName("tailwind");
            tailwind.setDescription("Tailwind CSS គឺជា Utility-First CSS Framework ដែលផ្តល់ Class ច្រើនសម្រាប់បង្កើតការរចនាប្លង់ដែលអាចប្តូរបានយ៉ាងឆាប់រហ័ស។");

            Tags sql = new Tags();
            sql.setName("sql");
            sql.setDescription("SQL (Structured Query Language) គឺជា​ភាសាសម្រាប់គ្រប់គ្រងទិន្នន័យនៅក្នុង Database ដែលអាចប្រើសម្រាប់ការស្វែងរក, បន្ថែម, និងកែប្រែទិន្នន័យ។");


            Tags postgresql = new Tags();
            postgresql.setName("postgresql");
            postgresql.setDescription("PostgreSQL គឺជា Database Management System ប្រភពបើកចំហ ដែលមានប្រសិទ្ធភាពខ្ពស់ និងគាំទ្រទិន្នន័យប្រភេទទំនាក់ទំនង (Relational) និងប្រភេទ JSON, XML, និងប្រព័ន្ធ Spatial។");

            Tags mysql = new Tags();
            mysql.setName("mysql");
            mysql.setDescription("MySQL គឺជា Relational Database Management System (RDBMS) ដែលប្រើសម្រាប់គ្រប់គ្រងទិន្នន័យប្រភេទតារាងជាមួយភាពឆាប់រហ័ស និងស្ថេរភាព។");

            Tags mongodb = new Tags();
            mongodb.setName("mongodb");
            mongodb.setDescription("MongoDB គឺជា Database ប្រភេទ NoSQL ដែលសន្សំទិន្នន័យជាទម្រង់ BSON (Binary JSON) ហើយមានសមត្ថភាពខ្ពស់សម្រាប់ប្រព័ន្ធដែលទាមទារការចងគ្នារហ័ស។");

            Tags redis = new Tags();
            redis.setName("redis");
            redis.setDescription("Redis គឺជា Database ប្រភេទ Key-Value Store ដែលមានប្រសិទ្ធភាពខ្ពស់ក្នុងការចងគ្នា និងប្រើសម្រាប់ការផ្ទុកឯកសារ Cache, Session Management, និង Pub/Sub Messaging។");

            Tags elasticsearch = new Tags();
            elasticsearch.setName("elasticsearch");
            elasticsearch.setDescription("Elasticsearch គឺជា Search Engine ដែលមានប្រសិទ្ធភាពខ្ពស់សម្រាប់ការស្វែងរក និងវិភាគទិន្នន័យធំៗក្នុងរយៈពេលខ្លី។");

            Tags kafka = new Tags();
            kafka.setName("kafka");
            kafka.setDescription("Kafka គឺជា Distributed Streaming Platform ដែលប្រើសម្រាប់ Messaging, Logging, និងការផ្ទុក Stream Data ជាស្ថិតិខ្ពស់។");

            Tags docker = new Tags();
            docker.setName("docker");
            docker.setDescription("Docker គឺជា Platform សម្រាប់បង្កើត និងដំណើរការ Container ដែលជួយអភិវឌ្ឍកម្មវិធីក្នុងបរិស្ថានដែលឯករាជ្យពីប្រព័ន្ធប្រតិបត្តិការ។");

            Tags kubernetes = new Tags();
            kubernetes.setName("kubernetes");
            kubernetes.setDescription("Kubernetes គឺជា Open-Source Container Orchestration System ដែលគ្រប់គ្រងការដំណើរការ និងការចែកចាយ Containers នៅលើបណ្ដាញ Server។");

            Tags jenkins = new Tags();
            jenkins.setName("jenkins");
            jenkins.setDescription("Jenkins គឺជា Open-Source Automation Server ដែលគាំទ្រ Continuous Integration និង Continuous Deployment (CI/CD) សម្រាប់ការអភិវឌ្ឍកម្មវិធី។");

            Tags aws = new Tags();
            aws.setName("aws");
            aws.setDescription("AWS (Amazon Web Services) គឺជា Cloud Computing Platform ដែលផ្តល់សេវាកម្មសម្រាប់ការរក្សាទិន្នន័យ, គណនា, និងបង្កើតបណ្តាញជាសកល។");


            Tags azure = new Tags();
            azure.setName("azure");
            azure.setDescription("Azure គឺជា Cloud Computing Platform របស់ Microsoft ដែលផ្តល់សេវាកម្មសម្រាប់ការរក្សាទិន្នន័យ, គណនា, និងបង្កើតកម្មវិធីជាសកល។");

            Tags gcp = new Tags();
            gcp.setName("gcp");
            gcp.setDescription("GCP (Google Cloud Platform) គឺជា Cloud Computing Platform របស់ Google ដែលផ្តល់សេវាកម្មសម្រាប់ការចងគ្នា, ស្តុកទិន្នន័យ, និងវិភាគទិន្នន័យ។");

            Tags ciCd = new Tags();
            ciCd.setName("ci-cd");
            ciCd.setDescription("CI/CD (Continuous Integration/Continuous Deployment) គឺជា​លទ្ធកម្មសម្រាប់សាកល្បង, ត្រួតពិនិត្យ, និងដាក់បញ្ចូលកម្មវិធីទៅក្នុងបរិស្ថានផលិតកម្មដោយស្វ័យប្រវត្តិ។");

            Tags git = new Tags();
            git.setName("git");
            git.setDescription("Git គឺជា Version Control System ដែលផ្តល់សេវាកម្មសម្រាប់គ្រប់គ្រងការផ្លាស់ប្តូររបស់ Source Code ដោយមានភាពងាយស្រួល និងប្រសិទ្ធភាព។");

            Tags github = new Tags();
            github.setName("github");
            github.setDescription("GitHub គឺជា Platform សម្រាប់រដ្ឋបាល Source Code និងគាំទ្រ Git, សម្រាប់ការចែករំលែក និងគ្រប់គ្រងគម្រោងកម្មវិធី។");

            Tags gitlab = new Tags();
            gitlab.setName("gitlab");
            gitlab.setDescription("GitLab គឺជា DevOps Platform ដែលផ្តល់នូវសេវាកម្មសម្រាប់ Version Control, CI/CD, និងការគ្រប់គ្រងគម្រោងសម្រាប់ក្រុមអភិវឌ្ឍកម្មវិធី។");

            Tags bitbucket = new Tags();
            bitbucket.setName("bitbucket");
            bitbucket.setDescription("Bitbucket គឺជា Platform សម្រាប់ការគ្រប់គ្រង Source Code និងសេវាកម្មសម្រាប់ Version Control ដោយមានការគាំទ្រ Git និង Mercurial។");

            Tags devops = new Tags();
            devops.setName("devops");
            devops.setDescription("DevOps គឺជា​វិធានសម្រាប់រួមបញ្ចូលក្រុមអភិវឌ្ឍកម្មវិធី និងប្រតិបត្តិការ ដើម្បីធានាភាពរលូនក្នុងការដាក់កម្មវិធី។");

            Tags restApi = new Tags();
            restApi.setName("rest-api");
            restApi.setDescription("REST API គឺជា Web Service Design Style ដែលអាចផ្ដល់នូវការតភ្ជាប់ចូលទៅក្នុងប្រព័ន្ធតាមប្រព័ន្ធ HTTP ជា Stateless។");

            Tags graphql = new Tags();
            graphql.setName("graphql");
            graphql.setDescription("GraphQL គឺជា Query Language សម្រាប់ APIs ដែលអាចដាក់សំណើតាមប្រព័ន្ធ Client ដើម្បីទទួលបានទិន្នន័យដែលច្បាស់លាស់។");

            Tags microservices = new Tags();
            microservices.setName("microservices");
            microservices.setDescription("Microservices គឺជា Architectural Style សម្រាប់ការបែងចែកកម្មវិធីធំៗជាប្រព័ន្ធតូចៗដែលអាចដំណើរការដោយឯករាជ្យ។");

            Tags hibernate = new Tags();
            hibernate.setName("hibernate");
            hibernate.setDescription("Hibernate គឺជា ORM Framework សម្រាប់ Java ដែលជួយបម្លែងទិន្នន័យតារាងក្នុង Database ជា Object ដើម្បីធ្វើការប្រើប្រាស់ជាមួយ Java។");

            Tags jpa = new Tags();
            jpa.setName("jpa");
            jpa.setDescription("JPA (Java Persistence API) គឺជា Specification សម្រាប់ការគ្រប់គ្រង Persistence Data ក្នុងកម្មវិធី Java។");

            Tags springSecurity = new Tags();
            springSecurity.setName("spring-security");
            springSecurity.setDescription("Spring Security គឺជា Framework សម្រាប់ការគ្រប់គ្រង Authentication និង Authorization ក្នុងកម្មវិធី Java Spring។");

            Tags jwt = new Tags();
            jwt.setName("jwt");
            jwt.setDescription("JWT (JSON Web Token) គឺជា Standard ដែលប្រើសម្រាប់ដាក់ Authentication និង Exchange Information ដោយសុវត្ថិភាព។");

            Tags oauth2 = new Tags();
            oauth2.setName("oauth2");
            oauth2.setDescription("OAuth2 គឺជា Framework សម្រាប់ការអនុញ្ញាត Access Token ដើម្បីប្រើប្រាស់ធនធានដោយសុវត្ថិភាព។");

            Tags unitTesting = new Tags();
            unitTesting.setName("unit-testing");
            unitTesting.setDescription("Unit Testing គឺជា​លទ្ធកម្មសម្រាប់សាកល្បង Function ឬ Method តូចៗក្នុងកម្មវិធីដើម្បីធានាប្រសិទ្ធភាព។");

            Tags integrationTesting = new Tags();
            integrationTesting.setName("integration-testing");
            integrationTesting.setDescription("Integration Testing គឺជា​លទ្ធកម្មសម្រាប់សាកល្បងការប្រើប្រាស់រវាង Module ឬប្រព័ន្ធផ្សេងៗនៅក្នុងកម្មវិធី។");

            Tags jest = new Tags();
            jest.setName("jest");
            jest.setDescription("Jest គឺជា JavaScript Testing Framework សម្រាប់ការសាកល្បង Unit និង Integration ដែលមានប្រសិទ្ធភាពខ្ពស់។");


            Tags mocha = new Tags();
            mocha.setName("mocha");
            mocha.setDescription("Mocha គឺជា JavaScript Testing Framework ដែលផ្តោតលើការសាកល្បង Unit និង Integration សម្រាប់ Node.js។");

            Tags cypress = new Tags();
            cypress.setName("cypress");
            cypress.setDescription("Cypress គឺជា JavaScript Testing Tool សម្រាប់សាកល្បង End-to-End ក្នុងកម្មវិធី Web។");

            Tags selenium = new Tags();
            selenium.setName("selenium");
            selenium.setDescription("Selenium គឺជា Framework សម្រាប់ការសាកល្បងកម្មវិធី Web ដោយស្វ័យប្រវត្តិដោយប្រើ Browser។");

            Tags flutter = new Tags();
            flutter.setName("flutter");
            flutter.setDescription("Flutter គឺជា UI Toolkit របស់ Google សម្រាប់ការអភិវឌ្ឍកម្មវិធី Mobile, Web, និង Desktop ដោយប្រើ Codebase តែមួយ។");

            Tags dart = new Tags();
            dart.setName("dart");
            dart.setDescription("Dart គឺជា Programming Language របស់ Google សម្រាប់ការអភិវឌ្ឍកម្មវិធីដែលមានប្រសិទ្ធភាពខ្ពស់។");

            Tags rust = new Tags();
            rust.setName("rust");
            rust.setDescription("Rust គឺជា Programming Language ដែលមានសុវត្ថិភាពខ្ពស់ និងសមត្ថភាពលឿនសម្រាប់ការអភិវឌ្ឍកម្មវិធីដែលត្រូវការសមត្ថភាពខ្ពស់។");

            Tags scala = new Tags();
            scala.setName("scala");
            scala.setDescription("Scala គឺជា Programming Language សម្រាប់ JVM ដែលបង្កើតសម្រាប់ការអភិវឌ្ឍកម្មវិធីដែលមានប្រសិទ្ធភាព។");

            Tags c = new Tags();
            c.setName("c");
            c.setDescription("C គឺជា Programming Language ដែលមានប្រសិទ្ធភាពសម្រាប់ការអភិវឌ្ឍកម្មវិធីទាប។");

            Tags cpp = new Tags();
            cpp.setName("c++");
            cpp.setDescription("C++ គឺជា Programming Language ដែលមានប្រសិទ្ធភាពសម្រាប់ការអភិវឌ្ឍកម្មវិធីដែលមានប្រព័ន្ធស្មុគស្មាញ។");

            Tags ruby = new Tags();
            ruby.setName("ruby");
            ruby.setDescription("Ruby គឺជា Programming Language សម្រាប់ការអភិវឌ្ឍកម្មវិធីដែលមានប្រសិទ្ធភាព និងងាយស្រួល។");

            Tags php = new Tags();
            php.setName("php");
            php.setDescription("PHP គឺជា Server-Side Programming Language សម្រាប់ការអភិវឌ្ឍ Web Applications។");

            Tags rubyOnRails = new Tags();
            rubyOnRails.setName("ruby-on-rails");
            rubyOnRails.setDescription("Ruby on Rails គឺជា Framework សម្រាប់ការអភិវឌ្ឍ Web Applications ដោយប្រើ Ruby។");

            Tags aspNet = new Tags();
            aspNet.setName("asp.net");
            aspNet.setDescription("ASP.NET គឺជា Framework របស់ Microsoft សម្រាប់ការអភិវឌ្ឍ Web Applications។");

            Tags django = new Tags();
            django.setName("django");
            django.setDescription("Django គឺជា Python Framework សម្រាប់ការអភិវឌ្ឍ Web Applications ដែលមានភាពលឿន និងសុវត្ថិភាព។");

            Tags flask = new Tags();
            flask.setName("flask");
            flask.setDescription("Flask គឺជា Python Framework សាមញ្ញសម្រាប់ការអភិវឌ្ឍ Web Applications។");

            Tags fastApi = new Tags();
            fastApi.setName("fastapi");
            fastApi.setDescription("FastAPI គឺជា Python Framework សម្រាប់ការអភិវឌ្ឍ APIs ដែលមានល្បឿនខ្ពស់។");

            Tags machineLearning = new Tags();
            machineLearning.setName("machine-learning");
            machineLearning.setDescription("Machine Learning គឺជា Branch របស់ AI ដែលផ្តោតលើការប្រើទិន្នន័យដើម្បីបណ្តុះម៉ូដែល។");

            Tags artificialIntelligence = new Tags();
            artificialIntelligence.setName("artificial-intelligence");
            artificialIntelligence.setDescription("Artificial Intelligence គឺជាបច្ចេកវិទ្យាសម្រាប់ការបង្កើតប្រព័ន្ធដែលមានសមត្ថភាពដូចមនុស្ស។");

            Tags dataScience = new Tags();
            dataScience.setName("data-science");
            dataScience.setDescription("Data Science គឺជា Science សម្រាប់ការវិភាគ និងប្រើប្រាស់ទិន្នន័យដើម្បីទទួលបានការយល់ដឹងថ្មី។");

            Tags pandas = new Tags();
            pandas.setName("pandas");
            pandas.setDescription("Pandas គឺជា Python Library សម្រាប់ការកែសម្រួល និងវិភាគទិន្នន័យ។");

            Tags numpy = new Tags();
            numpy.setName("numpy");
            numpy.setDescription("NumPy គឺជា Python Library សម្រាប់ការគណនាមេត្រិច និងការគណនាគណិតវិទ្យា។");

            Tags scikitLearn = new Tags();
            scikitLearn.setName("scikit-learn");
            scikitLearn.setDescription("Scikit-learn គឺជា Python Library សម្រាប់ការអនុវត្ត Machine Learning។");

            Tags tensorflow = new Tags();
            tensorflow.setName("tensorflow");
            tensorflow.setDescription("TensorFlow គឺជា Framework សម្រាប់ការបង្កើត និងបណ្តុះ Machine Learning Models។");

            Tags pytorch = new Tags();
            pytorch.setName("pytorch");
            pytorch.setDescription("PyTorch គឺជា Framework សម្រាប់ការបង្កើត Deep Learning Models ដោយប្រើ Python។");

            Tags spark = new Tags();
            spark.setName("apache-spark");
            spark.setDescription("Apache Spark គឺជា Framework សម្រាប់ការគ្រប់គ្រង Big Data និងប្រតិបត្តិការព័ត៌មាន។");

            Tags hadoop = new Tags();
            hadoop.setName("hadoop");
            hadoop.setDescription("Hadoop គឺជា Framework សម្រាប់ការរក្សាទិន្នន័យ និងការគ្រប់គ្រង Big Data។");


            Tags bigData = new Tags();
            bigData.setName("big-data");
            bigData.setDescription("Big Data គឺជាការគ្រប់គ្រង និងវិភាគទិន្នន័យដ៏មានបរិមាណធំដែលមិនអាចដំណើរការបានដោយប្រព័ន្ធទិន្នន័យធម្មតា។");

            Tags cassandra = new Tags();
            cassandra.setName("cassandra");
            cassandra.setDescription("Apache Cassandra គឺជា Distributed Database សម្រាប់ទិន្នន័យដែលមានការលូតលាស់លឿន និងតម្រូវការក្នុងល្បឿនខ្ពស់។");

            Tags couchbase = new Tags();
            couchbase.setName("couchbase");
            couchbase.setDescription("Couchbase គឺជា NoSQL Database សម្រាប់ការគ្រប់គ្រងទិន្នន័យដែលមានប្រសិទ្ធភាព និងសមត្ថភាពខ្ពស់។");

            Tags graphQLApi = new Tags();
            graphQLApi.setName("graphql-api");
            graphQLApi.setDescription("GraphQL API គឺជាបច្ចេកទេសសម្រាប់ Query និង Manipulate Data ដោយប្រើ Language ដែលមាន Flexible Schema។");

            Tags expressJs = new Tags();
            expressJs.setName("expressjs");
            expressJs.setDescription("Express.js គឺជា Node.js Framework សម្រាប់ការអភិវឌ្ឍ Backend Applications។");

            Tags nestJs = new Tags();
            nestJs.setName("nestjs");
            nestJs.setDescription("NestJS គឺជា Node.js Framework ដែលផ្តោតលើការអភិវឌ្ឍ Backend Applications ដែលមានរចនាសម្ព័ន្ធទាន់សម័យ។");

            Tags sequelize = new Tags();
            sequelize.setName("sequelize");
            sequelize.setDescription("Sequelize គឺជា ORM (Object-Relational Mapping) សម្រាប់ Node.js ដែលគាំទ្រ Databases ដូចជា MySQL, PostgreSQL។");

            Tags prisma = new Tags();
            prisma.setName("prisma");
            prisma.setDescription("Prisma គឺជា Next-Generation ORM សម្រាប់ការគ្រប់គ្រង Database សម្រាប់ JavaScript និង TypeScript។");

            Tags webpack = new Tags();
            webpack.setName("webpack");
            webpack.setDescription("Webpack គឺជា Module Bundler សម្រាប់ការចងវណ្ណកម្មវិធី Web Applications។");

            Tags rollup = new Tags();
            rollup.setName("rollup");
            rollup.setDescription("Rollup គឺជា Module Bundler សម្រាប់ JavaScript ដែលផ្តោតលើការបង្កើត Libraries និង Applications។");

            Tags vite = new Tags();
            vite.setName("vite");
            vite.setDescription("Vite គឺជា Build Tool សម្រាប់ Frontend Applications ដោយផ្តោតលើល្បឿន និងប្រសិទ្ធភាព។");

            Tags babel = new Tags();
            babel.setName("babel");
            babel.setDescription("Babel គឺជា JavaScript Compiler សម្រាប់ការបំលែង Code ទៅ Compatibility ខ្ពស់ក្នុង Browser។");

            Tags eslint = new Tags();
            eslint.setName("eslint");
            eslint.setDescription("ESLint គឺជា Tool សម្រាប់ពិនិត្យ និងបង្វែរ Code JavaScript និង TypeScript។");

            Tags prettier = new Tags();
            prettier.setName("prettier");
            prettier.setDescription("Prettier គឺជា Code Formatter សម្រាប់ការបង្វែររចនាបែបបទ Code ឱ្យមានភាពស្អាត និងមានលក្ខណៈស្របតាមក្បួន។");

            Tags tailwindUi = new Tags();
            tailwindUi.setName("tailwind-ui");
            tailwindUi.setDescription("Tailwind UI គឺជា Component Library សម្រាប់ Tailwind CSS ដែលផ្តល់ Components ស្រាប់។");

            Tags materialUi = new Tags();
            materialUi.setName("material-ui");
            materialUi.setDescription("Material-UI គឺជា React UI Framework ដែលផ្តោតលើរចនាបែប Material Design។");

            Tags antDesign = new Tags();
            antDesign.setName("ant-design");
            antDesign.setDescription("Ant Design គឺជា UI Framework សម្រាប់ React ដែលផ្តោតលើការរចនាទាន់សម័យ។");

            Tags chakraUi = new Tags();
            chakraUi.setName("chakra-ui");
            chakraUi.setDescription("Chakra UI គឺជា React UI Framework សម្រាប់ការរចនាដែលមាន Flexible និង Accessible។");

            Tags vuetify = new Tags();
            vuetify.setName("vuetify");
            vuetify.setDescription("Vuetify គឺជា UI Library សម្រាប់ Vue.js ដែលផ្តោតលើ Material Design។");

            Tags svelte = new Tags();
            svelte.setName("svelte");
            svelte.setDescription("Svelte គឺជា JavaScript Framework សម្រាប់ការរចនាបណ្ណកម្មវិធី Web Applications ដោយមិនចាំបាច់ Runtime។");

            Tags d3js = new Tags();
            d3js.setName("d3.js");
            d3js.setDescription("D3.js គឺជា JavaScript Library សម្រាប់ការបង្កើត Data Visualizations។");

            Tags chartJs = new Tags();
            chartJs.setName("chart.js");
            chartJs.setDescription("Chart.js គឺជា JavaScript Library សម្រាប់ការបង្កើត Charts និង Graphs។");

            Tags threeJs = new Tags();
            threeJs.setName("three.js");
            threeJs.setDescription("Three.js គឺជា JavaScript Library សម្រាប់ការបង្កើត 3D Visualizations នៅលើ Web។");

            Tags nextAuth = new Tags();
            nextAuth.setName("next-auth");
            nextAuth.setDescription("NextAuth គឺជា Authentication Library សម្រាប់ Next.js ដែលគាំទ្រ Providers ច្រើន។");

            Tags firebase = new Tags();
            firebase.setName("firebase");
            firebase.setDescription("Firebase គឺជា Backend-as-a-Service (BaaS) Platform ដែលផ្តល់សេវាកម្មទិន្នន័យ និង Authentication។");

            Tags supabase = new Tags();
            supabase.setName("supabase");
            supabase.setDescription("Supabase គឺជា Open Source Backend Platform សម្រាប់ការអភិវឌ្ឍកម្មវិធី Web និង Mobile។");


            Tags sanity = new Tags();
            sanity.setName("sanity");
            sanity.setDescription("Sanity គឺជា Content Platform សម្រាប់ការគ្រប់គ្រង និងប្រើប្រាស់ Content សម្រាប់ Web Applications។");

            Tags strapi = new Tags();
            strapi.setName("strapi");
            strapi.setDescription("Strapi គឺជា Headless CMS ដែលមានប្រសិទ្ធភាពសម្រាប់ការអភិវឌ្ឍ Backend API និង Content Management។");

            Tags swagger = new Tags();
            swagger.setName("swagger");
            swagger.setDescription("Swagger គឺជា Tool សម្រាប់ការបង្កើត និងឯកសារ API Specification។");

            Tags openApi = new Tags();
            openApi.setName("openapi");
            openApi.setDescription("OpenAPI Specification (OAS) គឺជា Format សម្រាប់ការរៀបចំ និងឯកសារ RESTful APIs។");

            Tags jsonWebToken = new Tags();
            jsonWebToken.setName("json-web-token");
            jsonWebToken.setDescription("JSON Web Token (JWT) គឺជា Standard សម្រាប់ការផ្ញើ Data ដែលបាន Signing រវាង Client និង Server។");

            Tags keycloak = new Tags();
            keycloak.setName("keycloak");
            keycloak.setDescription("Keycloak គឺជា Open Source Identity និង Access Management Tool សម្រាប់ការគ្រប់គ្រង Authentication និង Authorization។");

            Tags sonarQube = new Tags();
            sonarQube.setName("sonarqube");
            sonarQube.setDescription("SonarQube គឺជា Tool សម្រាប់ការវិភាគ និងធ្វើ Code Quality និង Security Scanning។");

            Tags logstash = new Tags();
            logstash.setName("logstash");
            logstash.setDescription("Logstash គឺជា Tool សម្រាប់ប្រមូល, កែតម្រូវ, និងបញ្ជូន Logs ទៅ Elasticsearch ឬ Destination ផ្សេងទៀត។");

            Tags prometheus = new Tags();
            prometheus.setName("prometheus");
            prometheus.setDescription("Prometheus គឺជា Monitoring System និង Time Series Database សម្រាប់ការចងតាម Metrics។");

            Tags grafana = new Tags();
            grafana.setName("grafana");
            grafana.setDescription("Grafana គឺជា Tool សម្រាប់ការបង្ហាញ Data Visualizations និងការគ្រប់គ្រង Dashboard។");

            Tags newRelic = new Tags();
            newRelic.setName("new-relic");
            newRelic.setDescription("New Relic គឺជា Monitoring Tool សម្រាប់ការតាមដាន និងវិភាគការងាររបស់ Applications និង Infrastructure។");

            Tags datadog = new Tags();
            datadog.setName("datadog");
            datadog.setDescription("Datadog គឺជា Monitoring និង Analytics Platform សម្រាប់ Cloud Applications និង Infrastructure។");


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
            communityEngagement.setLoveCount(0L);
            communityEngagement.setFireCount(0L);
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
