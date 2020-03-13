import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.services.cloudresourcemanager.CloudResourceManager
import com.takezoux2.googleapijar.Auth

import scala.collection.JavaConverters._


object Main {
  def main(args: Array[String]): Unit = {
    val auth = new Auth()
    val credential = auth.authenticate()

    val resourceManager = new CloudResourceManager.Builder(
      auth.HttpTransport,
      auth.JsonFactory,
      credential
    ).build()

    println("Projects are")
    resourceManager.projects().list().execute().getProjects.asScala.foreach(p => {
      println(s"${p.getProjectId}:${p.getName}")
    })

    println("Done")
  }


}