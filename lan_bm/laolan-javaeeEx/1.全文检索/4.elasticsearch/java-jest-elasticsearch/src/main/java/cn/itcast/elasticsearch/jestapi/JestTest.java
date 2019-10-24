package cn.itcast.elasticsearch.jestapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.client.JestResultHandler;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.IndicesExists;
import io.searchbox.indices.aliases.AddAliasMapping;
import io.searchbox.indices.aliases.ModifyAliases;
import io.searchbox.indices.aliases.RemoveAliasMapping;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class JestTest {

    private JestClient jestClient;


    @Before
    public void Init() {
        // Demo the JestClient
         jestClient = JestClientUtil.getJestClient();
    }

    @After
    public void destory() {
    }

    @Test
    public void Check_an_index()  throws IOException {
        // Check an index
        JestResult result = jestClient.execute(new IndicesExists.Builder("employees").build()); // 判断索引是否存在
        if(!result.isSucceeded()) {
            // 创建索引
            jestClient.execute(new CreateIndex.Builder("employees").build());
            System.out.println(result.getErrorMessage());
        }

        // Create an index with options
        Map<String, Object> settings = new HashMap<>();
        settings.put("number_of_shards", 11);
        settings.put("number_of_replicas", 2);
        jestClient.execute(new CreateIndex.Builder("employees").settings(settings).build());

        // Create an alias, then remove it
        jestClient.execute(new ModifyAliases.Builder(
                new AddAliasMapping.Builder(
                        "employees",
                        "e")
                        .build())
                .build());
        JestResult jestResult = jestClient.execute(new ModifyAliases.Builder(
                new RemoveAliasMapping.Builder(
                        "employees",
                        "e")
                        .build())
                .build());

        if(jestResult.isSucceeded()) {
            System.out.println("Success!");
        }
        else {
            System.out.println(jestResult.getErrorMessage());
        }
    }

    @Test
    public void Create_document()  throws IOException {
        // Sample JSON for indexing

        // {
        //  "name": "Michael Pratt",
        //  "title": "Java Developer",
        //  "skills": ["java", "spring", "elasticsearch"],
        //  "yearsOfService": 2
        // }

        // Index a document from String
        ObjectMapper mapper = new ObjectMapper();
        JsonNode employeeJsonNode = mapper.createObjectNode()
                .put("name", "Michael Pratt 1111111111111111111111111111111")
                .put("title", "Java Developer1 1111111111111111111111111111111")
                .put("yearsOfService", 2)
                .set("skills", mapper.createArrayNode()
                        .add("java 1111111111111111111111111111111")
                        .add("spring 1111111111111111111111111111111")
                        .add("elasticsearch 1111111111111111111111111111111"));
        jestClient.execute(new Index.Builder(employeeJsonNode.toString()).index("employees").type("employees").build());

        // Index a document from Map
        Map<String, Object> employeeHashMap = new LinkedHashMap<>();
        employeeHashMap.put("name", "Michael Pratt 22222222222222222222222");
        employeeHashMap.put("title", "Java Developer 22222222222222222222222");
        employeeHashMap.put("yearsOfService", 2);
        employeeHashMap.put("skills", Arrays.asList("java 22222222222222222222222", "spring 22222222222222222222222", "elasticsearch 22222222222222222222222"));
        jestClient.execute(new Index.Builder(employeeHashMap).index("employees").type("employees").build());

        // Index a document from POJO
        Employee employee = new Employee();
        employee.setName("Michael Pratt 3333333333333333333333333");
        employee.setTitle("Java Developer 3333333333333333333333333");
        employee.setYearsOfService(2);
        employee.setSkills(Arrays.asList("java 3333333333333333333333333", "spring 3333333333333333333333333", "elasticsearch 3333333333333333333333333"));
        jestClient.execute(new Index.Builder(employee).index("employees").type("employees").build());

    }

    @Test
    public void Search_document()  throws IOException {
        // Read document by ID
        Employee getResult = jestClient.execute(new Get.Builder("employees", "-Cio7m0BsvoQbkUY7Th1").build()).getSourceAsObject(Employee.class);

        System.out.println("Read document by ID:" + getResult);

        // Search documents
        String search = "{\n" +
                "  \"query\": {\n" +
                "    \"bool\": {\n" +
                "      \"must\": [\n" +
                "        { \"match\": { \"name\":   \"Michael Pratt\" }}\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
        List<SearchResult.Hit<Employee, Void>> searchResults =
                jestClient.execute(new Search.Builder(search).build())
                        .getHits(Employee.class);

        searchResults.forEach(hit -> {
            System.out.println(String.format("Document %s has score %s", hit.id, hit.score));
        });

        List<SearchResult.Hit<Map, Void>> searchResults2 =
//                jestClient.execute(new Search.Builder(search).build()).getHits(Map.class);
                jestClient.execute(new Search.Builder(search).addIndex("employees").addType("employees").build()).getHits(Map.class);
        searchResults2.forEach(map -> {
            System.out.println(String.format("Document %s has score %s", map.id, map.score));
        });

    }

    @Test
    public void Update_document()  throws IOException {
        Employee employee = new Employee();
        employee.setName("老兰");
        employee.setTitle("老兰 Java Developer");
        employee.setYearsOfService(2);
        employee.setSkills(Arrays.asList("老兰java", "老兰spring", "老兰elasticsearch"));

        // Update document
        employee.setYearsOfService(3);
        jestClient.execute(new Update.Builder(employee).index("employees").type("employees").id("-Cio7m0BsvoQbkUY7Th1").build());
    }

    @Test
    public void Delete_document()  throws IOException {
        // Delete documents
        jestClient.execute(new Delete.Builder("-Cio7m0BsvoQbkUY7Th1").index("employees").type("employees").build());
    }

    @Test
    public void Bulk_operations()  throws IOException {
        // Bulk operations
        Employee employeeObject1 = new Employee();
        employeeObject1.setName("John Smith");
        employeeObject1.setTitle("Python Developer");
        employeeObject1.setYearsOfService(10);
        employeeObject1.setSkills(Arrays.asList("python"));

        Employee employeeObject2 = new Employee();
        employeeObject2.setName("Kate Smith");
        employeeObject2.setTitle("Senior JavaScript Developer");
        employeeObject2.setYearsOfService(10);
        employeeObject2.setSkills(Arrays.asList("javascript", "angular"));

        jestClient.execute(new Bulk.Builder().defaultIndex("employees").defaultType("employees")
                .addAction(new Index.Builder(employeeObject1).build())
                .addAction(new Index.Builder(employeeObject2).build())
                .addAction(new Delete.Builder("3").build()) .build());
    }

    @Test
    public void Async_operations()  throws IOException {

        // Async operations
        Employee employeeObject3 = new Employee();
        employeeObject3.setName("Jane Doe");
        employeeObject3.setTitle("Manager");
        employeeObject3.setYearsOfService(20);
        employeeObject3.setSkills(Arrays.asList("managing"));

        jestClient.executeAsync( new Index.Builder(employeeObject3).build(), new JestResultHandler<JestResult>() {
            @Override public void completed(JestResult result) {
                // handle result
            }
            @Override public void failed(Exception ex) {
                // handle exception
            }
        });
    }

}