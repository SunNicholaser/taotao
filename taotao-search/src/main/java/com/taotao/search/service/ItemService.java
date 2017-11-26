package com.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * Created by ASUS on 2017/11/14.
 */
public interface ItemService {
    TaotaoResult importItems() throws IOException, SolrServerException, Exception;
}
