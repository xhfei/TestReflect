package solr;

import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;


/**
* @author E-mail:xingxing.feng@renren-inc.com
* @version :2012-3-7 下午05:54:23
* readme
*/
public class QueryClient {
	
	
	public List<Page4Search> Query(String q,int start,int num)throws Exception
	{
		SolrServer server = new CommonsHttpSolrServer("http://10.22.200.160:8082/solr/");
		 SolrQuery query = new SolrQuery();
		 query.setQuery(q);
		 query.setStart(new Integer(start));
		 query.setRows(new Integer(num));
		 
		 List<Page4Search> beans = null;
		 try {
			QueryResponse rsp = server.query(query);
			beans = rsp.getBeans(Page4Search.class);
		 } 
		 catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 return beans;
	}
	
	public static void main(String[] args)throws Exception{
		QueryClient queryclient=new QueryClient();
		List<Page4Search> listitems=queryclient.Query("NOT(sourceId:99)",2,2);
		System.out.print(listitems.size());
		System.out.print(listitems.toString());
	}
	
}
