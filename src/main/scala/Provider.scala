package io.yard.common.models

trait Provider {
  def name: String
  def controller: Option[ModuleController]

  def send(message: Message, organization: Organization)
}

trait ProviderConfiguration {}
