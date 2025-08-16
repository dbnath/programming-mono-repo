terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "~> 3.0"
    }
  }
}

provider "azurerm" {
  subscription_id = "4038d739-ccfe-40dc-a763-9a110551057e"  
  features {}
}

resource "azurerm_resource_group" "rg" {
  name     = "pgvector-rg"
  location = "East US"
}

resource "azurerm_virtual_network" "vnet" {
  name                = "pgvector-vnet"
  address_space       = ["10.20.0.0/22"]
  location            = azurerm_resource_group.rg.location
  resource_group_name = azurerm_resource_group.rg.name
}

resource "azurerm_subnet" "subnet" {
  name                 = "pgvector-subnet"
  resource_group_name  = azurerm_resource_group.rg.name
  virtual_network_name = azurerm_virtual_network.vnet.name
  address_prefixes     = ["10.20.0.0/23"]
}

resource "azurerm_container_app_environment" "cae" {
  name                = "pgvector-cae"
  location            = azurerm_resource_group.rg.location
  resource_group_name = azurerm_resource_group.rg.name
  infrastructure_subnet_id = azurerm_subnet.subnet.id
}

resource "azurerm_container_app" "ca" {
  name                         = "pgvector"
  container_app_environment_id = azurerm_container_app_environment.cae.id
  resource_group_name          = azurerm_resource_group.rg.name
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
