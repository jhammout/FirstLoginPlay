package controllers

import javax.inject._
import play.api.mvc._


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def login() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.login(UserData.form))
  }

  def submit() = Action { implicit request =>
    val formData: UserData = UserData.form.bindFromRequest.get // Careful: BasicForm.form.bindFromRequest returns an Option
    Ok(formData.toString) // just returning the data because it's an example :)
  }





  /*case class UserData(username: String, password: String)

  val userForm = Form(
    mapping(
      "username" -> text,
      "password"  -> text
    )(UserData.apply)(UserData.unapply)
  )*/
  /*def validate(username: String, password: String) = {
    username match {
      case "bob" =>
        Some(UserData(username, password))
      case "admin" =>
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

  userForm.bindFromRequest.fold(
    formWithErrors => {
      // binding failure, you retrieve the form containing errors:
      BadRequest(views.html.login(formWithErrors))
    },
    userData => {
      /* binding success, you get the actual value. */
      val newUser = models.User(userData.username, userData.password)
      val id      = models.User.create(newUser)
      Redirect(routes.HomeController.list)
    }
  )
  val userPostWithErrors = Action(
    parse.form(
      userForm,
      onErrors = (formWithErrors: Form[UserData]) => {
        implicit val messages = messagesApi.preferred(Seq(Lang.defaultLang))
        BadRequest(views.html.login(formWithErrors))
      }
    )
  ) { implicit request =>
    val userData = request.body
    val newUser  = models.User(userData.name, userData.age)
    val id       = models.User.create(newUser)
    Redirect(routes.HomeController.list)
  }*/



}
