package io.yard.common.models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Organization (
  name: String
)

object Organization {
  implicit val organizationFormat = Json.format[Organization]
}
