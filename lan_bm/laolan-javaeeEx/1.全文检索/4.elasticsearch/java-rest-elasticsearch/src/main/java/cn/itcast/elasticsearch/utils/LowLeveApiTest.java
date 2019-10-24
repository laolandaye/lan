package cn.itcast.elasticsearch.utils;

import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.codehaus.jettison.json.JSONObject;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Elastic Search 低级 Api 测试类
 *
 * @author 杨高超
 * @since 2018-01-11
 */
public class LowLeveApiTest {
    RestClient restClient = null;

    @Before
    public void before() {
        restClient = new IndexInitUtil().initLowLevelClient();
    }

    @Test
    public void testLocateAuthorIndex() {
        try {
            Response response = restClient.performRequest("GET", "/author_test");
            String responseData = readResposne(response);
            Assert.assertTrue(new JSONObject(responseData).has("author_test"));
            System.out.println(responseData);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }


    @Test
    public void testQueryAuthDoc() {
        try {
            String queryJson = "{\n" +
                    "    \"query\": {\n" +
                    "        \"match\": {\n" +
                    "            \"des\": \"Java\"\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
            Response response = restClient.performRequest("POST",
                    "/author_test/_search",
                    new HashMap<String, String>(),
                    new NStringEntity(queryJson, ContentType.APPLICATION_JSON));

            String responseData = readResposne(response);
            JSONObject responseJson = new JSONObject(responseData);
            Assert.assertTrue(responseJson.has("hits")
                    && responseJson.getJSONObject("hits").getInt("total") == 1);
            System.out.println(responseData);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testQueryAuthDocAsy() {
        try {
            String queryJson = "{\n" +
                    "    \"query\": {\n" +
                    "        \"match\": {\n" +
                    "            \"des\": \"软件\"\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";


            restClient.performRequestAsync("POST",
                    "/author_test/_search",
                    new HashMap<String, String>(),
                    new NStringEntity(queryJson, ContentType.APPLICATION_JSON), new ResponseListener() {
                        public void onSuccess(Response response) {
                            try {
                                String responseData = readResposne(response);
                                System.out.println("******* search success ******");
                                System.out.println(responseData);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        public void onFailure(Exception exception) {
                            exception.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }


    @After
    public void after() {
        try {
            if (restClient != null) {
                restClient.performRequest("DELETE", "/author_test");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (restClient != null) {
                try {
                    restClient.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String readResposne(Response response) throws Exception {
        BufferedReader brd = new BufferedReader(new BufferedReader(new InputStreamReader(response.getEntity().getContent())));
        String line;
        StringBuilder respongseContext = new StringBuilder();

        while ((line = brd.readLine()) != null) {
            respongseContext.append(line).append("\n");
        }
        //rd.close();
        if (respongseContext.length() > 0) {
            respongseContext.deleteCharAt(respongseContext.length() - 1);
        }
        return respongseContext.toString();
    }
}

