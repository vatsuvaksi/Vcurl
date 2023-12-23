package com.vatsuvaksi.clients.httpclients;


import com.vatsuvaksi.exceptions.clients.httpexceptions.HttpException;
import com.vatsuvaksi.requests.protocols.http.nonsecure.HttpRequest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;


public class PostRequest<K> extends HttpClient<K> {
    private static final String POST = "POST";
    private static final Logger logger = Logger.getLogger(PostRequest.class.getName());

    @Override
    public K executeRequest(HttpRequest httpRequest, Class<K> responseType) {
        logger.info("Executing POST Request");
        HttpURLConnection con = null;
        try {
            URL url = httpRequest.getUrl().toURL();
            con = openConnection(url);

            super.setRequestMethod(con, POST);
            super.setRequestHeaders(con, httpRequest);
            super.writeRequestBody(con,httpRequest);

            super.setConnectTimeout(con);

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                logger.info("POST Request Executed");
                return processResponse(con, responseType);
            } else {
                String msg = "Response Status Code Not Equal to OK[200] for " +
                        "-" + httpRequest.getUrl().getHost()  + "/" + httpRequest.getUrl().getPath();
                logger.warning(msg);
                throw new HttpException(msg);
            }
        } catch (Exception e) {
            logger.warning("POST Request Execution Failed");
        } finally {
            disconnectSafely(con);
        }
        return null;
    }
}
