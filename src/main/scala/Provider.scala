package io.yard.models

trait Provider {
  def name: String

  def send(message: Message, organization: Organization)
}

trait ProviderConfiguration {}
