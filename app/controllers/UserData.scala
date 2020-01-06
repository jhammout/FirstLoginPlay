package controllers

import play.api.data.Forms._
import play.api.data._
import play.api.data.validation.Constraints._

case class UserData(username: String, password: String)

object UserData {
  val form: Form[UserData] = Form(
    mapping(
      "username" -> text,
      "password" -> text
    )(UserData.apply)(UserData.unapply)
  )
  def save(username: UserData): Int = 99

  def validate(username: String, password: String):Option[UserData] = {
    username match {
      case "jihane" if password == "admin" =>
        Some(UserData(username, password))
      case _ =>
        None
    }
  }

    val userFormConstraintsAdHoc = Form(
    mapping(
      "username" -> text,
      "password"  -> text
    )(UserData.apply)(UserData.unapply).verifying(
      "Failed form constraints!",
      fields =>
        fields match {
          case userData => validate(userData.username, userData.password).isDefined
        }
    )
  )
}