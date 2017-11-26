package com.taotao.solrj;

import com.taotao.common.pojo.ExceptionUtil;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * Created by ASUS on 2017/11/14.
 */
public class SolrJTest {
    @Test
    public void testSolrJ() throws Exception{
        //创建一个连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.33.129:8080/solr");
        //创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();
        //添加域
        document.addField("id","solrtest01");
        document.addField("item_title","测试商品");
        document.addField("item_sell_point","买点");
        //添加到索引库
        solrServer.add(document);
        //提交
        solrServer.commit();

    }
    @Test
    public void testQuery() throws Exception{
        //创建连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.33.129:8080/solr");
        //创建一个查询对象
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        //执行查询
        QueryResponse response = solrServer.query(query);
        //取查询结果
        SolrDocumentList solrDocumentList = response.getResults();
        for (SolrDocument solrDocument : solrDocumentList){
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_sell_point"));

        }
    }

    @Test
    public void testSolrCloud() throws Exception{
        //创建一个solrsercer对象
        CloudSolrServer solrServer = new CloudSolrServer("192.168.33.129:2181,192.168.33.129:2182,192.168.33.129:2183");
        //设置默认的colleciton
        solrServer.setDefaultCollection("collection2");
        //创建文档对象
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id","test01");
        document.addField("item_title","title1");
        //添加文档
        solrServer.add(document);
        solrServer.commit();
    }
}
