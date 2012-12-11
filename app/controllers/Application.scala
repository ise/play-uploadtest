package controllers

import play.api.mvc._
import play.api.libs.json.Json._
import java.io.File

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index())
  }

  def upload = Action(parse.multipartFormData) { request =>
    request.body.file("file").map {file =>
      file.ref.moveTo(new File("/tmp/play-uploadtest", file.filename), true)
      Ok(toJson(Map("status" -> "ok")))
    }.getOrElse {
      Ok(toJson(Map("status" -> "error")))
    }
  }
  
}