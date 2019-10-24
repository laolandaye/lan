package cn.itcast.elasticsearch.utils;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Elastic Search 高级 Api 测试类
 *
 * @author 杨高超
 * @since 2018-01-11
 */
public class HighLevelApiTest {
    RestHighLevelClient restClient = null;

    @Before
    public void before() {
        restClient = new IndexInitUtil().initHighLevelClient();
    }


    @Test
    public void testQueryAuthDoc() {
        try {
            SearchRequest searchRequest = new SearchRequest("author_test");
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(QueryBuilders.matchQuery("des", "软件"));
            sourceBuilder.from(0);
            sourceBuilder.size(5);
            searchRequest.source(sourceBuilder);
            SearchResponse response = restClient.search(searchRequest);
            Assert.assertTrue(response.getHits().getTotalHits() == 2);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }


    @After
    public void after() {
        try {
            if (restClient != null) {
                restClient.indices().deleteIndex(new DeleteIndexRequest("author_test"));
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
}

