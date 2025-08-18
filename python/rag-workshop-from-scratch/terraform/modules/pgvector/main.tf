
resource "azurerm_container_app" "pgvector" {
  name                         = var.container_app_name
  container_app_environment_id = var.container_app_environment_id
  resource_group_name          = var.resource_group_name
  revision_mode                = "Single"

  template {
    container {
      name   = "pgvector"
      image  = "pgvector/pgvector:pg17"
      cpu    = 0.25
      memory = "0.5Gi"

      env {
        name  = "POSTGRES_PASSWORD"
        value = "postgres"
      }
    }
  }

  ingress {
    external_enabled = true
    target_port      = 5432
    exposed_port     = 6432
    transport        = "tcp"
    traffic_weight {
      percentage = 100
      latest_revision = true
    }
  }
}


# Create the rag_demo database after the container app is running
resource "null_resource" "create_rag_demo_db" {
  depends_on = [azurerm_container_app.pgvector]

  provisioner "local-exec" {
    command = "sleep 30 && PGPASSWORD=postgres psql -h ${azurerm_container_app.pgvector.latest_revision_fqdn} -U postgres -p 6432 -c \"CREATE DATABASE rag_demo;\""
    interpreter = ["/bin/bash", "-c"]
  }

  provisioner "local-exec" {
    command = "sleep 30 && PGPASSWORD=postgres psql -h ${azurerm_container_app.pgvector.latest_revision_fqdn} -U postgres -p 6432 -d rag_demo -f schema.sql"
    interpreter = ["/bin/bash", "-c"]
  }
}