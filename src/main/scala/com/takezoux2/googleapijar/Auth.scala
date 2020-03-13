package com.takezoux2.googleapijar

import java.io.InputStreamReader

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.{GoogleAuthorizationCodeFlow, GoogleClientSecrets}
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.{HttpRequest, HttpRequestInitializer}
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.drive.DriveScopes

import scala.collection.JavaConverters._

class Auth() {

  val CredentialFilePath = "/credentials.json"
  val JsonFactory = JacksonFactory.getDefaultInstance()

  val Scopes = List(
    DriveScopes.DRIVE,
    "https://www.googleapis.com/auth/cloud-platform",
    "https://www.googleapis.com/auth/adwords",
    "https://www.googleapis.com/auth/bigquery",
    "https://www.googleapis.com/auth/cloud-platform"
  ).asJava

  lazy val HttpTransport = GoogleNetHttpTransport.newTrustedTransport()

  private var _credential: Credential = null

  def credential = _credential


  def authenticate() = {

    val cFilePath = getClass.getResourceAsStream(CredentialFilePath)
    val clientSecrets = GoogleClientSecrets.load(JsonFactory, new InputStreamReader(cFilePath))


    val flow = new GoogleAuthorizationCodeFlow.Builder(
      HttpTransport, JsonFactory, clientSecrets, Scopes)
      .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("token")))
      .setAccessType("offline")
      .build()

    val receiver = new LocalServerReceiver.Builder().setPort(8888).build()

    val credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user")

    //refresh token in 5 minutes
    val now = new java.util.Date()
    if (credential.getExpirationTimeMilliseconds - now.getTime < 1000 * 300)
      credential.refreshToken()

    this._credential = credential
    credential
  }


}

trait TokenStorage {

  def getToken(): Option[String]
  def saveToken(token: String): Unit
}
