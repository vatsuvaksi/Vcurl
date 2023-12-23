package com.vatsuvaksi.clients.httpclients;


import com.vatsuvaksi.exceptions.clients.httpexceptions.HttpException;
import com.vatsuvaksi.requests.protocols.http.nonsecure.HttpRequest;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;


public class DeleteRequest<K> extends HttpClient<K> {

    private static final Logger logger = Logger.getLogger(DeleteRequest.class.getName());
    private static final String DELETE = "DELETE";

    @Override
    public K executeRequest(HttpRequest httpRequest, Class<K> responseType) {
        logger.info("Executing DELETE Request");
        HttpURLConnection con = null;
        try {
            URL url = httpRequest.getUrl().toURL();
            con = openConnection(url);

            super.setRequestMethod(con, DELETE);

            super.setRequestHeaders(con, httpRequest);
            super.writeRequestBody(con,httpRequest);

            super.setConnectTimeout(con);

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                logger.info("DELETE Request Executed");
                return processResponse(con, responseType);
            } else {
                String msg = "Response Status Code Not Equal to OK[200] for " +
                        "-" + httpRequest.getUrl().getHost()  + "/" + httpRequest.getUrl().getPath();
                logger.warning(msg);
                throw new HttpException(msg);
            }
        } catch (Exception e) {
            logger.warning("Delete Request Execution Failed");
        } finally {
            disconnectSafely(con);
        }
        return null;
    }
}
