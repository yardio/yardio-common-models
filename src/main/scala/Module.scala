package io.yard.common.models

import akka.actor.{ ActorRef, Props }

import play.core.Router
import play.api._
import play.api.mvc._

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Module(
  name: String,
  description: String,
  controller: Option[ModuleController] = None,
  actorProps: Option[Props] = None,
  actor: Option[ActorRef] = None // Don't try to set this one
)

trait ModuleConfiguration {}

abstract class ModuleController
    extends Controller
    with Router.Routes {

  protected var path: String = ""

  def setPrefix(prefix: String) {
    path = prefix
  }

  def prefix = path

  def documentation = Nil

  def hasRoute(rh: RequestHeader): Boolean

  def applyRoute[RH <: RequestHeader, H >: Handler](rh: RH, default: RH ⇒ H): H

  def routes = new scala.runtime.AbstractPartialFunction[RequestHeader, Handler] {
    override def applyOrElse[RH <: RequestHeader, H >: Handler](rh: RH, default: RH ⇒ H) = {
      if (rh.path.startsWith(path)) {
        applyRoute(rh, default)
      } else {
        default(rh)
      }
    }

    def isDefinedAt(rh: RequestHeader) = rh.path.startsWith(path) && hasRoute(rh)
  }
}
