package io.yard.common.models

case class Message (
  text: String,
  organization: Option[String] = None,
  user: Option[String] = None,
  channel: Option[String] = None
)

case class Command (
  action: String,
  arguments: Seq[String],
  organization: Option[String] = None,
  user: Option[String] = None,
  channel: Option[String] = None
)
