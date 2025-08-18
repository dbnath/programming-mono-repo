resource "azurerm_container_app" "chromadb" {
  name                         = var.container_app_name
  container_app_environment_id = var.container_app_environment_id
  resource_group_name          = var.resource_group_name
  revision_mode                = "Single"

  template {
    container {
      name   = "chromadb"
      image  = var.image
      cpu    = var.container_cpu
      memory = var.container_memory

      # add env blocks here if needed
    }
  }

  ingress {
    external_enabled = true
    target_port      = var.target_port
    exposed_port     = var.exposed_port
    transport        = "tcp"
    traffic_weight {
      percentage = 100
      latest_revision = true
    }
  }
}
