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
  name     = "rag-rg"
  location = "East US"
}

resource "azurerm_virtual_network" "vnet" {
  name                = "rag-vnet"
  address_space       = ["10.20.0.0/22"]
  location            = azurerm_resource_group.rg.location
  resource_group_name = azurerm_resource_group.rg.name
}

resource "azurerm_subnet" "subnet" {
  name                 = "rag-subnet"
  resource_group_name  = azurerm_resource_group.rg.name
  virtual_network_name = azurerm_virtual_network.vnet.name
  address_prefixes     = ["10.20.0.0/23"]
}

resource "azurerm_container_app_environment" "cae" {
  name                = "rag-cae"
  location            = azurerm_resource_group.rg.location
  resource_group_name = azurerm_resource_group.rg.name
  infrastructure_subnet_id = azurerm_subnet.subnet.id
}


module "pgvector" {
  source = "./modules/pgvector"

  resource_group_name = azurerm_resource_group.rg.name
  container_app_environment_id = azurerm_container_app_environment.cae.id
  # Optional overrides; module has sensible defaults1
  container_app_name     = "pgvector"
}


// Instantiate chromadb container app using mo2dule
module "chromadb" {
  source = "./modules/chromadb"

  resource_group_name          = azurerm_resource_group.rg.name
  container_app_environment_id = azurerm_container_app_environment.cae.id
  container_cpu                = 0.5
  container_memory             = "1Gi"
  target_port                  = 8000
  exposed_port                 = 8000
}

