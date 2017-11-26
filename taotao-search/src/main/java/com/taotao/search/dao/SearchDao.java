package com.taotao.search.dao;

import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * Created by ASUS on 2017/11/14.
 */
public interface SearchDao {
    SearchResult search(SolrQuery query) throws Exception;
}
