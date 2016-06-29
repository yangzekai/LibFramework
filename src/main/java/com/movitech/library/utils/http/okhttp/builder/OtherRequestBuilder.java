/**
 * Copyright (C) 2016 The yuhaiyang Android Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.movitech.library.utils.http.okhttp.builder;


import com.movitech.library.utils.http.okhttp.request.OtherRequest;
import com.movitech.library.utils.http.okhttp.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * DELETE、PUT、PATCH等其他方法
 */
public class OtherRequestBuilder extends OkHttpRequestBuilder {
    private RequestBody requestBody;
    private String method;
    private String content;

    public OtherRequestBuilder(String method) {
        this.method = method;
    }

    @Override
    public RequestCall build() {
        return new OtherRequest(requestBody, content, method, url, tag, params, headers).build();
    }

    public OtherRequestBuilder requestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public OtherRequestBuilder requestBody(String content) {
        this.content = content;
        return this;
    }

    @Override
    public OtherRequestBuilder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public OtherRequestBuilder tag(Object tag) {
        this.tag = tag;
        return this;
    }


    @Override
    public OtherRequestBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public OtherRequestBuilder addHeader(String key, String val) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return this;
    }
}
