output "pgvector_fqdn" {
  value = azurerm_container_app.pgvector.latest_revision_fqdn
}
