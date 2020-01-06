package controllers

import play.api.data.Forms._
import play.api.data._

case class UserData(username: String, password: String)

object UserData {
  val form: Form[UserData] = Form(
    mapping(
      "username" -> text,
      "password" -> text
    )(UserData.apply)(UserData.unapply)
  )
}