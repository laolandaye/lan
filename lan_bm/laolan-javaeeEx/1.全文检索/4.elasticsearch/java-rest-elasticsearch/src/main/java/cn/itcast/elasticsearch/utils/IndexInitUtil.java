package cn.itcast.elasticsearch.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IndexInitUtil {
    public RestClient initLowLevelClient() {
        // 通过 ip 、port 和协议建立 Elastic Search 客户端
        RestClient restClient = RestClient.builder(
                new HttpHost("114.116.14.239", 9200, "http")).build();


        try {
            initIndex(restClient);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return restClient;
    }

    public RestHighLevelClient initHighLevelClient() {
        // 通过 ip 、port 和协议建立 Elastic Search 客户端
        RestHighLevelClient highLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("114.116.14.239", 9200, "http"))
        );

        RestClient restClient = RestClient.builder(
                new HttpHost("114.116.14.239", 9200, "http")).build();

        try {
            initIndex(restClient);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                restClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return highLevelClient;
    }

    private void initIndex(RestClient restClient) {

        String authIndexDefine = "{\n" +
                "\t\"settings\" : {\n" +
                "        \"index\" : {\n" +
                "            \"number_of_shards\" : 6,\n" +
                "            \"number_of_replicas\" : 0\n" +
                "        }\n" +
                "    },\n" +
                "    \"mappings\": {\n" +
                "        \"doc\": {\n" +
                "            \"properties\": {\n" +
                "            \t\"id\": {\"type\": \"text\"},\n" +
                "                \"name\": {\"type\": \"text\"},\n" +
                "                \"sex\": {\"type\": \"text\"},\n" +
                "                \"age\": {\"type\": \"integer\"},\n" +
                "                \"des\":{\n" +
                "                \t\"type\":\"text\",\n" +
                "                \t\"analyzer\": \"ik_max_word\",\n" +
                "\t\t\t\t\t\"search_analyzer\": \"ik_max_word\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        HttpEntity authorIndexEntity = new NStringEntity(authIndexDefine, ContentType.APPLICATION_JSON);
        //初始化要索引的 author 文档列表
        List<HttpEntity> authorDocs = new ArrayList<HttpEntity>();
        authorDocs.add(new NStringEntity(" {\n" +
                "\t\"id\":\"A1001\",\n" +
                "\t\"name\":\"任盈盈\",\n" +
                "\t\"age\":24,\n" +
                "\t\"sex\":\"女\",\n" +
                "\t\"des\":\"IT软件工程师，擅长Java和软件架构\"\n" +
                " }", ContentType.APPLICATION_JSON));
        authorDocs.add(new NStringEntity(" {\n" +
                "\t\"id\":\"A1002\",\n" +
                "\t\"name\":\"风清扬\",\n" +
                "\t\"age\":47,\n" +
                "\t\"sex\":\"男\",\n" +
                "\t\"des\":\"IT软件技术经理，擅长技术管理过程控制\"\n" +
                " }", ContentType.APPLICATION_JSON));


        try {
            //创建 author_test 索引
            restClient.performRequest("PUT", "/author_test", new HashMap<String, String>(), authorIndexEntity);

            //索引 author_index 文档
            for (int i = 0; i < authorDocs.size(); i++) {
                restClient.performRequest("POST", "/author_test/doc", new HashMap<String, String>(), authorDocs.get(i));
            }
            //注意索引文档完成后，做一个小的延迟，保证后续查询能查到数据
            Thread.currentThread().sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


