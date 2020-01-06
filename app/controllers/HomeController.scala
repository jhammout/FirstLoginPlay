package controllers

import javax.inject._
import play.api.mvc._


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(messagesAction: MessagesActionBuilder, components: ControllerComponents) extends AbstractController(components) {

  def list() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.list())
  }
  def login() = messagesAction { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.login(UserData.userFormConstraintsAdHoc))
  }

  def submit() = messagesAction { implicit request: MessagesRequest[AnyContent] =>
    UserData.userFormConstraintsAdHoc.bindFromRequest.fold(
      formWithErrors => {
        println("hello Error")
        BadRequest(views.html.login(formWithErrors))
      },
      formData => {
        //val contactId = UserData.save(contact)
        //Redirect(routes.HomeController.showContact(contactId)).flashing("success" -> "Contact saved!")
        println("hello success")
        Redirect(routes.HomeController.list()).flashing("success" -> "Contact saved!")
      }
    )
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
