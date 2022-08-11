package com.example.myim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

class HttpTool {
    static String sendHttpRequest(String address,String body){
        HttpURLConnection conn = null;
        BufferedReader br = null;
        StringBuffer response = null;
        String a = null;
        try {
                URL url = new URL(address);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                /**POST*/
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setUseCaches(false);
                /** */
                conn.setConnectTimeout(8000);
                conn.setReadTimeout(8000);
                /** 请求 */
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                writer.write(body);
                writer.flush();
                writer.close();
                /**===== */
                InputStream in = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(in));
                String line;
                response = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }

//            String re = "(?<=<body>).*(?=</body>)";
            String rep = response.toString();
//            if(Pattern.matches(re,rep)) {
//                Pattern re1 = Pattern.compile(re);
//                Matcher match = re1.matcher(rep);
//                match.find();
//                String result = match.group();
//                return result + "result";
//            }
                return rep;

        } catch (Exception e) {
            e.printStackTrace();
            a =  e.toString();
            return "异常" + a;

        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

    }


}