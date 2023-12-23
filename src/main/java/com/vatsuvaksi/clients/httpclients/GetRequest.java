package com.vatsuvaksi.clients.httpclients;


import com.vatsuvaksi.exceptions.clients.httpexceptions.HttpException;
import com.vatsuvaksi.requests.protocols.http.nonsecure.HttpRequest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class GetRequest<K> extends HttpClient<K> {
    private static final String GET = "GET";
    private static final Logger logger = Logger.getLogger(GetRequest.class.getName());

    @Override
    public K executeRequest(HttpRequest httpRequest, Class<K> responseType) {
        logger.info("Executing GET Request");
        HttpURLConnection con = null;
        try {
            URL url = httpRequest.getUrl().toURL();
            con = openConnection(url);

            super.setRequestMethod(con, GET);

            super.setRequestHeaders(con, httpRequest);
            super.writeRequestBody(con,httpRequest);

            super.setConnectTimeout(con);

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                logger.info("GET Request Executed");
                return processResponse(con, responseType);
            } else {
                String msg = "Response Status Code Not Equal to OK[200] for " +
                        "-" + httpRequest.getUrl().getHost()  + "/" + httpRequest.getUrl().getPath();
                logger.warning(msg);
                throw new HttpException(msg);
            }
        } catch (Exception e) {
            logger.warning("GET Request Execution Failed");
        } finally {
            disconnectSafely(con);
        }
        return null;
    }
}
