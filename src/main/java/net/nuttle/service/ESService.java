package net.nuttle.service;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ESService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ESService.class);

  @Value("${esScheme}")
  private String scheme;
  @Value("${esHost}")
  private String host;
  @Value("${esPort}")
  private int port;
  
  public RestHighLevelClient getClient() {
    return new RestHighLevelClient(getRestClientBuilder());
  }
  
  public RestClient getLowLevelClient() {
    return getRestClientBuilder().build();
  }
  
  public RestClientBuilder getRestClientBuilder() {
    return RestClient.builder(new HttpHost(host, port, scheme));
  }

  /**
   * Demo search: Match all in Shakespeare, with sort and aggregations
   * @return
   */
  public SearchResponse matchAllShakespeare() throws IOException {
    try (RestHighLevelClient client = getClient()) {
      SearchRequest req = new SearchRequest("shakespeare");
      SearchSourceBuilder ssb = new SearchSourceBuilder();
      ssb.query(QueryBuilders.matchAllQuery());
      /* Jackson gets confused by aggs for some reason TBD
      TermsAggregationBuilder tab = AggregationBuilders.terms("play")
          .field("play_name");
      tab.subAggregation(AggregationBuilders.terms("speaker").field("speaker"));
      ssb.aggregation(tab);
      */
      ssb.from(0);
      ssb.size(2);
      ssb.sort(SortBuilders.fieldSort("play_name"));
      req.source(ssb);
      LOGGER.debug(req.toString());
      SearchResponse resp = client.search(req, RequestOptions.DEFAULT);
      return resp;
    }
  }
  
    
}
