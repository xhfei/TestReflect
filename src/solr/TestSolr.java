package solr;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;

public class TestSolr {
	public static void main(String... args) throws Exception {
		SolrServer server = new CommonsHttpSolrServer(
				"http://10.22.200.160:8082/solr/");
		SolrQuery query = new SolrQuery();
		query.setQuery("NOT(sourceId:99");
		query.setStart(2);
		query.setRows(2);

		List<Page4Search> beans = null;
		try {
			QueryResponse rsp = server.query(query);
			beans = rsp.getBeans(Page4Search.class);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
