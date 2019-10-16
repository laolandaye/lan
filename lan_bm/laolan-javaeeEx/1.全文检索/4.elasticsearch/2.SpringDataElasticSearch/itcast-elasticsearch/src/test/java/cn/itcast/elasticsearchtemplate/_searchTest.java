package cn.itcast.elasticsearchtemplate;

import cn.itcast.elasticsearch.ElasticsearchApplication;
import cn.itcast.elasticsearch.pojo.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchApplication.class)
public class _searchTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void test_query_match_all(){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery()).build();

        List<Map> res = elasticsearchTemplate.queryForList(searchQuery, Map.class);
        for (Map re : res) {
            System.out.println(re);
        }
    }
}
