/* Copyright 2013 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.justin.resume_internal;

import com.appspot.justin_katzwhite_resume.resume.Resume;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

import javax.annotation.Nullable;

public class AppConstants {

  public static final String WEB_CLIENT_ID = "your_web_client_id";

  public static final String AUDIENCE = "server:client_id:" + WEB_CLIENT_ID;

  public static final JsonFactory JSON_FACTORY = new AndroidJsonFactory();

  public static final HttpTransport HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();

  public static Resume getApiServiceHandle(@Nullable GoogleAccountCredential credential) {
    // Use a builder to help formulate the API request.
    Resume.Builder tastebudz = new Resume.Builder(AppConstants.HTTP_TRANSPORT,
        AppConstants.JSON_FACTORY, credential);

//    tastebudz.setRootUrl("http://192.168.0.100:8080/_ah/api/");
//    tastebudz.setRootUrl("http://192.168.0.102:8080/_ah/api/");
    tastebudz.setRootUrl("https://justin-katzwhite-resume.appspot.com/_ah/api/");

    return tastebudz.build();
  }
}
